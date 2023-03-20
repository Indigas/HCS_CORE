package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.provider.JmsWorkerServiceProvider;

import java.util.ServiceLoader;

public class JmsWorker{

    private static ServiceLoader<JmsWorkerServiceProvider> loader = ServiceLoader.load(JmsWorkerServiceProvider.class);

    public static JmsWorkerServiceProvider provider(){
        // should never be null
        return loader.iterator().next();
    }

}
