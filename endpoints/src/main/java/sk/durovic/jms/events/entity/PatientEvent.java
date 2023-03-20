package sk.durovic.jms.events.entity;

import sk.durovic.dto.PatientDto;
import sk.durovic.jms.messaging.event.EntityEvent;

public class PatientEvent extends EntityEvent<PatientDto> {

    private void setPatient(PatientDto patient){
        this.entity = patient;
    }
}
