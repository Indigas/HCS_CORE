package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.service.PatientEntityService;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
public class JmsPatientWorker<T extends Serializable> extends JmsMessageWorkerService<Patient,T> {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";


    public JmsPatientWorker(EntityServiceManager ems) {
       super(ems);
    }

    @Override
    public WorkerResult<T> processEvent(Event<T> event) {
        return null;
//        log.info("Started processing JMS message");
//        WorkerResult<Patient> result = new EntityWorkerResult<>();
//
//        if(!(event instanceof PatientEvent)) {
//            return EntityWorkerResult.createBadEventResult(event);
//        }
//
//        Patient patient = (Patient) event.getEntity();
//        JmsEntityAction action = (JmsEntityAction) event.getAction();
//        Optional<Patient> loadedPatient = Optional.empty();
//
//
//        PatientEntityService service = (PatientEntityService) getService();
//
//        switch (action){
//            case GET:
//                loadedPatient = service.load(patient.getId());
//                if(loadedPatient.isPresent()){
//                    result.setEntity(loadedPatient.get());
//                    result.setStatus(WorkerStatusResult.OK);
//                } else
//                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);
//
//                break;
//            case CREATE:
//                patient = service.save(patient);
//
//                result.setEntity(patient);
//                result.setStatus(WorkerStatusResult.OK);
//                break;
//            case UPDATE:
//                loadedPatient = service.load(patient.getId());
//
//                if (loadedPatient.isPresent()) {
//                    Patient updatedPatient = updatePatient(patient, loadedPatient.get());
//
//                    service.save(updatedPatient);
//
//                    result.setStatus(WorkerStatusResult.OK);
//                } else
//                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);
//
//                break;
//            case DELETE:
//                service.delete(patient);
//
//                result.setStatus(WorkerStatusResult.OK);
//                break;
//        }
//
//        return result;
    }

    private Patient updatePatient(Patient source, Patient dest){
        PatientEntity merged = new PatientEntity(dest);

        if(source.getEmail() != null && !source.getEmail().equals(merged.getEmail()))
            merged.setEmail(source.getEmail());

        if(source.getFirstName() != null && !source.getFirstName().equals(merged.getFirstName()))
            merged.setFirstName(source.getFirstName());

        if(source.getLastName() != null && !source.getLastName().equals(merged.getLastName()))
            merged.setLastName(source.getLastName());

        if(source.getBloodGroup() != null && source.getBloodGroup().compareTo(merged.getBloodGroup()) != 0)
            merged.setBloodGroup(source.getBloodGroup());

        return merged.createPatient();
    }

}
