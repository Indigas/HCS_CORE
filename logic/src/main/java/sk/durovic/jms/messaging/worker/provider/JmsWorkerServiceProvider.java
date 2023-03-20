package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;

public interface JmsWorkerServiceProvider {

    JmsPatientWorker createJmsPatientWorker(EntityServiceManager esm);
    JmsDiseaseWorker createJmsDiseaseWorker(EntityServiceManager esm);
    JmsContactWorker createJmsContactWorker(EntityServiceManager esm);
    JmsDiagnoseWorker createJmsDiagnoseWorker(EntityServiceManager esm);
    JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager esm);
    JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager esm);
}
