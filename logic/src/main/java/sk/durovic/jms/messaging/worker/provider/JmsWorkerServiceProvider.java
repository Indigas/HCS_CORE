package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.service.*;
import sk.durovic.manager.service.EntityServiceManager;

import java.io.Serializable;

public interface JmsWorkerServiceProvider<T extends Serializable> {

    JmsPatientWorker<T> createJmsPatientWorker(EntityServiceManager esm);
    JmsDiseaseWorker<T> createJmsDiseaseWorker(EntityServiceManager esm);
    JmsContactWorker<T> createJmsContactWorker(EntityServiceManager esm);
    JmsDiagnoseWorker<T> createJmsDiagnoseWorker(EntityServiceManager esm);
    JmsMedicalRecordWorker<T> createJmsMedicalRecordWorker(EntityServiceManager esm);
    JmsPatient_DiagnoseWorker<T> createJmsPatient_DiagnoseWorker(EntityServiceManager esm);
}
