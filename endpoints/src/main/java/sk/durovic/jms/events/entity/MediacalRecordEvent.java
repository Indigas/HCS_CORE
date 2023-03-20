package sk.durovic.jms.events.entity;

import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.MedicalRecord;

public class MediacalRecordEvent extends EntityEvent<MedicalRecordDto> {

    private void setMediacalRecord(MedicalRecordDto mediacalRecord){
        this.entity = mediacalRecord;
    }
}
