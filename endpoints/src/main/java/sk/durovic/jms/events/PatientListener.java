package sk.durovic.jms.events;

import jms.messaging.worker.implementations.JmsPatientWorker;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PatientEvent {

    @JmsListener(destination = JmsPatientWorker.PATIENT)
    public void receiveMessage(@Payload PatientEvent event, @Headers MessageHeaders headers){
        // ziskat spravneho workera z poolu a spustit v inom threade

    }
}
