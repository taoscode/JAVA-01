package io.github.taoscode.conc0302.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count2 {
    final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void get(){
        try {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"-rwlock get begin");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"-rwlock get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
    public void put(){
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"-rwlock put begin");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+"-rwlock put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }
}
