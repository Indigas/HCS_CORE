package jms.messaging.worker.provider.utility;

import jms.messaging.worker.implementations.JmsPatientWorker;
import jms.messaging.worker.provider.JmsWorkerProvider;

class JmsWorkerProviderImpl implements JmsWorkerProvider {
    @Override
    public JmsPatientWorker createJmsPatientWorker() {
        return new JmsPatientWorker();
    }
}
