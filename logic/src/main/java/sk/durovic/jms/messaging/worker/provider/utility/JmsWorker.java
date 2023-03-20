package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.provider.JmsWorkerManagerProvider;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerServiceProvider;

import java.util.ServiceLoader;

public class JmsWorker{

    private static ServiceLoader<JmsWorkerServiceProvider> serviceLoader = ServiceLoader.load(JmsWorkerServiceProvider.class);
    private static ServiceLoader<JmsWorkerManagerProvider> managerLoader = ServiceLoader.load(JmsWorkerManagerProvider.class);

    public static JmsWorkerServiceProvider serviceProvider(){
        // should never be null
        return serviceLoader.iterator().next();
    }

    public static JmsWorkerManagerProvider managerProvider(){
        return managerLoader.iterator().next();
    }

}
