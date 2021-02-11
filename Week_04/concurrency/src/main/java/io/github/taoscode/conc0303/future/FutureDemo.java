package io.github.taoscode.conc0303.future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Future<Integer> future = executorService.submit(()->{
           return new Random().nextInt();
        });
        executorService.shutdown();
        System.out.println("result:"+future.get());
    }
}
