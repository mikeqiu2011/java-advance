package com.mosh.collections;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void show() {
        Customer c1 = new Customer("a", "e1");
        Customer c2 = new Customer("b", "e2");

        Customer unkown = new Customer("unkown", "unkown");

        Map<String, Customer> map = new HashMap<>();
        map.put(c1.getEmail(), c1);
        map.put(c2.getEmail(), c2);

        System.out.println(map.getOrDefault("e10", unkown));

        for ( Map.Entry entry : map.entrySet())
            System.out.println(entry.getValue());

    }

    public static void main(String[] args) {
        MapDemo.show();
    }
}
