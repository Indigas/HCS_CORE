package sk.durovic.jms.events.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Patient;

import java.io.Serializable;

public class PatientEvent extends EntityEvent<Patient> implements Serializable {

    private void setPatient(Patient patient){
        this.entity = patient;
    }
}
