package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerServiceProvider;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;

public class JmsWorkerProviderImpl implements JmsWorkerServiceProvider {

    @Override
    public JmsPatientWorker createJmsPatientWorker(EntityServiceManager esm) {
        return new JmsPatientWorker(esm);
    }

    @Override
    public JmsDiseaseWorker createJmsDiseaseWorker(EntityServiceManager esm) {
        return new JmsDiseaseWorker(esm);
    }

    @Override
    public JmsContactWorker createJmsContactWorker(EntityServiceManager esm) {
        return new JmsContactWorker(esm);
    }

    @Override
    public JmsDiagnoseWorker createJmsDiagnoseWorker(EntityServiceManager esm) {
        return new JmsDiagnoseWorker(esm);
    }

    @Override
    public JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager esm) {
        return new JmsMedicalRecordWorker(esm);
    }

    @Override
    public JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager esm) {
        return new JmsPatient_DiagnoseWorker(esm);
    }
}
