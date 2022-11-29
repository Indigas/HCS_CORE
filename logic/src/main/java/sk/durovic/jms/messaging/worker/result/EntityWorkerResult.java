package sk.durovic.jms.messaging.worker.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;

public class EntityWorkerResult<T> implements WorkerResult<T>{

    private T entity;
    private WorkerStatusResult result;

    public static <R> WorkerResult<R> createBadEventResult(Event<?> event){
        WorkerResult<R> result = new EntityWorkerResult<>();
        WorkerStatusResult status = WorkerStatusResult.BAD_EVENT;
        status.setMessage("Wrong queue for this event = " + event.getClass().getSimpleName());
        result.setStatus(status);
        return result;
    }

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
