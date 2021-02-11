package io.github.taoscode.conc0301;

public class RunnerMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner1());
        Thread thread2 = new Thread(new Runner2());
        thread1.start();
        thread2.start();
        //终端线程2
        thread2.interrupt();
        System.out.println(Thread.activeCount());
    }

    static class Runner1 implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                System.out.println("进去Runner1的运行状态："+i);
            }
        }
    }
    static class Runner2 implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                System.out.println("进去Runner2的运行状态："+i);
            }
            boolean result1 = Thread.currentThread().isInterrupted();
            //中断
            boolean result2 = Thread.interrupted();
            boolean result3 = Thread.currentThread().isInterrupted();
            System.out.println("Runner2 run result1----->"+result1);
            System.out.println("Runner2 run result2----->"+result2);
            System.out.println("Runner2 run result3----->"+result3);
        }
    }
}
