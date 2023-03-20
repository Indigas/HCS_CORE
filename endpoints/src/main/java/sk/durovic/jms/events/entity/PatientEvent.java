package sk.durovic.jms.events.entity;

import sk.durovic.dto.PatientDto;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Patient;

import java.io.Serializable;

public class PatientEvent extends EntityEvent<PatientDto> {

    private void setPatient(PatientDto patient){
        this.entity = patient;
    }
}
