package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.implementations.*;

public interface JmsWorkerProvider {

    JmsPatientWorker createJmsPatientWorker();
    JmsDiseaseWorker createJmsDiseaseWorker();
    JmsContactWorker createJmsContactWorker();
    JmsDiagnoseWorker createJmsDiagnoseWorker();
    JmsMedicalRecordWorker createJmsMedicalRecordWorker();
    JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker();
}
