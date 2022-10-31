package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.model.Patient;

import java.io.Serializable;

public class PatientEvent extends Event<Patient> implements Serializable {

    private void setPatient(Patient patient){
        this.entity = patient;
    }
}
