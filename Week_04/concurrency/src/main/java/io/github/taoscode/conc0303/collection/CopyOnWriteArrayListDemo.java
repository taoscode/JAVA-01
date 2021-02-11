package io.github.taoscode.conc0303.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();//线程安全
//        List<Integer> list = new ArrayList<>();//线程不安全
//        List<Integer> list = new LinkedList<>();//线程不安全
//        List<Integer> list = new Vector<>();//线程不安全

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        T1 t1 = new T1(list);
        T2 t2 = new T2(list);
        t1.start();
        t2.start();
    }
    static class T1 extends Thread{
        private List<Integer> list;
        public T1(List<Integer> list){
            this.list = list;
        }
        @Override
        public void run() {
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
        }
    }
    static class T2 extends Thread{
        private List<Integer> list;
        public T2(List<Integer> list){
            this.list = list;
        }
        @Override
        public void run() {
            for(Integer i : list){
                System.out.println(i);
            }
        }
    }
}
