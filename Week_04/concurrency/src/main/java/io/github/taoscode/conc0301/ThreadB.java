package io.github.taoscode.conc0301;

public class ThreadB implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("implement Runnable 这是线程B");
        Thread thread = Thread.currentThread();
        String currentThreadName = thread.getName();
        System.out.println("这是线程名称："+currentThreadName);
        System.out.println("这是线程："+currentThreadName+",线程组名称："+thread.getThreadGroup().getName()+",线程组种活动的线程数量："+thread.getThreadGroup().activeCount());
        System.out.println("这是线程："+currentThreadName+",线程优先级："+thread.getPriority()+",线程的状态："+thread.getState()+"，是否为守护线程："+thread.isDaemon()+",是否处于活跃状态："+thread.isAlive());

    }
}
