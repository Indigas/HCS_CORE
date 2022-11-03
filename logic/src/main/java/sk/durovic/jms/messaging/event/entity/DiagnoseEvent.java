package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Diagnose;

public class DiagnoseEvent extends EntityEvent<Diagnose> {

    private void setDiagnose(Diagnose diagnose){
        this.entity = diagnose;
    }
}
