package sk.durovic.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import sk.durovic.model.*;
import sk.durovic.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Return services for given class
 */
public class ServiceContainer {

    private final Map<Class<?>, Class<?>> servicesMap = new HashMap<>();

    @Autowired
    private ApplicationContext context;

    {
        setServices();
    }

    /**
     * Get service for given class
     * @param clazz
     * @param <T>
     * @param <R>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseEntityAbstractClass<?>, R> Optional<R> getService(Class<T> clazz){
        return Optional.of((R)context.getBean(servicesMap.get(clazz)));
    }

    /**
     * Get service for given class
     * @param clazz
     * @return
     */
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
