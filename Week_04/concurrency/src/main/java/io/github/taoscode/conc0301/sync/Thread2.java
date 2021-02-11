package io.github.taoscode.conc0301.sync;

public class Thread2 {

    public void m4t1(){
        synchronized(this){
            int i=5;
            while (i-- >0){
                System.out.println(Thread.currentThread().getName() + ":" +i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void m4t2(){
        int i=5;
        while (i-- >0){
            System.out.println(Thread.currentThread().getName() + ":" +i);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Thread2 thread2 = new Thread2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread2.m4t1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread2.m4t2();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
