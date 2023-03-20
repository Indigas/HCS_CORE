package sk.durovic.configuration;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.service.ContactEntityService;

import java.util.Optional;

@TestConfiguration
public class EntityServiceManagerConfiguration {

    @Autowired
    private ApplicationContext context;
    EntityServiceManager esm = Mockito.mock(EntityServiceManager.class);
    ServiceContainer service = Mockito.mock(ServiceContainer.class);
    ContactEntityService entityService = Mockito.mock(ContactEntityService.class);

    @Bean
    public EntityServiceManager getEntityServiceManager(){

        Mockito.when(esm.getServiceContainer()).thenReturn(service);
        Mockito.when(service.getService(Mockito.any())).thenReturn(Optional.ofNullable(entityService));


        return esm;
    }

    @Bean
    public ContactEntityService getEntityService(){
        return entityService;
    }

    @Bean
    public EntityManagerCreator getFactory() {
        return new EntityManagerCreator(context);
    }
}
