package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.entity.PatientEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient;

import javax.jms.JMSException;
import javax.jms.Message;


@Service
@Slf4j
public class PatientListener extends EntityListener<Patient> {

    public PatientListener(JmsTemplate jmsTemplate, EntityManagerFactory factory) {
        super(jmsTemplate, JmsWorker.provider().createJmsPatientWorker(factory.getBasicEntityManager()));
    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_QUEUE)
    @Override
    public void receiveMessage(Message msg){

        Event<Patient> result = JmsMessage2Event.convertMsg2Event(msg, PatientEvent.class);

        if(result.isResultOk())
            getMessageProcessor().processMessage(result);

    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {

        Event<Patient> result = JmsMessage2Event.convertMsg2Event(msg, PatientEvent.class);

        try {
            if (result.isResultOk())
                getMessageProcessor().processMessageAndReply(result, msg.getJMSReplyTo());
        } catch (JMSException e){
            log.error("JMS exception", e);
        }
    }

}
