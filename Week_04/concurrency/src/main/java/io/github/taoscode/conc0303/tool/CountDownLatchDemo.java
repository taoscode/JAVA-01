package io.github.taoscode.conc0303.tool;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNum(i,countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("各个子线程任务执行结束.....");
        System.out.println("main thread end!");
    }
    static class ReadNum implements Runnable{
        private int id;
        private CountDownLatch countDownLatch;
        public ReadNum(int id,CountDownLatch countDownLatch){
            this.id = id;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            System.out.println("任务： "+id+"-"+Thread.currentThread().getName());
            System.out.println("线程任务"+id+"结束,其他任务继续");
            countDownLatch.countDown();
        }
    }
}
