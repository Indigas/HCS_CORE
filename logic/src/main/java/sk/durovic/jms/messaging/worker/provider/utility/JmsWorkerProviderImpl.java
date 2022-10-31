package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerProvider;

public class JmsWorkerProviderImpl implements JmsWorkerProvider {

    @Override
    public JmsPatientWorker createJmsPatientWorker() {
        return new JmsPatientWorker();
    }

    @Override
    public JmsDiseaseWorker createJmsDiseaseWorker() {
        return new JmsDiseaseWorker();
    }

    @Override
    public JmsContactWorker createJmsContactWorker() {
        return new JmsContactWorker();
    }

    @Override
    public JmsDiagnoseWorker createJmsDiagnoseWorker() {
        return new JmsDiagnoseWorker();
    }

    @Override
    public JmsMedicalRecordWorker createJmsMedicalRecordWorker() {
        return new JmsMedicalRecordWorker();
    }

    @Override
    public JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker() {
        return new JmsPatient_DiagnoseWorker();
    }
}
