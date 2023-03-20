package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.DiagnoseDto;
import sk.durovic.jms.events.entity.DiagnoseEvent;
import sk.durovic.jms.messaging.worker.service.JmsDiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;

import javax.jms.Message;

@Service
@Slf4j
public class DiagnoseListener extends EntityListener<DiagnoseDto> {

    public DiagnoseListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.serviceProvider().createJmsDiagnoseWorker(ems));
    }

    @JmsListener(destination = JmsDiagnoseWorker.DIAGNOSE_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for DIAGNOSE_QUEUE");

        processMessage(msg, DiagnoseEvent.class);
    }

}
