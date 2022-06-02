package sk.durovic.manager.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.basic.EntityManagerBasic;

@Component
public class EntityManagerFactory {

    private final ApplicationContext context;

    @Autowired
    public EntityManagerFactory(ApplicationContext context) {
        this.context = context;
    }

    public EntityManager getBasicEntityManager(){
        return new EntityManagerBasic(context);
    };
}
