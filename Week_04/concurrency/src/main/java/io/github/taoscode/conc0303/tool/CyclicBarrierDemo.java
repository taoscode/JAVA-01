package io.github.taoscode.conc0303.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("回调-----"+Thread.currentThread().getName());
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNum(i,cyclicBarrier)).start();
        }
        System.out.println("main thread end!");
        System.out.println("cyclicBarrier重复使用");
        //重复利用
        for (int i = 5; i < 10; i++) {
            new Thread(new ReadNum(i,cyclicBarrier)).start();
        }
    }

    static class ReadNum implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;
        public ReadNum(int id ,CyclicBarrier cyclicBarrier){
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            System.out.println("id:"+id+"-----"+Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                System.out.println(String.format("线程任务[%d]结束，其他任务继续",id));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
