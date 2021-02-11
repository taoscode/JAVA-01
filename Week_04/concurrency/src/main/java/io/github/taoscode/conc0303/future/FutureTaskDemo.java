package io.github.taoscode.conc0303.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) {

        FutureTask<Integer> task =  new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return new Random().nextInt();
            }
        });
        //两种方式
//        new Thread(task).start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(task);
        executorService.shutdown();
        try {
            System.out.println("result:"+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
