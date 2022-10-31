package sk.durovic.jms.events.listeners;

import org.hamcrest.MatcherAssert;
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
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@SpringBootTest
@EnableJms
class ContactListenerTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @SpyBean
    private ContactListener listener;

    private static final String json = "{\"contact\":{\"id\":\"13568\",\"fullName\":\"Marek\",\"telephone\":\"0908\",\"notes\":\"dad\"}}";

    @Test
    @Disabled
    void receiveMessage() {

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        jmsTemplate.send(JmsContactWorker.CONTACT_QUEUE, msg);

        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener, Mockito.timeout(1000)).receiveMessage(argumentCaptor.capture());

        Message receivedMsg = argumentCaptor.getValue();
        MatcherAssert.assertThat(receivedMsg, Matchers.notNullValue());
    }
}