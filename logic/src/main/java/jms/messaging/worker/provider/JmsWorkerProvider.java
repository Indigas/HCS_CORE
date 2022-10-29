package jms.messaging.worker.provider;

import jms.messaging.worker.implementations.JmsPatientWorker;

public interface JmsWorkerProvider {

    JmsPatientWorker createJmsPatientWorker();
}
