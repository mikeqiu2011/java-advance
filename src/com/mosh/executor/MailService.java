package com.mosh.executor;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send(){
        LongTask.simulator();
        System.out.println("mail sent");
    }

    public CompletableFuture<Void> sendAsync(){
        return CompletableFuture.runAsync(() -> send());
    }
}
