package sk.durovic.jms.events.entity;

import sk.durovic.dto.Patient_DiagnoseDTO;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Patient_Diagnose;

public class Patient_DiagnoseEvent extends EntityEvent<Patient_DiagnoseDTO> {

    private void setPatient_Diagnose(Patient_DiagnoseDTO patient_diagnose){
        this.entity = patient_diagnose;
    }
}
