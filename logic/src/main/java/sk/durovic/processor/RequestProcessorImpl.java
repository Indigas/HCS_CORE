package sk.durovic.processor;

import sk.durovic.actions.DefaultAction;
import sk.durovic.actions.RequestAction;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.result.Result;
import sk.durovic.service.EntityService;

import java.util.Collection;

public abstract class RequestProcessorImpl<T,ID> implements RequestProcessor{

    private final RequestAction<T,ID> requestAction;

    protected RequestProcessorImpl(EntityService<T,ID,?> service){
        this.requestAction = new DefaultAction<>(service);
    }
    protected RequestProcessorImpl(RequestAction<T, ID> requestAction) {
        this.requestAction = requestAction;
    }

    @Override
        public Result process(Event event) {
            EventAction action = event.getAction();
            switch (action){
                case GET:
                    getAction();
                    break;
                case POST:
                    postAction();
                    break;
                case PUT:
                    putAction();
                    break;
                case DELETE:
                    deleteAction();
                    break;
            }
            return null;
        }

    protected Collection<T> getAction(){
        requestAction.get();
        return null;
    }

    protected T postAction(){
        return null;
    }

    protected void putAction(){}

    protected void deleteAction(){}
}
