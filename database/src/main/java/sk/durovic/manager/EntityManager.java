package sk.durovic.manager;

import org.springframework.stereotype.Component;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;

import java.lang.reflect.Constructor;

@Component
public class EntityManager {

    private final EntityContainer entityContainer;
    private final ServiceContainer serviceContainer;

    public EntityManager() {
        this.entityContainer = new EntityContainer();
        this.serviceContainer = new ServiceContainer();
    }


    <T extends BaseEntityAbstractClass<?>> T save(T object){
        Service<T,?,?> service = serviceContainer.getService(object.getClass());
        return service.save(object);
    }

    <T> T createEntity(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

}
