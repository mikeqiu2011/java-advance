package com.mosh.exceptions;

import com.mosh.generics.Account;

import java.io.IOException;

public class ExceptionsDemo {
    public static void show(String name) throws IOException {
        Account account = new Account();
        try {
            account.deposit(-1);
        } catch (IOException e) {
            System.out.println("logging");
            throw e;
        }


    }

    public static void sayHello(String name) {
        System.out.println(name.toUpperCase());
    }
}
