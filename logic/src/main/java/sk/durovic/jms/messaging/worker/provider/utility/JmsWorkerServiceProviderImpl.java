package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerServiceProvider;
import sk.durovic.jms.messaging.worker.service.*;
import sk.durovic.manager.service.EntityServiceManager;


public class JmsWorkerServiceProviderImpl implements JmsWorkerServiceProvider {

    @Override
    public <T, ID> JmsMessageWorkerService<T, ID> createServiceWorker(EntityServiceManager esm) {
        return new JmsMessageWorkerService<>(esm);
    }

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
    public  JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityServiceManager esm) {
        return new JmsMedicalRecordWorker(esm);
    }

    @Override
    public JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityServiceManager esm) {
        return new JmsPatient_DiagnoseWorker(esm);
    }
}
