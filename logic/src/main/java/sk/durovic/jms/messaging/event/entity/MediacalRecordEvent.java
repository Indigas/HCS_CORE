package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.MedicalRecord;

public class MediacalRecordEvent extends EntityEvent<MedicalRecord> {

    private void setMediacalRecord(MedicalRecord mediacalRecord){
        this.entity = mediacalRecord;
    }
}
