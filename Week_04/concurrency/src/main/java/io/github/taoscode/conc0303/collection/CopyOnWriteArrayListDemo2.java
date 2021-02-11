package io.github.taoscode.conc0303.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListDemo2 {
    private List<String>  list = new CopyOnWriteArrayList<>();
//    private List<String>  list = new ArrayList<>();
//    private List<String>  list = new LinkedList<>();
//    private List<String>  list = new Vector<>();
    private final int MAX_THREAD_NUM = 10;
    public static void main(String[] args) {
        CopyOnWriteArrayListDemo2 copyOnWriteArrayListDemo2 = new CopyOnWriteArrayListDemo2();
        copyOnWriteArrayListDemo2.start();
    }
    private void init(){
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            list.add(".....Line"+(i+i)+"......");
        }
    }
    public void start(){
        init();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            executorService.execute(new ListRead(list));
            executorService.execute(new ListWrite(list,i));
        }
        executorService.shutdown();
    }
    private class ListRead implements Runnable{
        private List<String> list;
        public ListRead(List<String> list){
            this.list = list;
        }
        @Override
        public void run() {
            if(this.list != null){
                for (String str : list){
                    System.out.println(Thread.currentThread().getName() + ":" + str);
                }
            }
        }
    }
    private class ListWrite implements Runnable{
        private List<String> list;
        private int idx;
        public  ListWrite(List<String> list,int idx){
            this.list = list;
            this.idx = idx;
        }
        @Override
        public void run() {
//            this.list.remove(idx);
            this.list.add("add"+idx);
        }
    }
}
