package com.mosh.executor;

import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuturesDemo {
    public static void show() {

        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 1);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    private static void showAsync() {
        MailService mailService = new MailService();
        mailService.sendAsync();
        System.out.println("hello world");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void showThenAccept() {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 1);

        future.thenAcceptAsync(result -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });
    }

    private static void showExceptionally() {
        CompletableFuture<Object> future =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("getting whether");
                    throw new IllegalArgumentException();
                });

        try {
            Object temperature = future.exceptionally(ex -> 1).get();
            System.out.println(temperature);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("got ya");
            e.printStackTrace();
        }
    }

    private static void concatenateFuture() {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 20);

        future
                .thenApply(cel -> (cel * 1.8) + 32)
                .thenAccept(f -> System.out.println(f));
    }

    private static void showCombine() {
        CompletableFuture<Integer> first =
                CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Double> second =
                CompletableFuture.supplyAsync(() -> 0.9);

        first.thenCombine(second, (price, exchangeRate) -> price * exchangeRate)
                .thenAccept(result -> System.out.println(result));
    }

    private static void showAllOf() {
        CompletableFuture<Integer> first =
                CompletableFuture.supplyAsync(() -> 1);

        CompletableFuture<Integer> second =
                CompletableFuture.supplyAsync(() -> 2);

        CompletableFuture.allOf(first, second).thenRun(() -> {
            try {
                Integer firstResult = first.get();
                System.out.println(firstResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("all done");
        });
    }

    private static void showAnyOf() {
        CompletableFuture<Integer> slow =
                CompletableFuture.supplyAsync(() -> {
                    LongTask.simulator();
                    return 2;
                });

        CompletableFuture<Integer> fast =
                CompletableFuture.supplyAsync(() -> 2);

        CompletableFuture
                .anyOf(slow, fast)
                .thenAccept(temp -> System.out.println(temp));
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(startTime);

        CompletableFuture<PriceQuote> first =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("getting from Site1");
                    LongTask.simulator();

                    PriceQuote site1 = new PriceQuote(100, "site1");
                    System.out.println(site1);
                    return site1;
                });

        CompletableFuture<PriceQuote> second =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("getting from Site2");
                    LongTask.simulator();

                    PriceQuote site2 = new PriceQuote(150, "site2");
                    System.out.println(site2);
                    return site2;
                });

        CompletableFuture.allOf(first,second).thenRun(()->{
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(endTime);

            try {
                PriceQuote priceQuote1 = first.get();
                PriceQuote priceQuote2 = second.get();

                if (priceQuote1.getPrice() > priceQuote2.getPrice()){
                    System.out.println("site1 is better");
                }else{
                    System.out.println("site2 is better");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println(totalTime);
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("done");


    }
}
