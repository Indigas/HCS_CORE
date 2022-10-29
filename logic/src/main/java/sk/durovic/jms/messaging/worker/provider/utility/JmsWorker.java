package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.provider.JmsWorkerProvider;

import java.util.ServiceLoader;

public class JmsWorker{

    private static ServiceLoader<JmsWorkerProvider> loader = ServiceLoader.load(JmsWorkerProvider.class);

    public static JmsWorkerProvider provider(){
        // should never be null
        return loader.iterator().next();
    }

}
