package sk.durovic.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedAnnotationTypes("sk.durovic.annotations.EntityProcessor")
@AutoService(Processor.class)
public class EntityProcessor extends AbstractProcessor {

    final String CONNECT = "connect";
    final String DISCONNECT = "disconnect";
    final String SETTER = "set";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);

            annotatedElements.stream().forEach(element -> {

                Map<String, Map<String, String>>  fieldsMap = processFields( ((TypeElement) element).getEnclosedElements());

                try {
                    writeModelClass(fieldsMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }

        return true;
    }

    private Map<String, Map<String, String>> processFields(List<? extends Element> fields){
        Map<String, Map<String, String>> fieldsMap = new HashMap<>();
        Map<String, String> connectors = new HashMap<>();
        Map<String, String> setters = new HashMap<>();
        Map<String, String> classAttr = new HashMap<>();
        fieldsMap.put("connectors", connectors);
        fieldsMap.put("setters", setters);
        fieldsMap.put("classAttr", classAttr);


        for (Element field :
                fields) {
            if(field.getKind().toString().equals("CONSTRUCTOR"))
                continue;

            if(!classAttr.containsKey("pkgName"))
                classAttr.put("pkgName", field.getEnclosingElement().getEnclosingElement().toString());


            if(!classAttr.containsKey("fullQuallifiedName"))
                classAttr.put("fullQuallifiedName", field.getEnclosingElement().toString());

            if(!classAttr.containsKey("className"))
                classAttr.put("className", field.getEnclosingElement().getSimpleName().toString() + "Entity");

            if(hasRelationAnnotation(field.getAnnotationMirrors())) {
                String nameVar = field.getSimpleName().toString();
                String classVar = field.asType().toString();
                connectors.put(nameVar,classVar);

            } else {
                String nameVar = field.getSimpleName().toString();
                String classVar = field.asType().toString();

                if(!nameVar.equals("id"))
                    setters.put(nameVar, classVar);
            }

        }
        return fieldsMap;
    }

    private boolean hasRelationAnnotation(List<? extends AnnotationMirror> annotations){
        for (AnnotationMirror onField :
                annotations) {
            if(onField.getAnnotationType().toString().equals("javax.persistence.OneToMany")
                    || onField.getAnnotationType().toString().equals("javax.persistence.ManyToOne")) {
                // call method to createMethods
                return true;
            }
        }
        return false;
    }

    private void writeModelClass(Map<String, Map<String, String>> fields) throws IOException {
        Map<String, String> classAttr = fields.get("classAttr");

        String simpleClassName = classAttr.get("className");
        String pkgName = classAttr.get("pkgName") + ".access";
        String className = pkgName + "." + simpleClassName;
        String extendingClass = classAttr.get("fullQuallifiedName");

        fields.remove("className");
        fields.remove("pkgName");
        fields.remove("fullQuallifiedName");


        JavaFileObject classFile = processingEnv.getFiler().createSourceFile(className);
        try(PrintWriter out = new PrintWriter(classFile.openWriter())){
            // write package
            out.print("package ");
            out.print(pkgName);
            out.println(";");
            out.println();

            out.println("import javax.persistence.*;");
            out.println();


            out.println("@Entity");

            //write class
            out.print("public class ");
            out.print(simpleClassName);
            out.print(" extends ");
            out.print(extendingClass);
            out.println(" {");
            out.println();

            writeToPrintWriter(out, fields, "setters");
            writeToPrintWriter(out, fields, "connectors");

            out.print("}");
        }

    }

    private void writeToPrintWriter(PrintWriter out, Map<String, Map<String, String>> fieldsMap, String nameOfMapping){
        for (Map.Entry<String, String> field : fieldsMap.get(nameOfMapping).entrySet()) {
            out.println(writeMethod(field, nameOfMapping));
        }
    }

    private String writeMethod(Map.Entry<String, String> field, String nameOfMapping){
        StringBuilder sb = new StringBuilder();

        switch (nameOfMapping){
            case "connectors": writeConnectorsMethod(field,sb);
            break;
            case "setters": writeSettersMethod(field, sb);
            break;
            default: break;
        }

        return sb.toString();
    }

    private void writeSettersMethod(Map.Entry<String, String> field, StringBuilder sb) {
        String name = getBeautyName(field.getKey());
        String argumentType = field.getValue();
        String parameterName = getParamaterName(name.toLowerCase());
        String methodName = "super.set" + name;

        methodBody(sb, name, argumentType, parameterName, methodName, parameterName, SETTER);
    }

    private String writeConnectorsMethod(Map.Entry<String, String> field, StringBuilder sb){

        writeMethodForDiffRelations(field, sb, CONNECT);
        writeMethodForDiffRelations(field, sb, DISCONNECT);

        return sb.toString();
    }

    private void writeMethodForDiffRelations(Map.Entry<String, String> field, StringBuilder sb, String methodPrefix){

        if(field.getValue().contains("List"))
            oneToManyEntity(field, sb, methodPrefix);
        else
            manyToOneEntity(field, sb, methodPrefix);

    }

    private void oneToManyEntity(Map.Entry<String, String> field, StringBuilder sb, String methodPrefix){
        String addOrRemove = methodPrefix.equals(CONNECT) ? "add" : "remove";

        String name = getBeautyName(getMethodName(field.getValue()));
        String argumentType = getArgumentType(field.getValue());
        String parameterName = getParamaterName(name.toLowerCase());
        String methodName = "get" + getBeautyName(field.getKey()) + "()." + addOrRemove;

        methodBody(sb, name, argumentType, parameterName, methodName, parameterName, methodPrefix);
    }

    private void manyToOneEntity(Map.Entry<String, String> field, StringBuilder sb, String methodPrefix) {
        String name = getBeautyName(field.getKey());
        String argumentType = field.getValue();
        String parameterName = getParamaterName(name.toLowerCase());
        String methodName = "set" + name;
        String parameter = methodPrefix.equals(CONNECT) ? parameterName : "null";

        methodBody(sb, name, argumentType, parameterName, methodName, parameter, methodPrefix);
    }

    private void methodBody(StringBuilder sb, String name, String argumentType, String parameterName,
                            String methodName, String parameter, String methodPrefix) {
        sb.append("     public void ");
        sb.append(methodPrefix);
        sb.append(name);
        sb.append("(");
        sb.append(argumentType);
        sb.append(" ");
        sb.append(parameterName);
        sb.append(") {");
        sb.append("\n");
        sb.append("          ");
        sb.append(methodName);
        sb.append("(");
        sb.append(parameter);
        sb.append(");");
        sb.append("\n");
        sb.append("     }");
        sb.append("\n\n");
    }

    private String getArgumentType(String name){
        name = name.substring(name.lastIndexOf("<")+1);
        return name.replace(">", "");
    }

    private String getMethodName(String name){
        name = getParamaterName(name);
        name = name.replaceAll("[^a-zA-z0-9]","");

        return name;
    }

    private String getBeautyName(String name){
        String firstLetter = name.substring(0,1).toUpperCase();
        return firstLetter + name.substring(1);
    }

    private String getParamaterName(String name){
        return name.substring(name.lastIndexOf(".")+1);
    }

}
