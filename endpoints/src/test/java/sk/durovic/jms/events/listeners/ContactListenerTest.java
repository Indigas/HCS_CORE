package sk.durovic.jms.events.listeners;

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
import sk.durovic.jms.listeners.ContactListener;
import sk.durovic.repository.ContactRepository;

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

    @Autowired
    private ContactRepository repo;

    private static final String json = "{\"entities\":[{\"id\":\"13568\",\"fullName\":\"Marek\",\"telephone\":\"0908\",\"notes\":\"dad\"}],\"action\":\"GET\"}";

    @AfterEach
    public void removeEntities() throws InterruptedException {
        repo.deleteAll();
    }
    @Test
    @Disabled
    void receiveMessage() throws InterruptedException, JMSException {
        final String json2 = "{\"entities\":[{\"id\":\"135\",\"fullName\":\"Marek\",\"telephone\":\"0908\",\"notes\":\"dad\"}],\"action\":\"GET\"}";

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json2);
            }
        };

        jmsTemplate.send(ContactListener.CONTACT_QUEUE, msg);

        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener).receiveMessage(argumentCaptor.capture());

        Message receivedMsg = argumentCaptor.getValue();
        String body = receivedMsg.getBody(String.class);

        MatcherAssert.assertThat(receivedMsg, Matchers.notNullValue());
        MatcherAssert.assertThat(body, Matchers.is(json2));
    }

    @Test
    @Disabled
    void receiveMessage2() throws InterruptedException, JMSException {

        MessageCreator msg = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        };

        jmsTemplate.send(ContactListener.CONTACT_QUEUE, msg);

        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(listener, Mockito.timeout(1000)).receiveMessage(argumentCaptor.capture());

        Message receivedMsg = argumentCaptor.getValue();
        String body = receivedMsg.getBody(String.class);

        MatcherAssert.assertThat(receivedMsg, Matchers.notNullValue());
        MatcherAssert.assertThat(body, Matchers.is(json));
    }
}