package sk.durovic.jms.events;

import jms.messaging.event.PatientEvent;
import jms.messaging.worker.JmsMessageWorker;
import jms.messaging.worker.implementations.JmsPatientWorker;
import jms.messaging.worker.provider.utility.JmsWorker;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PatientListener {

    @JmsListener(destination = JmsPatientWorker.PATIENT)
    public void receiveMessage(@Payload PatientEvent event, @Headers MessageHeaders headers){
        // ziskat spravneho workera z poolu a spustit v inom threade
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                worker.processMessage(event);
            }
        });

    }
}
