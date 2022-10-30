package sk.durovic.jms.messaging.event;

import lombok.Data;
import sk.durovic.model.Patient;

import java.io.Serializable;

public class PatientEvent extends Event<Patient> {
    private void setPatient(Patient patient){
        this.entity = patient;
    }
}
