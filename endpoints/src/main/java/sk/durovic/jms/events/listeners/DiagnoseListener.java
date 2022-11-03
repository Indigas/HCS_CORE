package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsDiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Diagnose;

import javax.jms.Message;

@Service
@Slf4j
public class DiagnoseListener extends EntityListener<Diagnose> {

    protected DiagnoseListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate, JmsWorker.provider().createJmsDiagnoseWorker());
    }

    @JmsListener(destination = JmsDiagnoseWorker.DIAGNOSE_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
