package com.mosh.threads;

public class DownloadTask implements Runnable {
    private DownloadStatus status;


    public DownloadStatus getStatus() {
        return status;
    }

    public DownloadTask() {
        status = new DownloadStatus();
    }

    public DownloadTask(DownloadStatus status) {
        this.status = status;
    }

    @Override
    public void run() {

        System.out.println("Downloading a file: " + Thread.currentThread().getName());

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 1_000_000; i++) {
            if (Thread.currentThread().isInterrupted())
                return;
            status.incrementTotalBytes();
        }

        status.done();

        synchronized (status){

            status.notifyAll();
        }


        System.out.println("Download completed: " + Thread.currentThread().getName());

    }


}
