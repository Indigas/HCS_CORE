package sk.durovic.worker;

import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;

public interface JpaProcessor {
    JpaProcessWorker getWorker();
    void initialize(List<? extends BaseEntityAbstractClass<?>> list, boolean clearContainer);
}
