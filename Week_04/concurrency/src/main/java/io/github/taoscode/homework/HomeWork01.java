package io.github.taoscode.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HomeWork01 {
    private static Integer mySum;
    private static Integer myResult;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("==================方法1====================");
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        new Thread(futureTask).start();
        System.out.println("异步调用返回结果1："+futureTask.get());
        System.out.println("Main Thread End !");

        System.out.println("==================方法2====================");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executorService.shutdown();
        System.out.println("异步调用返回结果2："+future.get());
        System.out.println("Main Thread End !");

        System.out.println("==================方法3====================");
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(()->{
            return sum();
        });
        executorService1.execute(futureTask1);
        executorService1.shutdown();
        System.out.println("异步调用返回结果3："+futureTask1.get());
        System.out.println("Main Thread End !");
        System.out.println("==================方法4====================");
        Count count = new Count();
        count.start();
        count.join();
        System.out.println("异步调用返回结果4："+count.calc());
        System.out.println("Main Thread End !");
        System.out.println("==================方法5====================");
        Count1 count1 = new Count1();
        Thread t1 = new Thread(count1);
        t1.start();
        t1.join();
        System.out.println("异步调用返回结果5："+count1.calc());
        System.out.println("Main Thread End !");
        System.out.println("==================方法6====================");
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Count2 count2 = new Count2(countDownLatch);
//        executorService2.submit(count2);
        executorService2.execute(count2);
        countDownLatch.await();
        executorService2.shutdown();
        System.out.println("异步调用返回结果6："+count2.calc());
        System.out.println("Main Thread End !");

        System.out.println("==================方法7====================");
        ExecutorService executorService3 = Executors.newFixedThreadPool(1);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(()->{return sum();});
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(1,futureTask2);
        Count3 count3 = new Count3(cyclicBarrier);
        executorService3.execute(count3);
//        executorService3.submit(count3);
        executorService3.shutdown();
        System.out.println("异步调用返回结果7："+futureTask2.get());
        System.out.println("Main Thread End !");
        System.out.println("==================方法8====================");
        int result = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return sum();}).join();
        System.out.println("异步调用返回结果8："+result);
        System.out.println("Main Thread End !");

        System.out.println("==================方法9====================");
        Count4 count4 = new Count4();
        count4.start();
        count4.join();
        System.out.println("异步调用返回结果9："+mySum);
        System.out.println("Main Thread End !");
        System.out.println("==================方法10====================");
        WaitAndNotifyDemo waitAndNotifyDemo = new WaitAndNotifyDemo();
        new Thread(()->{waitAndNotifyDemo.calc();}).start();
        System.out.println("异步调用返回结果10："+waitAndNotifyDemo.getResult());
        System.out.println("Main Thread End !");
        System.out.println("==================方法11====================");
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(()->{return sum();});
        forkJoinPool.shutdown();
        System.out.println("异步调用返回结果11："+forkJoinTask.get());
        System.out.println("Main Thread End !");

        System.out.println("==================方法12====================");
        LockDemo lockDemo = new LockDemo();
        new Thread(()->{
            lockDemo.calc();
        }).start();
        System.out.println("异步调用返回结果12："+lockDemo.getResult());
        System.out.println("Main Thread End !");

        System.out.println("==================方法13====================");
        Count8 count8 = new Count8();
        List<Integer> list2 = new ArrayList<>();
        count8.setValue(list2);
        ExecutorService executorService4 =  Executors.newCachedThreadPool();
        executorService4.submit(count8).get();
        executorService4.shutdown();
        System.out.println("异步调用返回结果13："+list2.get(0));
        System.out.println("Main Thread End !");

        System.out.println("==================方法14====================");
        Count9 count9 = new Count9();
        count9.start();
        count9.join();
        System.out.println("异步调用返回结果14："+System.getProperty("sum"));
        System.out.println("Main Thread End !");
        System.out.println("==================方法15====================");
        Semaphore semaphore = new Semaphore(0);
        new Thread(()->{
            myResult = sum();
            semaphore.release();
        }).start();
        semaphore.acquire();
        System.out.println("异步调用返回结果15："+myResult);
        System.out.println("Main Thread End !");

        System.out.println("==================方法16====================");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        new Thread(()->{
            queue.offer(sum());
        }).start();
        System.out.println("异步调用返回结果16："+queue.take());
        System.out.println("Main Thread End !");
    }

    static class Count extends Thread{
        private  int sum;
        public int calc(){
            return this.sum;
        }
        @Override
        public void run() {
            this.sum = sum();
        }
    }
    static class Count1 implements Runnable{
        private  int sum;
        public int calc(){
            return this.sum;
        }
        @Override
        public void run() {
            this.sum = sum();
        }
    }
    static class Count2 implements Runnable{
        private  int sum;
        private CountDownLatch countDownLatch;
        public Count2(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        public int calc(){
            return this.sum;
        }
        @Override
        public void run() {
            this.sum = sum();
            countDownLatch.countDown();
        }
    }
    static class Count3 implements Runnable{
        private  int sum;
        private CyclicBarrier cyclicBarrier;
        public Count3(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                this.sum = sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    static class Count4 extends Thread{
        @Override
        public void run() {
            mySum = sum();
        }
    }

    static class Count8 implements Callable{
        private List<Integer> list;
        public void setValue(List<Integer> list){
            this.list = list;
        }
        @Override
        public Object call() throws Exception {
            if(this.list == null){
                list = new ArrayList<>();
            }
            list.add(sum());
            return null;
        }
    }

    static class Count9 extends Thread{
        @Override
        public void run() {
            System.setProperty("sum",String.valueOf(sum()));
        }
    }
    static class LockDemo{
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        private Integer result;
        public void calc(){
            try{
                lock.lock();
                this.result = sum();
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }
        public Integer getResult(){
            try{
                lock.lock();
                while (result == null){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return this.result;
            }finally {
                lock.unlock();
            }
        }
    }
    static class WaitAndNotifyDemo{
        private Integer result;
        public synchronized void calc(){
            this.result = sum();
            notifyAll();
        }
        public synchronized Integer getResult(){
            while (this.result == null){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.result;
        }
    }
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
