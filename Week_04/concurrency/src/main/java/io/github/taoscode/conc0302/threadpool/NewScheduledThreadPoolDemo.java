package io.github.taoscode.conc0302.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            final int no = i;
            executorService.schedule(()->{
                System.out.println("start:"+no);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end:"+no);
            },10, TimeUnit.SECONDS);
        }
        executorService.shutdown();
        System.out.println("main thread end");
    }

}
