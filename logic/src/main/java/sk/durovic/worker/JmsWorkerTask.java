package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class JmsWorkerTask {

    private JmsWorkerTask(){}

    public static void processWithoutReply(Consumer<Object> consumer, Object event){
        JmsWorkerTask task = new JmsWorkerTask();

        task.createService().execute(new Runnable() {
            @Override
            public void run() {
                consumer.accept(event);
            }
        });
    }

    public static Object processWithReply(Function<Object, Object> function, Object event){
        JmsWorkerTask task = new JmsWorkerTask();

        Future<Object> reply = task.createService().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return function.apply(event);
            }
        });

        try {
            return reply.get();
        } catch (ExecutionException | InterruptedException e){
            log.error("Jms message not processed");
            return null;
        }
    }

    private ExecutorService createService(){
        return Executors.newSingleThreadExecutor();
    }
}
