package sk.durovic.manager.basic;

import org.springframework.context.ApplicationContext;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.JpaPersistWorker;
import sk.durovic.worker.JpaProcessWorker;
import sk.durovic.worker.JpaProcessor;
import sk.durovic.worker.JpaRemoveWorker;

import java.util.List;

/**
 * Track version of entity
 */
public class Version {

    /**
     * Status of entity represents following processing task.
     * Used by workers to execute optimal task
     */
    public enum Status implements JpaProcessor {
        // Entity can be fetched from container.
        // Entity will be tracked with version.
        OPTIMISTIC_LOCK,

        // Entity cannot be fetched from container.
        LOCK,

        // Entity waiting for persist/commit
        TO_SAVE,

        // Entity to remove from DB
        TO_REMOVE{
            @Override
            public void initialize(List<? extends BaseEntityAbstractClass<?>> list,
                                   ApplicationContext context){
                setWorker(new JpaRemoveWorker(list, context));
            }
        };

        private JpaProcessWorker worker;

        void setWorker(JpaProcessWorker worker){
            this.worker = worker;
        }

        /**
         * Initialize worker to following processing task
         * @param list
         * @param clearContainer
         */
        @Override
        public void initialize(List<? extends BaseEntityAbstractClass<?>> list,
                               ApplicationContext context){
            setWorker(new JpaPersistWorker(list, context));
        }

        @Override
        public JpaProcessWorker getWorker() {
            return this.worker;
        }


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

    boolean isStatusChanged(){
        return this.status != this.statusOld;
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
        return status != Status.LOCK && status != Status.TO_REMOVE;
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

    public boolean isLocked(){
        return this.status == Status.LOCK;
    }

    public boolean isToRemove(){
        return this.status == Status.TO_REMOVE;
    }

    public boolean isToSave(){
        return this.status == Status.TO_SAVE;
    }

    public boolean isOptimisticLock(){
        return this.status == Status.OPTIMISTIC_LOCK;
    }

}
