package sk.durovic.provider;

import org.junit.jupiter.api.Test;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;

public class JmsWorkerProviderTest {

    @Test
    void getProvider(){
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

    }
}
