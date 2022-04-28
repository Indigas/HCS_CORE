package sk.durovic.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedAnnotationTypes("sk.durovic.annotations.Connector")
@AutoService(Processor.class)
public class ConnectorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);

            annotatedElements.stream().forEach(element -> {
                    processFields( ((TypeElement) element).getEnclosedElements());

            });
        }

        return true;
    }

    private void processFields(List<? extends Element> fields){
        for (Element field :
                fields) {
            hasRelationAnnotation(field.getAnnotationMirrors());
        }
    }

    private void hasRelationAnnotation(List<? extends AnnotationMirror> annotations){
        for (AnnotationMirror onField :
                annotations) {
            if(onField.getAnnotationType().toString().equals("javax.persistence.OneToMany")
                    || onField.getAnnotationType().toString().equals("javax.persistence.ManyToOne")) {
                // call method to createMethods

            }
        }
    }
}
