package com.mosh.generics;

public class Utils {
    public static <T extends Comparable<T>> T max(T first, T second){
        return (first.compareTo(second) >= 0) ? first : second;
    }

    public static <K, V> void print(K key, V value){
        System.out.println(key + " = " + value);
    }

    public static void printUsers(GenericList<? extends User> users){
        System.out.println(users.get(0));
//        users.add(new User(2));
    }
}
