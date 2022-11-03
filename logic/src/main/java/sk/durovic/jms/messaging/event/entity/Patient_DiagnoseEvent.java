package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Patient_Diagnose;

public class Patient_DiagnoseEvent extends EntityEvent<Patient_Diagnose> {

    private void setPatient_Diagnose(Patient_Diagnose patient_diagnose){
        this.entity = patient_diagnose;
    }
}
