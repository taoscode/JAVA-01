package io.github.taoscode.conc0302.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(16);
        String str = scheduledExecutorService.submit(()->{
            return "hello callback";
        }).get();
        System.out.println("str=" + str);
    }
}
