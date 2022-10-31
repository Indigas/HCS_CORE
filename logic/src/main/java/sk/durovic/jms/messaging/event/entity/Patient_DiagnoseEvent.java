package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.model.Patient_Diagnose;

public class Patient_DiagnoseEvent extends Event<Patient_Diagnose> {

    private void setPatient_Diagnose(Patient_Diagnose patient_diagnose){
        this.entity = patient_diagnose;
    }
}
