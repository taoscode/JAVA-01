package io.github.taoscode.conc0303.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo3 {
    static Warehouse warehouse = new Warehouse();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }
    static class Producer implements Runnable{
        static int num =1;
        @Override
        public void run() {
            int n = num ++;
            while (true){
                warehouse.put(n);
                System.out.println(">"+n);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Consumer implements Runnable{

        @Override
        public void run() {
            while (true){
                System.out.println("<"+warehouse.take());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class Warehouse{
        //非满锁
        final Semaphore notFull = new Semaphore(10);
        //非空锁
        final Semaphore notEmpty  = new Semaphore(0);
        //核心锁
        final Semaphore mutex = new Semaphore(1);
        //库存容量
        final Object [] items = new Object[10];
        int putptr,takeptr,count;
        public void put(Object obj){
            try {
                notFull.acquire();
                mutex.acquire();
                items[putptr] = obj;
                if(++putptr==items.length){
                    putptr = 0;
                    ++count;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                notFull.release();
                mutex.release();
            }
        }

        public Object take(){
            Object obj=null;
            try {
                notEmpty.acquire();
                mutex.acquire();
                obj = items[putptr];
                if(++takeptr==items.length){
                    takeptr = 0;
                    --count;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                notEmpty.release();
                mutex.release();
            }
            return obj;
        }
    }
}
