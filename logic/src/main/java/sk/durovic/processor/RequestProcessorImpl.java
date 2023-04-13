package sk.durovic.processor;

import sk.durovic.actions.DefaultAction;
import sk.durovic.actions.RequestAction;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.result.EntityResult;
import sk.durovic.result.Result;
import sk.durovic.service.EntityService;

import java.util.Collection;

public abstract class RequestProcessorImpl<T extends BaseEntityAbstractClass<ID>,ID> implements RequestProcessor{

    private final RequestAction<T,ID> requestAction;

    protected RequestProcessorImpl(EntityService<T,ID,?> service){
        this.requestAction = new DefaultAction<>(service);
    }
    protected RequestProcessorImpl(RequestAction<T, ID> requestAction) {
        this.requestAction = requestAction;
    }

    @Override
        public Result<T> process(Event event) {
            EventAction action = event.getAction();
            Result<T> result = new EntityResult<>();
            Collection<T> entities = event.getEntities();

            switch (action){
                case GET:
                    result.setEntities(getAction(entities));
                    break;
                case POST:
                    result.setEntities(postAction(entities));
                    break;
                case PUT:
                    result.setEntities(putAction(entities));
                    break;
                case DELETE:
                    deleteAction(entities);
                    break;
            }
            return result;
        }

    protected Collection<T> getAction(Collection<T> entities){
        return requestAction.get(entities);
    }

    protected Collection<T> postAction(Collection<T> entities){
        return requestAction.post(entities);
    }

    protected Collection<T> putAction(Collection<T> entities){
        return requestAction.put(entities);
    }

    protected void deleteAction(Collection<T> entities){
        requestAction.delete(entities);
    }
}
