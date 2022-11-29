package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.service.PatientDiagnoseService;
import sk.durovic.jms.messaging.actions.JmsEntityAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static sk.durovic.jms.messaging.actions.JmsEntityAction.GET;


@Slf4j
public class JmsPatient_DiagnoseWorker extends JmsMessageWorkerService<Patient_Diagnose> {

    public static final String Patient_Diagnose_QUEUE = "Patient_Diagnose_QUEUE";

    public JmsPatient_DiagnoseWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<List<Patient_Diagnose>> processEvent(Event<?> event) {
        log.info("Started processing JMS message");
        PatientDiagnoseService service = (PatientDiagnoseService) getService();
        JmsEntityAction action = (JmsEntityAction) event.getAction();
        WorkerResult<List<Patient_Diagnose>> result = new EntityWorkerResult<>();

        if(action == GET){
            List<Patient_Diagnose> diagnoses = new ArrayList<>(service.getDiagnosesByPatientId(((Patient_Diagnose) event.getEntity()).getId()));
            result.setEntity(diagnoses);
            result.setStatus(WorkerStatusResult.OK);

            return result;
        }

        result.setStatus(WorkerStatusResult.ERROR.setMessage("No action processed"));
        return result;
    }

}

