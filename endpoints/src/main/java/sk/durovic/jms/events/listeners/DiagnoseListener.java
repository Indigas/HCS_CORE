package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.model.Diagnose;

import javax.jms.Message;

@Service
@Slf4j
public class DiagnoseListener extends EntityListener<Diagnose> {

    public static final String DIAGNOSE_QUEUE="DIAGNOSE_QUEUE";
    public DiagnoseListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @JmsListener(destination = DIAGNOSE_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for DIAGNOSE_QUEUE");

//        processMessage(msg, DiagnoseEvent.class);
    }

}
