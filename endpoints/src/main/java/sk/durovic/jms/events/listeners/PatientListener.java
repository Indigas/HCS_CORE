package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.entity.PatientEvent;
import sk.durovic.jms.messaging.event.result.EventStatusResult;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Patient;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.JMSException;
import javax.jms.Message;


@Service
@Slf4j
public class PatientListener implements EntityListener {

    private final JmsTemplate jmsTemplate;
    private final JmsMessageWorker worker;

    public PatientListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.worker = JmsWorker.provider().createJmsPatientWorker();
    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_QUEUE)
    @Override
    public void receiveMessage(Message msg){

        Event<Patient> result = JmsMessage2Event.convertMsg2Event(msg, PatientEvent.class);

        if(result.getResult().getStatus() == EventStatusResult.OK)
            JmsWorkerTask.processWithoutReply(worker::processMessage, result);

    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {

        Event<Patient> result = JmsMessage2Event.convertMsg2Event(msg, PatientEvent.class);
        Object messageToSend = new Object();

        if(result.isResultOk())
            messageToSend = JmsWorkerTask.processWithReply(worker::processMessageWithReply, result);


        try {
            jmsTemplate.convertAndSend(msg.getJMSReplyTo(), messageToSend);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
