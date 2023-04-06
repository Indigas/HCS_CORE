package sk.durovic.jms.events.processor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Setter
public class JmsMessageProcessor<T> {

    private JmsTemplate jmsTemplate;

    public JmsMessageProcessor() {
    }

//    public void processMessage(Message msg, Class<? extends EntityEvent<T>> clazz){
//        Event<T> event = JmsMessage2Event.convertMsg2Event(msg, clazz);
//        WorkerResult<?> result = worker.processEvent(event);
//
//        try {
//            if (event.isResultOk() && msg.getJMSReplyTo() != null)
//                jmsTemplate.convertAndSend(msg.getJMSReplyTo(), result);
//
//        } catch (JMSException e){
//            log.error("JMS exception", e);
//        }
        // not necessary to use runnable - JMS listener has concurrency configured
        /*workerService.processMessage(new Runnable() {
            @Override
            public void run() {
                Event<T> event = JmsMessage2Event.convertMsg2Event(msg, clazz);
                WorkerResult<?> result = worker.processEvent(event);

                try {
                    if (event.isResultOk() && msg.getJMSReplyTo() != null)
                        jmsTemplate.convertAndSend(msg.getJMSReplyTo(), result);

                } catch (JMSException e){
                    log.error("JMS exception", e);
                }
            }
        });*/

//    }

}
