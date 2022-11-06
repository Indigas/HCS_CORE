package sk.durovic.jms;

import org.hamcrest.Matchers;
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
import org.springframework.transaction.annotation.Transactional;
import sk.durovic.jms.events.listeners.PatientListener;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@EnableJms
public class receiveJmsPatientMessageTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @SpyBean
    private PatientListener listener;

    private static final String json = "{\"patient\":{\"id\":\"D\",\"firstName\":\"Marek\",\"lastName\":\"Durovic\",\"email\":\"marek@gmail\"},\"action\":\"GET\"}";

    @Test
    void receivePatientEvent() throws JMSException {

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        jmsTemplate.send(JmsPatientWorker.PATIENT_QUEUE, msg);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener, Mockito.timeout(1000)).receiveMessage(messageCaptor.capture());

        Message receivedMessage = messageCaptor.getValue();
        assertThat(receivedMessage.getJMSMessageID(), Matchers.notNullValue());
    }

    @Test
    void receiveAndReplyTest() throws JMSException {
        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsPatientWorker.PATIENT_WITH_REPLY_QUEUE, msg);

        System.out.println(receivedMsg.getBody(String.class).toString());
    }

    @Test
    void createEntityAndReplyTest() throws JMSException {
        String jsonCreate = "{\"patient\":{\"id\":\"D\",\"firstName\":\"Marek\",\"lastName\":\"Durovic\",\"email\":\"marek@gmail\"},\"action\":\"CREATE\"}";
        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonCreate);
            }
        };

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsPatientWorker.PATIENT_WITH_REPLY_QUEUE, msg);

        System.out.println(receivedMsg.getBody(String.class).toString());
    }

}
