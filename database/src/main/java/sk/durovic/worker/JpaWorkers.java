package sk.durovic.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JpaWorkers {

    private final ExecutorService executorService;

    public JpaWorkers() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void execute(JpaProcessor... processors){
        for(JpaProcessor processor : processors)
            executorService.execute(processor.getWorker());
    }

    public void close(){
        executorService.shutdown();
    }

}
