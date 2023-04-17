package sk.durovic.actions;

import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;

import java.util.Collection;

public class ViewModelEntityAction<T extends BaseEntityAbstractClass<ID>,ID> extends DefaultAction<T,ID>{

    public ViewModelEntityAction(EntityService<T, ID, ?> service) {
        super(service);
    }

    @Override
    public Collection<T> post(Collection<T> entities) {
        throw new RuntimeException("Action not allowed");
    }

    @Override
    public Collection<T> put(Collection<T> entities) {
        throw new RuntimeException("Action not allowed");
    }

    @Override
    public void delete(Collection<T> entities) {
        throw new RuntimeException("Action not allowed");
    }
}
