package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.entity.MediacalRecordEvent;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.MedicalRecord;
import sk.durovic.model.Patient;
import sk.durovic.model.access.MedicalRecordEntity;
import sk.durovic.service.MedicalRecordEntityService;

import java.util.Optional;

@Slf4j
public class JmsMedicalRecordWorker extends JmsEntityWorker<MedicalRecord, MedicalRecordEntityService> {

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
