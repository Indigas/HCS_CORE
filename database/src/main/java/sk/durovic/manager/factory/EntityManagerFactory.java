package sk.durovic.manager.factory;

import org.springframework.context.ApplicationContext;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.basic.EntityManagerBasic;

public abstract class EntityManagerFactory {

    public static EntityManager getBasicEntityManager(ApplicationContext context){
        return new EntityManagerBasic(context);
    };
}
