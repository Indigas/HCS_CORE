package sk.durovic.jms.messaging.event;

import sk.durovic.model.Patient;

public class PatientEvent extends Event<Patient> {
    private void setPatient(Patient patient){
        this.entity = patient;
    }
}
