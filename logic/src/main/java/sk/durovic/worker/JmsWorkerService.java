package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class JmsWorkerService {

    private final ExecutorService executorService;
    public JmsWorkerService(){
        this.executorService = createService();
    }

    public void processMessage(Runnable runnable){
        executorService.execute(runnable);
    }

    private ExecutorService createService(){
        return Executors.newFixedThreadPool(4);
    }
}
