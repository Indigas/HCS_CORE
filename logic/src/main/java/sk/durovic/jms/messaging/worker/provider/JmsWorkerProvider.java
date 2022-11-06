package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.manager.service.EntityServiceManager;

public interface JmsWorkerProvider {

    JmsPatientWorker createJmsPatientWorker(EntityManager em);
    JmsDiseaseWorker createJmsDiseaseWorker(EntityManager em);
    JmsContactWorker createJmsContactWorker(EntityManager em);
    JmsDiagnoseWorker createJmsDiagnoseWorker(EntityManager em);
    JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityManager em);
    JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityManager em);
}
