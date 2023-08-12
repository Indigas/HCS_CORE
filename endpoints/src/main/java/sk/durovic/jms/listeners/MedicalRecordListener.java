package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.processor.JmsRequestProcessor;
import sk.durovic.service.MedicalRecordEntityService;

import javax.jms.Message;

@Service
@Slf4j
public class MedicalRecordListener extends EntityListener {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";
    public MedicalRecordListener(JmsTemplate jmsTemplate, MedicalRecordEntityService service) {
        super(jmsTemplate, new JmsRequestProcessor<>(service));
    }

    @JmsListener(destination = MEDIACAL_RECORD_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for "+MEDIACAL_RECORD_QUEUE);

        processMessage(msg, MedicalRecordDto.class);
    }

}
