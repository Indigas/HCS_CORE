package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import sk.durovic.dto.PatientDto;
import sk.durovic.jms.listeners.PatientListener;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@EnableJms
public class receiveJmsPatientMessageTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private PatientRepository repository;

    @SpyBean
    private PatientListener listener;


    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String json = "{\"entities\":[{\"id\":\"D\",\"firstName\":\"Marek\",\"lastName\":\"Durovic\",\"email\":\"marek@gmail\"}],\"action\":\"GET\"}";

    @AfterEach
    private void removeEntities(){
        repository.deleteAll();
    }
    @Test
    @Disabled
    void receivePatientEvent() throws JMSException {

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        jmsTemplate.send(PatientListener.PATIENT_QUEUE, msg);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener, Mockito.after(2000)).receiveMessage(messageCaptor.capture());

        Message receivedMessage = messageCaptor.getValue();
        String body = receivedMessage.getBody(String.class);

        assertThat(receivedMessage.getJMSMessageID(), Matchers.notNullValue());
        MatcherAssert.assertThat(body, Matchers.is(json));
    }

    @Test
    void receiveAndReplyTest() throws JMSException, JsonProcessingException {
        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        Message receivedMsg = jmsTemplate.sendAndReceive(PatientListener.PATIENT_QUEUE, msg);
        String body = receivedMsg.getBody(String.class);

        JsonNode jsonMsg = objectMapper.readTree(body);

        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, PatientDto.class);
        List<PatientDto> dtos = objectMapper.readValue(jsonMsg.get("entities").toString(), type);

        assertThat(dtos, Matchers.hasSize(0));
    }

    @Test
    void createEntityAndReplyTest() throws JMSException, JsonProcessingException {
        String jsonCreate = "{\"entities\":[{\"firstName\":\"Marek\",\"lastName\":\"Durovic\",\"email\":\"marek@gmail\"}],\"action\":\"POST\"}";
        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonCreate);
            }
        };

        Message receivedMsg = jmsTemplate.sendAndReceive(PatientListener.PATIENT_QUEUE, msg);
        String body = receivedMsg.getBody(String.class);

        JsonNode jsonMsg = objectMapper.readTree(body);

        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, PatientDto.class);
        List<PatientDto> dtos = objectMapper.readValue(jsonMsg.get("entities").toString(), type);

        assertThat(dtos, Matchers.hasSize(1));

        PatientDto dto = dtos.get(0);

        Patient patient = repository.findById(dto.getId()).orElse(new Patient());

        assertThat(patient, Matchers.notNullValue());
        assertThat(patient.getId(), Matchers.notNullValue());
        assertThat(patient.getFirstName(), Matchers.is(dto.getFirstName()));
        assertThat(patient.getLastName(), Matchers.is(dto.getLastName()));
        assertThat(patient.getEmail(), Matchers.is(dto.getEmail()));
    }

}
