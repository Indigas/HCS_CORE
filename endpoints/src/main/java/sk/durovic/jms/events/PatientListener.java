package sk.durovic.jms.events;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sk.durovic.worker.JmsWorkerTask;
import sk.durovic.jms.messaging.event.PatientEvent;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;

@Component
public class PatientListener {

    @JmsListener(destination = JmsPatientWorker.PATIENT)
    public void receiveMessage(@Payload PatientEvent event, @Headers MessageHeaders headers){
        // ziskat spravneho workera z poolu a spustit v inom threade
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

        JmsWorkerTask.processWithoutReply(worker::processMessage, event);

    }
}
