package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.DiseaseEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsDiseaseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Disease;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.Message;
import java.util.Optional;

@Service
@Slf4j
public class DiseaseListener implements EntityListener {

    @JmsListener(destination = JmsDiseaseWorker.DISEASE_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        JmsMessageWorker worker = JmsWorker.provider().createJmsDiseaseWorker();

        Optional<Event<Disease>> result = JmsMessage2Event.convertMsg2Event(msg, DiseaseEvent.class);

        result.ifPresent(diseaseEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, diseaseEvent));
    }
}
