package sk.durovic.jms.events.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.Patient_DiagnoseEvent;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsPatient_DiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.Message;
import java.util.Optional;

@Service
public class Patient_DiagnoseListener implements EntityListener {


    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        JmsMessageWorker worker = JmsWorker.provider().createJmsPatient_DiagnoseWorker();

        Optional<Event<Patient_Diagnose>> result = JmsMessage2Event.convertMsg2Event(msg, Patient_DiagnoseEvent.class);

        result.ifPresent(patient_diagnoseEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, patient_diagnoseEvent));
    }
}
