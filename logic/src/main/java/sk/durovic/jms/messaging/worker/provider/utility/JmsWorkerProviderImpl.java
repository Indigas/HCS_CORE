package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerProvider;

class JmsWorkerProviderImpl implements JmsWorkerProvider {
    @Override
    public JmsPatientWorker createJmsPatientWorker() {
        return new JmsPatientWorker();
    }
}
