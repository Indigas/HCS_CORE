package sk.durovic.jms.messaging.event;

import sk.durovic.model.Diagnose;

public class DiagnoseEvent extends Event<Diagnose> {

    private void setDiagnose(Diagnose diagnose){
        this.entity = diagnose;
    }
}
