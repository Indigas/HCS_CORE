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
import sk.durovic.jms.events.PatientListener;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import static org.hamcrest.MatcherAssert.*;

@SpringBootTest
@EnableJms
public class receiveJmsPatientMessageTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @SpyBean
    private PatientListener listener;

    private static final String json = "{\"patient\":{\"id\":\"ABC\",\"firstName\":\"Marek\",\"lastName\":\"Durovic\",\"email\":\"marek@gmail\"}}";

    @Test
    @Disabled
    void receivePatientEvent() throws JMSException {

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        jmsTemplate.send(JmsPatientWorker.PATIENT, msg);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener, Mockito.timeout(1000)).receiveMessage(messageCaptor.capture());

        Message receivedMessage = messageCaptor.getValue();
        assertThat(receivedMessage.getJMSMessageID(), Matchers.notNullValue());
    }

}
