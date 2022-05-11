package sk.durovic.worker;

import sk.durovic.manager.Version;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JpaWorkers {

    private final ExecutorService executorService;

    public JpaWorkers() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    /*public void execute(Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> mapOfEntities, boolean clearContainers){
        JpaPersistWorker persistToSave = new JpaPersistWorker(mapOfEntities.get(Version.Status.TO_SAVE), clearContainers);
        JpaPersistWorker persistOptimisticLock = new JpaPersistWorker(mapOfEntities.get(Version.Status.OPTIMISTIC_LOCK), clearContainers);
        JpaPersistWorker persistLock = new JpaPersistWorker(mapOfEntities.get(Version.Status.LOCK), clearContainers);
        JpaRemoveWorker jpaRemoveWorker = new JpaRemoveWorker(mapOfEntities.get(Version.Status.TO_REMOVE));

        executorService.execute(persistToSave);
        executorService.execute(persistLock);
        executorService.execute(persistOptimisticLock);
        executorService.execute(jpaRemoveWorker);
    }*/

    public void execute(JpaProcessor... processors){
        for(JpaProcessor processor : processors)
            executorService.execute(processor.getWorker());
    }

    public void close(){
        executorService.shutdown();
    }

}
