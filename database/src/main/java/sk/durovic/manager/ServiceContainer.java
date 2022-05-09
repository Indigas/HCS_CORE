package sk.durovic.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import sk.durovic.model.*;
import sk.durovic.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {

    private final Map<Class<?>, Class<?>> servicesMap = new HashMap<>();

    @Autowired
    private ApplicationContext context;

    {
        setServices();
    }

    @SuppressWarnings("unchecked")
    public <T, R extends Service<?,ID,?>, ID> R getService(Class<T> clazz){
        return (R) context.getBean(servicesMap.get(clazz));
    }

    Object getObjectService(Class<?> clazz){
        return servicesMap.get(clazz);
    }

    void clear(){
        servicesMap.clear();
    }

    private void setServices(){
        servicesMap.put(Patient.class, PatientService.class);
        servicesMap.put(MedicalRecord.class, MedicalRecordService.class);
        servicesMap.put(Disease.class, DiagnoseService.class);
        servicesMap.put(Diagnose.class, DiagnoseService.class);
        servicesMap.put(Contact.class, ContactService.class);
        servicesMap.put(Patient_Diagnose.class, PatientDiagnoseService.class);
    }

}
