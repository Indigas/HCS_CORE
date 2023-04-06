package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.model.Disease;

import javax.jms.Message;

@Service
@Slf4j
public class DiseaseListener extends EntityListener<Disease> {

    public static final String DISEASE_QUEUE="DISEASE_QUEUE";
    public DiseaseListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @JmsListener(destination = DISEASE_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for DISEASE_QUEUE");

//        processMessage(msg, DiseaseEvent.class);
    }

}
