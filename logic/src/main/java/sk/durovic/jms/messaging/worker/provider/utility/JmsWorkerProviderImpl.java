package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerProvider;
import sk.durovic.manager.service.EntityServiceManager;

public class JmsWorkerProviderImpl implements JmsWorkerProvider {

    @Override
    public JmsPatientWorker createJmsPatientWorker(EntityServiceManager ems) {
        return new JmsPatientWorker(ems);
    }

    @Override
    public JmsDiseaseWorker createJmsDiseaseWorker(EntityServiceManager ems) {
        return new JmsDiseaseWorker(ems);
    }

    @Override
    public JmsContactWorker createJmsContactWorker(EntityServiceManager ems) {
        return new JmsContactWorker(ems);
    }

    @Override
    public JmsDiagnoseWorker createJmsDiagnoseWorker(EntityServiceManager ems) {
        return new JmsDiagnoseWorker(ems);
    }

    @Override
    public JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager ems) {
        return new JmsMedicalRecordWorker(ems);
    }

    @Override
    public JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager ems) {
        return new JmsPatient_DiagnoseWorker(ems);
    }
}
