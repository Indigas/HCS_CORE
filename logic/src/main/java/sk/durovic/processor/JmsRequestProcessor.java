package sk.durovic.processor;

import sk.durovic.actions.RequestAction;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;

public class JmsRequestProcessor<T extends BaseEntityAbstractClass<ID>,ID> extends RequestProcessorImpl<T,ID> {


    public JmsRequestProcessor(EntityService<T, ID, ?> service) {
        super(service);
    }

    public JmsRequestProcessor(RequestAction<T> requestAction) {
        super(requestAction);
    }

}
