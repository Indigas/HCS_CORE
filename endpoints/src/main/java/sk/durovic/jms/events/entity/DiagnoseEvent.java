package sk.durovic.jms.events.entity;

import sk.durovic.dto.DiagnoseDto;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Diagnose;

public class DiagnoseEvent extends EntityEvent<DiagnoseDto> {

    private void setDiagnose(DiagnoseDto diagnose){
        this.entity = diagnose;
    }
}
