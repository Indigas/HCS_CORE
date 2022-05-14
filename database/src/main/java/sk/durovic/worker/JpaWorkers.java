package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Parent class to other workers to execute tasks
 */
@Slf4j
public class JpaWorkers {

    private final ExecutorService executorService;

    public JpaWorkers() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void execute(JpaProcessor... processors){
        for(JpaProcessor processor : processors){
            try {
                executorService.execute(processor.getWorker());
            } catch (NullPointerException exception){
                log.error("Worker was not initialized");
                exception.printStackTrace();
            }
        }
    }

    public void close(){
        executorService.shutdown();
    }

}
