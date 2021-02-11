package io.github.taoscode.conc0303.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SyncListDemo {
    public static void main(String[] args) throws InterruptedException {
        List list0 = Arrays.asList(1,2,3,4,5,6,7,8,8);
        list0.set(8,9); //允许修改元素
//        list0.add(10); //不能增加元素个数
        List list1 = new ArrayList();
        list1.addAll(list0);
        List list2 = Collections.synchronizedList(list1);
        for (int i = 0; i < 10; i++) {
            final int no = i;
            new Thread(()->{
                list2.add("t"+no );//可以安全修改
                System.out.println(Thread.currentThread().getName()+"-修改list2......");
            },"myThread-"+i).start();
        }
        Thread.sleep(1000);
        System.out.println(Arrays.toString(list1.toArray()));
        Collections.shuffle(list2);
        System.out.println(Arrays.toString(list2.toArray()));
        List list3 = Collections.unmodifiableList(list2);
//        list3.set(0,2);//无法修改
    }
}
