package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.implementations.*;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerProvider;
import sk.durovic.manager.EntityManager;

public class JmsWorkerProviderImpl implements JmsWorkerProvider {

    @Override
    public JmsPatientWorker createJmsPatientWorker(EntityManager em) {
        return new JmsPatientWorker(em);
    }

    @Override
    public JmsDiseaseWorker createJmsDiseaseWorker(EntityManager em) {
        return new JmsDiseaseWorker(em);
    }

    @Override
    public JmsContactWorker createJmsContactWorker(EntityManager em) {
        return new JmsContactWorker(em);
    }

    @Override
    public JmsDiagnoseWorker createJmsDiagnoseWorker(EntityManager em) {
        return new JmsDiagnoseWorker(em);
    }

    @Override
    public JmsMedicalRecordWorker createJmsMedicalRecordWorker(EntityManager em) {
        return new JmsMedicalRecordWorker(em);
    }

    @Override
    public JmsPatient_DiagnoseWorker createJmsPatient_DiagnoseWorker(EntityManager em) {
        return new JmsPatient_DiagnoseWorker(em);
    }
}
