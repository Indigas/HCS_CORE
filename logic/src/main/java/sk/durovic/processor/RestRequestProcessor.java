package sk.durovic.processor;

import sk.durovic.actions.RequestAction;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;

public class RestRequestProcessor<T extends BaseEntityAbstractClass<ID>,ID> extends RequestProcessorImpl<T,ID> {


    public RestRequestProcessor(EntityService<T, ID, ?> service) {
        super(service);
    }

    public RestRequestProcessor(RequestAction<T, ID> requestAction) {
        super(requestAction);
    }
}
