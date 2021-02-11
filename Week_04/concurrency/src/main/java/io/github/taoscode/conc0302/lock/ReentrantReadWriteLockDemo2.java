package io.github.taoscode.conc0302.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo2 {
    private final Map<String,Object> map = new HashMap<>();
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    public Object readWrite(String key){
        Object value= null;
        System.out.println("1.开启读锁，读取缓存数据。。。。。。");
        rwlock.readLock().lock();
        try{
            value = map.get(key);
            if(value == null){
                System.out.println("2.缓存数据不存在，释放读锁。。。。。");
                rwlock.readLock().unlock();
                System.out.println("3.获取写锁，写入数据。。。。。");
                rwlock.writeLock().lock();
                try {
                    value = "test";
                }finally {
                    System.out.println("4.释放写锁。。。。。");
                    rwlock.writeLock().unlock();
                }
                System.out.println("5.获取读锁。。。。。");

                rwlock.readLock().lock();

            }
        }finally {
            System.out.println("6.释放读锁。。。。。");
            rwlock.readLock().unlock();
        }
        return value;
    }
    public static void main(String[] args) {
        ReentrantReadWriteLockDemo2 reentrantReadWriteLockDemo2 = new ReentrantReadWriteLockDemo2();
        reentrantReadWriteLockDemo2.readWrite("key");
    }
}
