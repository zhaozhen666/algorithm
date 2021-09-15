package com.zhao.algorithm.out;

import java.util.Iterator;
import java.util.LinkedList;

public class LRU {
    LinkedList<Integer> lru = new LinkedList<>();
    int size=3;
    public void  print(){
        System.out.println(this.lru);
    }
    public void  add(int i){
        lru.addFirst(i);
        if (lru.size()>size){
            lru.removeLast();
        }
        print();
    }
    public void read(int i){
        Iterator<Integer> iterator = lru.iterator();
        int index=0;
        while (iterator.hasNext()){
            int j = iterator.next();
            if (i==j){
                System.out.println("find it");
                lru.remove(index);
                lru.addFirst(j);
                print();
                return;

            }
            index++;
        }
        System.out.println("not found");
        print();
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        System.out.println("add 1-3");
        lru.add(1);
        lru.add(2);
        lru.add(3);
        System.out.println("add 4");
        lru.add(4);
        System.out.println("read 2");
        lru.read(2);
        System.out.println("read 100");
        lru.read(100);
        System.out.println("add 5");
        lru.add(5);
    }
}
