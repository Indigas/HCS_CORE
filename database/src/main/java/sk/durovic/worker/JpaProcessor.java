package sk.durovic.worker;

import org.springframework.context.ApplicationContext;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;

/**
 * Used by worker to execute task
 */
public interface JpaProcessor {

    JpaProcessWorker getWorker();

    void initialize(List<? extends BaseEntityAbstractClass<?>> list, ApplicationContext context);
}
