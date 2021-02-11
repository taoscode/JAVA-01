package io.github.taoscode.conc0301.op;

public class Join {
    static class MyThread extends Thread{
        private String name;
        private Object obj;
        public MyThread(String name){
            this.name = name;
        }
        public void setObj(Object obj){
            this.obj = obj;
        }
        @Override
        public void run() {
            synchronized(this){
                for(int i=0 ; i< 100; i++){
                    System.out.println(name + ":" + i);
                }
            }
        }
    }
    public static void main(String[] args) {
        Object obj = new Object();
        MyThread myThread = new MyThread("mythread---");
        myThread.setObj(obj);
        myThread.start();
        synchronized (myThread){
            for (int i=0;i<100;i++){
                if(i ==20){
                    try {
                        myThread.join();
//                        myThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() +"---" +i);
            }
        }
    }
}
