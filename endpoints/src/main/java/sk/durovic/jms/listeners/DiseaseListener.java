package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.DiseaseDto;
import sk.durovic.jms.events.entity.DiseaseEvent;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.jms.messaging.worker.service.JmsDiseaseWorker;
import sk.durovic.manager.service.EntityServiceManager;

import javax.jms.Message;

@Service
@Slf4j
public class DiseaseListener extends EntityListener<DiseaseDto> {

    public DiseaseListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.serviceProvider().createJmsDiseaseWorker(ems));
    }

    @JmsListener(destination = JmsDiseaseWorker.DISEASE_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for DISEASE_QUEUE");

        processMessage(msg, DiseaseEvent.class);
    }

}
