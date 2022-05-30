package sk.durovic.manager.factory;

import sk.durovic.manager.EntityManager;
import sk.durovic.manager.basic.EntityManagerBasic;

public abstract class EntityManagerFactory {

    public static EntityManager getBasicEntityManager(){
        return new EntityManagerBasic();
    };
}
