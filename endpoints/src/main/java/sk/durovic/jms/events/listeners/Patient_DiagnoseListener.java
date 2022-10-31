package sk.durovic.jms.events.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.implementations.JmsPatient_DiagnoseWorker;

import javax.jms.Message;

@Service
public class Patient_DiagnoseListener implements EntityListener {


    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
