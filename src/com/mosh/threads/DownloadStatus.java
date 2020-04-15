package com.mosh.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class DownloadStatus {
    private LongAdder totalBytes = new LongAdder();
    private volatile boolean isDone;

    public int getTotalBytes() {
        return totalBytes.intValue(); //sum
    }

    public void incrementTotalBytes() {
        totalBytes.increment();
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
}
