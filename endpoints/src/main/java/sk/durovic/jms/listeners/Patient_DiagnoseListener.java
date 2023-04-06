package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.processor.JmsRequestProcessor;
import sk.durovic.service.PatientDiagnoseService;

import javax.jms.Message;

@Service
@Slf4j
public class Patient_DiagnoseListener extends EntityListener<Patient_Diagnose> {

    public static final String Patient_Diagnose_QUEUE="Patient_Diagnose_QUEUE";
    public Patient_DiagnoseListener(JmsTemplate jmsTemplate, PatientDiagnoseService service) {
        super(jmsTemplate, new JmsRequestProcessor<>(service));
    }

    @JmsListener(destination = Patient_Diagnose_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for Patient_Diagnose_QUEUE");

//        processMessage(msg, Patient_DiagnoseEvent.class);
    }

}
