package com.mosh.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            //future is callable which takes no input but return a value
            Future<Integer> future = executor.submit(() -> {
                LongTask.simulator(); // execute for 3 sec the return 1
                return 1;
            });
            System.out.println("continue first");

            try {
                Integer result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


        } finally {
            executor.shutdown(); // safely shutdown the pool
        }

//        executor.shutdownNow(); //force shutdown even if task is in progress

    }
}
