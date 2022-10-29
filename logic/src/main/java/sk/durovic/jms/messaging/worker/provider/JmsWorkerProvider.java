package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;

public interface JmsWorkerProvider {

    JmsPatientWorker createJmsPatientWorker();
}
