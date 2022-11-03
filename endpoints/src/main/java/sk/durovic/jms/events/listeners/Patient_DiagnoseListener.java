package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsPatient_DiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Patient_Diagnose;

import javax.jms.Message;

@Service
@Slf4j
public class Patient_DiagnoseListener extends EntityListener<Patient_Diagnose> {

    public Patient_DiagnoseListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate, JmsWorker.provider().createJmsPatient_DiagnoseWorker());
    }

    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
