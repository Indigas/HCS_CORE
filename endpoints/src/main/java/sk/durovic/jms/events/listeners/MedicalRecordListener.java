package sk.durovic.jms.events.listeners;

import org.springframework.jms.annotation.JmsListener;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.implementations.JmsMedicalRecordWorker;

import javax.jms.Message;

public class MedicalRecordListener implements EntityListener {

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
