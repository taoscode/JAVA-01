package io.github.taoscode.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class HomeWork01 {
    private static Integer mySum;
    private static  InheritableThreadLocal<List<Integer>> myLocal = new InheritableThreadLocal<List<Integer>>(){
        @Override
        protected List initialValue() {
            return Arrays.asList(1);
        }
    };
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
        Count5 count5 = new Count5();
        List<Integer> list = new ArrayList<>();
        count5.setValue(list);
        count5.start();
        count5.join();
        System.out.println("异步调用返回结果10："+list.get(0));
        System.out.println("Main Thread End !");
        System.out.println("==================方法11====================");
        Count6 count6 = new Count6();
        List<Integer> list1 = new ArrayList<>();
        count6.setValue(list1);
        Thread t2 = new Thread(count6);
        t2.start();
        t2.join();
        System.out.println("异步调用返回结果11："+list1.get(0));
        System.out.println("Main Thread End !");

        System.out.println("==================方法12====================");
        Count7 count7 = new Count7();
        Thread t3 = new Thread(count7);
        t3.start();
        t3.join();
        System.out.println("异步调用返回结果12："+mySum);
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
    static class Count5 extends Thread{
        private List<Integer> list;
        public void setValue(List<Integer> list){
            this.list = list;
        }
        @Override
        public void run() {
            if(this.list == null){
                list = new ArrayList<>();
            }
            list.add(sum());
        }
    }
    static class Count6 implements Runnable{
        private List<Integer> list;
        public void setValue(List<Integer> list){
            this.list = list;
        }
        @Override
        public void run() {
            if(this.list == null){
                list = new ArrayList<>();
            }
            list.add(sum());
        }
    }
    static class Count7 implements Runnable{
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
    static class Count10 extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"---------"+myLocal.get());
            myLocal.get().set(0,sum());
            System.out.println(Thread.currentThread().getName()+"---------"+myLocal.get());
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
