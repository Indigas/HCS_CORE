package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.model.MedicalRecord;

public class MediacalRecordEvent extends Event<MedicalRecord> {

    private void setMediacalRecord(MedicalRecord mediacalRecord){
        this.entity = mediacalRecord;
    }
}
