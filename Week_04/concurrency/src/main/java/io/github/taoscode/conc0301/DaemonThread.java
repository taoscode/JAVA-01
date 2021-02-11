package io.github.taoscode.conc0301;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程："+Thread.currentThread().getName());
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.setName("mythread-1");
        thread.start();
        Thread.sleep(500);
    }
}
