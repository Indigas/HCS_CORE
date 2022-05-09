package sk.durovic.worker;

import sk.durovic.manager.Version;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JpaWorkers {

    private ExecutorService executorService;

    public JpaWorkers() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void execute(Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> mapOfEntities, boolean clearContainers){
        JpaPersistWorker jpaPersistWorker = new JpaPersistWorker(mapOfEntities.get(Version.Status.TO_SAVE), clearContainers);
        JpaRemoveWorker jpaRemoveWorker = new JpaRemoveWorker(mapOfEntities.get(Version.Status.TO_REMOVE));

        executorService.execute(jpaPersistWorker);
        executorService.execute(jpaRemoveWorker);
    }

    public void close(){
        executorService.shutdown();
    }

}
