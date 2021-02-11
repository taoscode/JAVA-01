package io.github.taoscode.conc0302.lock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        final Count2 count = new Count2();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                count.get();
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                count.put();
            }).start();
        }
    }
}
