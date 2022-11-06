package sk.durovic.jms.messaging.worker.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;

public class EntityWorkerResult<T> implements WorkerResult<T>{

    private T entity;
    private WorkerStatusResult result;

    @Override
    public void setStatus(WorkerStatusResult status) {
        result = status;
    }

    @Override
    public WorkerStatusResult getStatus() {
        return result;
    }

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
    }

    @JsonIgnore
    @Override
    public boolean isResultOk() {
        return result == WorkerStatusResult.OK;
    }
}
