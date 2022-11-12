package sk.durovic.jms.events.worker;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.worker.JmsWorkerService;

import javax.jms.Destination;

@Slf4j
@Setter
public class JmsMessageProcessor<T> {

    private JmsTemplate jmsTemplate;
    private JmsWorkerService workerService;
    private JmsMessageWorker<T> worker;

    public JmsMessageProcessor() {
    }

    public void processMessage(Event<T> event){
        JmsProcessMessageExecutor executor = new JmsProcessMessageExecutor(event);
        workerService.processMessage(executor);
    }

    public void processMessageAndReply(Event<T> event, Destination destination){
        JmsProcessMessageExecutor executor = new JmsProcessMessageExecutor(event);
        executor.setDestination(destination);
        workerService.processMessage(executor);
    }

    private class JmsProcessMessageExecutor implements Runnable{
        private final Event<T> event;
        private Destination destination;

        JmsProcessMessageExecutor(Event<T> event) {
            this.event = event;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        @Override
        public void run() {
            // call JMS task // get result
            log.info("JMS message processing with event > {}", event);

            if(destination==null) {
                worker.processMessage(event);
            } else {
                WorkerResult<T> result = worker.processMessageWithReply(event);

                // create message and send if needed
                jmsTemplate.convertAndSend(destination, result);
                // log operation and result
                log.info("JMS message processed with result > {}", result);
            }



        }
    }

}
