package com.zhao.algorithm.out;

import java.util.Iterator;
import java.util.LinkedList;

public class FIFO {
    LinkedList<Integer> fifo = new LinkedList<>();
    int size =3;

    public void print(){
        System.out.println(this.fifo);
    }

    public void  add(int i){
        fifo.addFirst(i);
        if (fifo.size()>size){
            fifo.removeLast();
        }
        print();
    }

    public void read(int i){
        Iterator<Integer> iterator = fifo.iterator();
        while (iterator.hasNext()){
            int j = iterator.next();
            if (i==j){
                System.out.println("find it");
                print();
                return;
            }
        }
        System.out.println("not found");
        print();
    }

    public static void main(String[] args) {
        FIFO fifo = new FIFO();
        System.out.println("add 1,2,3");
        fifo.add(1);
        fifo.add(2);
        fifo.add(3);
        System.out.println("add 4");
        fifo.add(4);
        System.out.println("read 2");
        fifo.read(2);
        System.out.println("read 100");
        fifo.read(100);
        System.out.println("add 5");
        fifo.add(5);
    }
}
