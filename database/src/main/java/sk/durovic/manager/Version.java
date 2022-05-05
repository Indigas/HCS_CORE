package sk.durovic.manager;

public class Version {

    public enum Status {
        // Entity can be fetched from container.
        // Entity will be tracked with version.
        OPTIMISTIC_LOCK,

        // Entity cannot be fetched from container.
        LOCK,

        // Entity waiting for persist/commit
        TO_SAVE,

        // Entity to remove from DB
        TO_REMOVE;
    }

    private int version = 0;
    private int versionOld = 0;
    private Status status;

    Version(){
    }

    void setVersion(int version){
        this.version = version;
    }

    boolean isChanged(){
        return this.version > versionOld;
    }

    void incrementVersion(){
        this.version++;
    }

    void onPersist(){
        version = 0;
    }

    void changeStatus(Status status){
        this.status = status;
    }

    boolean isReadyForChange(){
        if(status == Status.LOCK)
            return false;

        return ++this.version == this.versionOld || this.version == 0;
    }

}
