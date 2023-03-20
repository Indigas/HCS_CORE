package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.provider.JmsWorkerServiceProvider;
import sk.durovic.jms.messaging.worker.service.*;
import sk.durovic.manager.service.EntityServiceManager;

import java.io.Serializable;

public class JmsWorkerServiceProviderImpl<T extends Serializable> implements JmsWorkerServiceProvider<T> {

    @Override
    public JmsPatientWorker<T> createJmsPatientWorker(EntityServiceManager esm) {
        return new JmsPatientWorker<>(esm);
    }

    @Override
    public JmsDiseaseWorker<T> createJmsDiseaseWorker(EntityServiceManager esm) {
        return new JmsDiseaseWorker<>(esm);
    }

    @Override
    public JmsContactWorker<T> createJmsContactWorker(EntityServiceManager esm) {
        return new JmsContactWorker<>(esm);
    }

    @Override
    public JmsDiagnoseWorker<T> createJmsDiagnoseWorker(EntityServiceManager esm) {
        return new JmsDiagnoseWorker<>(esm);
    }

    @Override
    public JmsMedicalRecordWorker<T> createJmsMedicalRecordWorker(EntityServiceManager esm) {
        return new JmsMedicalRecordWorker<>(esm);
    }

    @Override
    public JmsPatient_DiagnoseWorker<T> createJmsPatient_DiagnoseWorker(EntityServiceManager esm) {
        return new JmsPatient_DiagnoseWorker<>(esm);
    }
}
