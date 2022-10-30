package sk.durovic.jms.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.PatientEvent;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Patient;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class PatientListener {

    @JmsListener(destination = JmsPatientWorker.PATIENT)
    public void receiveMessage(Message msg){
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatientWorker();

        Optional<Event<Patient>> result = JmsMessage2Event.convertMsg2Event(msg, PatientEvent.class);

        result.ifPresent(patientEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, patientEvent.getEntity()));

    }
}
