package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.DiagnoseEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsDiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Diagnose;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.Message;
import java.util.Optional;

@Service
@Slf4j
public class DiagnoseListener implements EntityListener {

    @JmsListener(destination = JmsDiagnoseWorker.DIAGNOSE_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        JmsMessageWorker worker = JmsWorker.provider().createJmsDiagnoseWorker();

        Optional<Event<Diagnose>> result = JmsMessage2Event.convertMsg2Event(msg, DiagnoseEvent.class);

        result.ifPresent(diagnoseEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, diagnoseEvent));
    }
}
