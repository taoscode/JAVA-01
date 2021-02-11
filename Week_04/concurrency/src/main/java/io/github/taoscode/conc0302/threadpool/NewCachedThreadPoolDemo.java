package io.github.taoscode.conc0302.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            final int no = i;
            executorService.execute(()->{
                System.out.println("start:"+no);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end:"+no);
            });
        }
        executorService.shutdown();
        System.out.println("main thread end");
    }

}
