package io.github.taoscode.conc0303.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(2);
                    test(threadNum);
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
    private static void test(int threadNum){
        System.out.println("id:"+threadNum+"-----"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
