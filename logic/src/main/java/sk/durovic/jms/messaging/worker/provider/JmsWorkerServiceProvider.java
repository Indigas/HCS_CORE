package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.service.*;
import sk.durovic.manager.service.EntityServiceManager;

public interface JmsWorkerServiceProvider {

    <T, ID> JmsMessageWorkerService<T, ID> createServiceWorker(EntityServiceManager esm);
     JmsPatientWorker createJmsPatientWorker(EntityServiceManager esm);
     JmsDiseaseWorker createJmsDiseaseWorker(EntityServiceManager esm);
     JmsContactWorker createJmsContactWorker(EntityServiceManager esm);
     JmsDiagnoseWorker createJmsDiagnoseWorker(EntityServiceManager esm);
     JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager esm);
     JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager esm);
}
