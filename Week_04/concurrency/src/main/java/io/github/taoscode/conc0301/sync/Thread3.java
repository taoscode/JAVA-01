package io.github.taoscode.conc0301.sync;

public class Thread3 {
    class Inner{
        private void m4t1(){
            int i = 100;
            while(i-- >0){
                System.out.println(Thread.currentThread().getName() + "-Inner.m4t1=" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void m4t2(){
            int i = 100;
            while(i-- >0){
                System.out.println(Thread.currentThread().getName() + "-Inner.m4t2=" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void m4t1(Inner inner){
        synchronized(inner){
            inner.m4t1();
        }
//        inner.m4t1();
    }
    private void m4t2(Inner inner){
        /*synchronized (inner) {
            inner.m4t2();
        }*/
        inner.m4t2();
    }

    public static void main(String[] args) {
        final Thread3 thread3 = new Thread3();
        final Inner inner = thread3.new Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t1(inner);
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t2(inner);
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
