package com.mosh.executor;

public class LongTask {
    public static void simulator() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
