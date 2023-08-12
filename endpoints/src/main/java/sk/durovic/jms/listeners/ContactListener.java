package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.ContactDto;
import sk.durovic.processor.JmsRequestProcessor;
import sk.durovic.service.ContactEntityService;

import javax.jms.Message;

@Service
@Slf4j
public class ContactListener extends EntityListener {

    public static final String CONTACT_QUEUE="CONTACT_QUEUE";
    public ContactListener(JmsTemplate jmsTemplate, ContactEntityService service) {
        super(jmsTemplate, new JmsRequestProcessor<>(service));
    }

    @JmsListener(destination = CONTACT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Processing JMS message for "+ CONTACT_QUEUE);

        processMessage(msg, ContactDto.class);
    }
}
