package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.manager.service.EntityServiceManager;

public interface JmsWorkerProvider {

    JmsPatientWorker createJmsPatientWorker(EntityServiceManager ems);
    JmsDiseaseWorker createJmsDiseaseWorker(EntityServiceManager ems);
    JmsContactWorker createJmsContactWorker(EntityServiceManager ems);
    JmsDiagnoseWorker createJmsDiagnoseWorker(EntityServiceManager ems);
    JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager ems);
    JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager ems);
}
