package com.mosh.generics;

import java.util.Iterator;

public class GenericList<T> implements Iterable<T> {
    private T[] items = (T[]) new Object[10];
    private int count;

    public void add(T item){
        items[count++] = item;
    }

    public T get(int index){
        return items[index];
    }

    private T[] getItems() {
        return items;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenIterator(this);
    }

    private class GenIterator implements Iterator<T>{
        private GenericList<T> list;
        int index;

        private GenIterator(GenericList<T> genericList) {
            this.list = genericList;
//            index=0;
        }

        @Override
        public boolean hasNext() {
            System.out.println(index);
            return index < list.count;

        }

        @Override
        public T next() {
            return list.items[index++];
        }
    }
}
