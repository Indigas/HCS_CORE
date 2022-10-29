package sk.durovic.provider;

import jms.messaging.worker.JmsMessageWorker;
import jms.messaging.worker.provider.utility.JmsWorker;
import org.junit.jupiter.api.Test;

public class JmsWorkerProviderTest {

    @Test
    void getProvider(){
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

    }
}
