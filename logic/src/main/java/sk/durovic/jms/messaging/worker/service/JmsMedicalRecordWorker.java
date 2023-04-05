package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.MedicalRecord;
import sk.durovic.model.access.MedicalRecordEntity;

@Slf4j
public class JmsMedicalRecordWorker extends JmsEntityServiceWorker<MedicalRecord, Long> {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";


    public JmsMedicalRecordWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    MedicalRecord updateEntity(MedicalRecord source, MedicalRecord dest) {
        MedicalRecordEntity entity = new MedicalRecordEntity(dest);

        return null;
    }
}
