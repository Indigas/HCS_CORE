package sk.durovic.manager;

import org.springframework.context.ApplicationContext;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.JpaProcessor;

import java.util.Optional;

public interface EntityContainer {

    <T extends BaseEntityAbstractClass<?>> Optional<T> onLoad(T entity);
    <T extends BaseEntityAbstractClass<?>> void onSave(T entity);
    <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity);
    <T extends BaseEntityAbstractClass<?>> boolean onRefresh(T entity);
    <T extends BaseEntityAbstractClass<?>> void onRemove(T entity);
    <T extends BaseEntityAbstractClass<?>> void onLock(T entity);
    <T extends BaseEntityAbstractClass<?>> void onRelease(T entity);
    <T extends BaseEntityAbstractClass<?>> boolean contains(T entity);
    JpaProcessor[] onFlush(ApplicationContext context);
    void clear();
}
