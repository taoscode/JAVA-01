package io.github.taoscode.conc0302.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Count {
    final ReentrantLock lock = new ReentrantLock();
    public void get(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"get begin");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"get end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void put(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"put begin");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"put end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
