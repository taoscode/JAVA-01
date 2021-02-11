package io.github.taoscode.conc0303.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("1.变换结果");
        String result1 =  CompletableFuture.supplyAsync(()->{return "Hello";}).thenApplyAsync(v->v+" World").join();
        System.out.println("result1:"+result1);
        CompletableFuture.supplyAsync(()->{return "Hello";}).thenAccept(v->{
            System.out.println("2.消费数据");
            System.out.println("consumer:"+v);
        });
        System.out.println("3.组合");
        String result3 =CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " World";}),(s1,s2)->{return s1+s2;}).join();
        System.out.println("result3:"+result3);
        System.out.println("4.竞争");
        String result4 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            return "Hi Girl";
        }),(s)->{return s;}).join();
        System.out.println("竞争："+result4);
        System.out.println("5.补偿异常");
        String result5 = CompletableFuture.supplyAsync(()->{
            if(true){
                throw new RuntimeException("Exception test");
            }
            return "Hello";
        }).exceptionally(e->{
            e.printStackTrace();
            return "Hello World!";
        }).join();
        System.out.println(result5);
        System.out.println("main thread end!");
    }
}
