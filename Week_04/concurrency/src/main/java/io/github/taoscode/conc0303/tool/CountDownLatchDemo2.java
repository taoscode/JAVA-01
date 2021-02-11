package io.github.taoscode.conc0303.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {
    private final static int MAX_THREAD_NUM =100;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD_NUM);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            final int no = i;
            executorService.execute(()->{
                try{
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("程序员[%d]完成任务",no));
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("项目完成，顺利上线......");
        executorService.shutdown();
    }

}
