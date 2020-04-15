package com.mosh.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("a", "3"));
        customers.add(new Customer("b", "2"));
        customers.add(new Customer("c", "1"));

        Collections.sort(customers, new EmailComparator());

        System.out.println(customers);

    }

    @Override
    public String toString() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
