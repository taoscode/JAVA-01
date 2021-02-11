package io.github.taoscode.conc0303.threadloal;

public class ThreadLocalDemo {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
        return 0;
    }};
    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }
    public int getNextNum(){
        seqNum.set(seqNum.get() +1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalDemo sn = new ThreadLocalDemo();
        Thread t1 = new SnThread(sn);
        Thread t2 = new SnThread(sn);
        Thread t3 = new SnThread(sn);
        Thread t4 = new SnThread(sn);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
    private static class SnThread extends Thread{
        private ThreadLocalDemo sn;
        public SnThread(ThreadLocalDemo sn){
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"--->sn="+sn.getNextNum());
            }
            sn.getThreadLocal().remove();
        }
    }
}
