package sk.durovic.manager;

public enum Version {

    OPTIMISTIC_LOCK,
    LOCK,
    TO_SAVE,
    TO_REMOVE;

    private int version = 0;
    private int versionOld = 0;

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

}
