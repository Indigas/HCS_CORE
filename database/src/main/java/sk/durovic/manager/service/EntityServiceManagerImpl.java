package sk.durovic.manager.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sk.durovic.manager.ServiceContainer;

@Service
public class EntityServiceManagerImpl implements EntityServiceManager{

    private final ApplicationContext context;

    public EntityServiceManagerImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public ServiceContainer getServiceContainer(){
        return new EntityServiceContainer(context);
    }

}
