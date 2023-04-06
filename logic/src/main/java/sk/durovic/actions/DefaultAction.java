package sk.durovic.actions;

import sk.durovic.service.EntityService;

public class DefaultAction<T, ID> implements RequestAction<T,ID>{

    private final EntityService<T,ID,?> service;

    public DefaultAction(EntityService<T, ID, ?> service) {
        this.service = service;
    }

    @Override
    public void get() {

    }

    @Override
    public void post() {

    }

    @Override
    public void put() {

    }

    @Override
    public void delete() {

    }
}
