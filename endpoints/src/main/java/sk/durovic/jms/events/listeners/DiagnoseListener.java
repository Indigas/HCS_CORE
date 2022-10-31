package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.implementations.JmsDiagnoseWorker;

import javax.jms.Message;

@Service
@Slf4j
public class DiagnoseListener implements EntityListener {

    @JmsListener(destination = JmsDiagnoseWorker.DIAGNOSE_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
