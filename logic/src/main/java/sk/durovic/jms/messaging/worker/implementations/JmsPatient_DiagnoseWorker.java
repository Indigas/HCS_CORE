package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.model.Patient_Diagnose;


@Slf4j
public class JmsPatient_DiagnoseWorker implements JmsMessageWorker<Patient_Diagnose> {

    public static final String Patient_Diagnose_QUEUE = "Patient_Diagnose_QUEUE";
    public static final String Patient_Diagnose_WITH_REPLY_QUEUE = "Patient_Diagnose_WITH_REPLY_QUEUE";

    @Override
    public void processMessage(Event<Patient_Diagnose> message) {

    }

    @Override
    public WorkerResult<Patient_Diagnose> processMessageWithReply(Event<Patient_Diagnose> message) {
        return null;
    }
}

