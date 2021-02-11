package io.github.taoscode.conc0303.tool;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 20; i++) {
            new Worker(i,semaphore).start();
        }
    }
    static class Worker extends Thread{
        private int id;
        private Semaphore semaphore;
        public Worker(int id,Semaphore semaphore){
            this.id = id;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+id+"占用一台机器在生产.....");
                Thread.sleep(1000);
                System.out.println("工人"+id+"释放出机器.....");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
