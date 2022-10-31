package sk.durovic.jms.events.listeners;

import org.springframework.jms.annotation.JmsListener;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.MediacalRecordEvent;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsMedicalRecordWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.MedicalRecord;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.Message;
import java.util.Optional;

public class MedicalRecordListener implements EntityListener {

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        JmsMessageWorker worker = JmsWorker.provider().createJmsMedicalRecordWorker();

        Optional<Event<MedicalRecord>> result = JmsMessage2Event.convertMsg2Event(msg, MediacalRecordEvent.class);

        result.ifPresent(medicalRecordEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, medicalRecordEvent));
    }
}
