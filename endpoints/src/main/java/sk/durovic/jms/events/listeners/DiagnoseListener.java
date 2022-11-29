package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.entity.DiagnoseEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsDiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Diagnose;

import javax.jms.Message;

@Service
@Slf4j
public class DiagnoseListener extends EntityListener<Diagnose> {

    public DiagnoseListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.provider().createJmsDiagnoseWorker(ems));
    }

    @JmsListener(destination = JmsDiagnoseWorker.DIAGNOSE_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for DIAGNOSE_QUEUE");

        defaultMessageProcessing(msg, DiagnoseEvent.class);
    }

}
