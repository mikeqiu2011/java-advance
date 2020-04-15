package com.mosh.threads;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ThreadsDemo {
    public static void printThread() {
        System.out.println("Active Threads: " + Thread.activeCount());
        System.out.println("Total Threads: " + Runtime.getRuntime().availableProcessors());
    }

    public static void startThread() {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new DownloadTask());
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //this will be printed only after the previous thread finish running
        System.out.println("ready to scan the file");
    }

    public static void interuptThread() {
        Thread thread = new Thread(new DownloadTask());
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        //this will be printed only after the previous thread finish running
        System.out.println("user cancelled");
    }

    public static void race() {
        DownloadStatus status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new DownloadTask(status));
            thread.start();
            threads.add(thread);
        }

        //wait for all threads to complete running
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("total download is: " + status.getTotalBytes());

    }

    public static void confinement() {
        List<Thread> threads = new ArrayList<>();
        List<DownloadTask> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            DownloadTask task = new DownloadTask();
            Thread thread = new Thread(task);
            tasks.add(task);

            thread.start();
            threads.add(thread);
        }

        //wait for all threads to complete running
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Integer collect =
                tasks.stream()
                        .map(t -> t.getStatus().getTotalBytes())
                        .reduce(0, Integer::sum);


//                        collect(Collectors.summingInt(t -> t.getStatus().getTotalBytes()));

        System.out.println("total download is: " + collect);

    }

    public static void lock() {
        DownloadStatus status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new DownloadTask(status));
            thread.start();
            threads.add(thread);
        }

        //wait for all threads to complete running
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("total download is: " + status.getTotalBytes());

    }

    public static void cachingDemo() {
        DownloadStatus status = new DownloadStatus();

        Thread thread1 = new Thread(new DownloadTask(status));
        Thread thread2 = new Thread(()->{
            while(!status.isDone()){
                synchronized (status){

                    try {
                        status.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(status.getTotalBytes());
        });

        thread1.start();
        thread2.start();

    }

    public static void showSyncCollection() {
//        Collection<Integer> collection = new ArrayList<>();
        Collection<Integer> collection =
                Collections.synchronizedCollection(new ArrayList<>());

        Thread thread1 = new Thread(()-> {
            collection.addAll(Arrays.asList(1,2,3));
        });

        Thread thread2 = new Thread(()-> {
            collection.addAll(Arrays.asList(4,5,6));
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(collection);
    }

    public static void showConcurrentCollection() {
//        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> map = new ConcurrentHashMap<>();

        Thread thread1 = new Thread(()->{
            map.put(1,"a");
            map.put(2,"b");
        });

        Thread thread2 = new Thread(()->{
            map.put(3,"c");
            map.put(4,"d");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.toString());

    }

    public static void main(String[] args) {

//        startThread();
//        interuptThread();
//        race();
//        confinement();
//        lock();
//        cachingDemo();
//        showSyncCollection();
        showConcurrentCollection();
    }
}
