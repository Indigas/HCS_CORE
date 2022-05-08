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
    private Status status = Status.OPTIMISTIC_LOCK;
    private Status statusOld = Status.OPTIMISTIC_LOCK;

    public Version(){
    }

    void setVersion(int version){
        this.version = version;
    }

    int getVersion(){ return this.version; }

    boolean isChanged(){
        return this.version > versionOld;
    }

    public void incrementVersion(){
        this.version++;
    }

    void onPersist(){
        version = 0;
        versionOld = 0;
        toSave();
    }

    void onSave(){
        this.status = Status.TO_SAVE;
        this.versionOld = version;
    }

    void changeStatus(Status status){
        onStatusChange();
        this.status = status;
    }

    boolean isReadyForChange(){
        if(status == Status.LOCK || status == Status.TO_REMOVE)
            return false;

        return this.version == versionOld;
    }

    Status getStatus(){
        return this.status;
    }

    Status getStatusOld(){
        return this.statusOld;
    }

    void release(){
        onStatusChange();
        this.status = Status.OPTIMISTIC_LOCK;
        this.versionOld = this.version;
    }

    void lock(){
        onStatusChange();
        this.status = Status.LOCK;
    }

    void toRemove(){
        onStatusChange();
        this.status = Status.TO_REMOVE;
    }

    void toSave(){
        onStatusChange();
        this.status = Status.TO_SAVE;
    }

    private void onStatusChange(){
        this.statusOld = this.status;
    }

}
