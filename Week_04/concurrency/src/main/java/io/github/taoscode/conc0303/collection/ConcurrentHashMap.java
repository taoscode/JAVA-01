package io.github.taoscode.conc0303.collection;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, AtomicInteger> count = new java.util.concurrent.ConcurrentHashMap<>();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AtomicInteger oldValue ;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if(oldValue == null){
                        AtomicInteger zero = new AtomicInteger(0);
                        count.putIfAbsent("a",zero);
                        oldValue = count.get("a");
                    }
                    oldValue.incrementAndGet();
                }

                countDownLatch.countDown();
            }
        };
        Thread t1 =  new Thread(runnable);
        Thread t2 =  new Thread(runnable);
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println(count);
    }
}
