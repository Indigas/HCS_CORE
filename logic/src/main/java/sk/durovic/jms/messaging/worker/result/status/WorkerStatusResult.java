package sk.durovic.jms.messaging.worker.result.status;

public enum WorkerStatusResult {
    OK,
    ERROR,
    NO_STATUS,
    MESSAGE_RECEIVED,
    ENTITY_NOT_EXISTS,
    BAD_EVENT;

    private String message;

    public String getMessage() {
        return message;
    }

    public WorkerStatusResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
