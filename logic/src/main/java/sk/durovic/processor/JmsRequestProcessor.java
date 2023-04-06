package sk.durovic.processor;

import sk.durovic.actions.DefaultAction;
import sk.durovic.actions.RequestAction;
import sk.durovic.service.EntityService;

public class JmsRequestProcessor<T,ID> extends RequestProcessorImpl<T,ID> {


    public JmsRequestProcessor(EntityService<T, ID, ?> service) {
        super(service);
    }

    public JmsRequestProcessor(RequestAction<T,ID> requestAction) {
        super(requestAction);
    }

}
