package sk.durovic.manager.service;

import org.springframework.context.ApplicationContext;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.model.*;
import sk.durovic.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EntityServiceContainer implements ServiceContainer {

    private final Map<Class<?>, Class<?>> servicesMap = new HashMap<>();
    private final ApplicationContext context;

    public EntityServiceContainer(ApplicationContext context) {
        this.context = context;
        setServices();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEntityAbstractClass<?>, R> Optional<R> getService(Class<T> clazz) {
        return Optional.of((R)context.getBean(servicesMap.get(clazz)));
    }

    @Override
    public Object getObjectService(Class<?> clazz) {
        return servicesMap.get(clazz);
    }

    @Override
    public void clear() {
        // nothing to do
    }

    private void setServices(){
        servicesMap.put(Patient.class, PatientEntityService.class);
        servicesMap.put(MedicalRecord.class, MedicalRecordEntityService.class);
        servicesMap.put(Disease.class, DiagnoseEntityService.class);
        servicesMap.put(Diagnose.class, DiagnoseEntityService.class);
        servicesMap.put(Contact.class, ContactEntityService.class);
        servicesMap.put(Patient_Diagnose.class, PatientDiagnoseService.class);
    }
}
