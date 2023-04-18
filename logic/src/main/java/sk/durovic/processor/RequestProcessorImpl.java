package sk.durovic.processor;

import sk.durovic.actions.DefaultRequestAction;
import sk.durovic.actions.RequestAction;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityConverter;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.result.EntityResult;
import sk.durovic.result.Result;
import sk.durovic.service.EntityService;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class RequestProcessorImpl<T extends BaseEntityAbstractClass<ID>,ID> implements RequestProcessor{

    private final RequestAction<T,ID> requestAction;

    protected RequestProcessorImpl(EntityService<T,ID,?> service){
        this.requestAction = new DefaultRequestAction<>(service);
    }
    protected RequestProcessorImpl(RequestAction<T, ID> requestAction) {
        this.requestAction = requestAction;
    }

    @Override
    public Result process(Event event) {
        EventAction action = event.getAction();
        Result result = new EntityResult();
        Collection<T> entities = event.getEntities();

        switch (action){
                case GET:
                    result.setEntities(convertToDtos(getAction(entities)));
                    break;
                case POST:
                    result.setEntities(convertToDtos(postAction(entities)));
                    break;
                case PUT:
                    result.setEntities(convertToDtos(putAction(entities)));
                    break;
                case DELETE:
                    deleteAction(entities);
                    break;
        }
        return result;
    }
    protected Collection<?> convertToDtos(Collection<T> entities) {
        if(!entities.iterator().hasNext())
            return Collections.EMPTY_LIST;

        T obj = entities.iterator().next();
        EntityConverter<?, T> converter = EntityMapperHelper.getConverter(obj.getClass());
        return entities.stream().map(converter::convert2Dto).collect(Collectors.toList());

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
