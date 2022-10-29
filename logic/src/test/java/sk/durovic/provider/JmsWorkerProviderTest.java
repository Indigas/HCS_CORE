package sk.durovic.provider;

import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import org.junit.jupiter.api.Test;

public class JmsWorkerProviderTest {

    @Test
    void getProvider(){
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

    }
}
