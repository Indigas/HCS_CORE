package sk.durovic.jms.messaging.worker.result;

import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;

public interface WorkerResult<T> {
    void setStatus(WorkerStatusResult status);
    WorkerStatusResult getStatus();
    T getEntity();
    void setEntity(T entity);
    boolean isResultOk();
}
