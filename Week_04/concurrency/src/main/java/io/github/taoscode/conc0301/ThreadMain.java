package io.github.taoscode.conc0301;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("这是主线程......");
        ThreadB runnable = new ThreadB();
        new Thread(runnable).start();
        System.out.println("这是主线程......");
        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("这是主线程......");
        try {
            System.out.println("callable 返回结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
