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
    <T, R extends Service<?,?,?>> R getService(Class<T> clazz){
        return (R) context.getBean(servicesMap.get(clazz));
    }

    private void setServices(){
        servicesMap.put(Patient.class, PatientService.class);
        servicesMap.put(MedicalRecord.class, MedicalRecordService.class);
        servicesMap.put(Disease.class, DiagnoseService.class);
        servicesMap.put(Diagnose.class, DiagnoseService.class);
        servicesMap.put(Contact.class, ContactService.class);
    }

}
