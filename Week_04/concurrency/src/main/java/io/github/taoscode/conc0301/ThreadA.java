package io.github.taoscode.conc0301;

public class ThreadA extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("extend Thread 这是线程A");
    }
}
