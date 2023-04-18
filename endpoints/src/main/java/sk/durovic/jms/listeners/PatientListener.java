package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.PatientDto;
import sk.durovic.model.Patient;
import sk.durovic.processor.JmsRequestProcessor;
import sk.durovic.service.PatientEntityService;

import javax.jms.Message;


@Service
@Slf4j
public class PatientListener extends EntityListener {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";

    public PatientListener(JmsTemplate jmsTemplate, PatientEntityService service) {
        super(jmsTemplate, new JmsRequestProcessor<>(service));
    }

    @JmsListener(destination = PATIENT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg){
        log.info("Processing JMS message for "+ PATIENT_QUEUE);

        processMessage(msg, PatientDto.class);

    }

}
