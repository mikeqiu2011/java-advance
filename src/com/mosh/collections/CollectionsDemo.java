package com.mosh.collections;

import java.util.*;

public class CollectionsDemo {

    public static void showCollection() {
        Collection<String> collection = new ArrayList<>();
        Collection<String> other = new ArrayList<>();
        Collections.addAll(collection, "a", "b", "c");

        other.addAll(collection);

        System.out.println(collection.equals(other));

    }

    public static void showList() {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("a");

        list.add(0, "!");

        list.set(0, "A+");

        System.out.println(list.subList(0,2));
    }

    public static void showQueue() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");


        System.out.println(queue);

        System.out.println("peek:" + queue.peek());
        System.out.println("element:" + queue.element());
        System.out.println("poll:" + queue.poll());

        System.out.println(queue);

    }

    public static void showSet() {
        Collection collection = new ArrayList();
        Collections.addAll(collection,"a","b","b","b");

        Set<String> set = new HashSet<>(collection);
        System.out.println(set);


    }

    public static void main(String[] args) {
//        CollectionsDemo.showCollection();
//        CollectionsDemo.showList();
//        CollectionsDemo.showQueue();
        CollectionsDemo.showSet();
    }
}
