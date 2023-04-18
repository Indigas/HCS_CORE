package sk.durovic.actions;

import sk.durovic.exceptions.IllegalRequest;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;

import java.util.Collection;

public class ViewModelEntityRequestAction<T extends BaseEntityAbstractClass<ID>,ID> extends DefaultRequestAction<T,ID> {

    public ViewModelEntityRequestAction(EntityService<T, ID, ?> service) {
        super(service);
    }

    @Override
    public Collection<T> post(Collection<T> entities) {
        throw new IllegalRequest("Action not allowed");
    }

    @Override
    public Collection<T> put(Collection<T> entities) {
        throw new IllegalRequest("Action not allowed");
    }

    @Override
    public void delete(Collection<T> entities) {
        throw new IllegalRequest("Action not allowed");
    }
}
