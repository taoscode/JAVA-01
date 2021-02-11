package io.github.taoscode.conc0302.lock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        final Count count = new Count();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                count.get();
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                count.put();
            }).start();
        }
    }
}
