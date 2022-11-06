package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;
import sk.durovic.manager.EntityManager;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import java.util.Optional;

@Slf4j
public class JmsPatientWorker implements JmsMessageWorker<Patient> {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";
    public static final String PATIENT_WITH_REPLY_QUEUE = "PATIENT_WITH_REPLY_QUEUE";

    private final EntityManager em;

    public JmsPatientWorker(EntityManager em) {
        this.em = em;
    }

    @Override
    public void processMessage(Event<Patient> message) {
        log.info("Started processing JMS message");

    }

    @Override
    public WorkerResult<Patient> processMessageWithReply(Event<Patient> message) {
        log.info("Started processing JMS message");

        Patient patient = message.getEntity();
        JmsEntityAction action = (JmsEntityAction) message.getAction();
        Optional<Patient> loadedPatient = Optional.empty();
        WorkerResult<Patient> result = new EntityWorkerResult<>();


        switch (action){
            case GET:
                loadedPatient = em.load(Patient.class, patient.getId());
                if(loadedPatient.isPresent()){
                    result.setEntity(loadedPatient.get());
                    result.setStatus(WorkerStatusResult.OK);
                }
                break;
            case CREATE:
                em.save(patient);
                em.commit();

                result.setStatus(WorkerStatusResult.MESSAGE_RECEIVED);
                break;
            case UPDATE:
                // load from DB - change only fields, which are not null
                loadedPatient = em.load(Patient.class, patient.getId());

                if (loadedPatient.isPresent()) {
                    Patient updatedPatient = updatePatient(patient, loadedPatient.get());
                    em.save(updatedPatient);
                    em.commit();

                    result.setStatus(WorkerStatusResult.MESSAGE_RECEIVED);
                } else
                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);

                break;
            case DELETE:
                em.remove(patient);
                em.commit();

                result.setStatus(WorkerStatusResult.MESSAGE_RECEIVED);
                break;
        }

        return result;
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
