package com.zhao.algorithm.balance;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class LeastConnect {
    class Node{
        String name;
        AtomicInteger count = new AtomicInteger(0);
        public Node(String name){
            this.name =name;
        }

        public void inc(){
            count.incrementAndGet();
        }

        public int get(){
            return count.get();
        }

        @Override
        public String toString() {
            return name+"="+count;
        }
    }

    Node[] nodes;
    public LeastConnect(String ns){
        String[] ns1 = ns.split(",");
        nodes = new Node[ns1.length+1];
        for (int i=0;i<ns1.length;i++){
            nodes[i+1] = new Node(ns1[i]);
        }
    }

    void down(int i){
        while (i<<1<nodes.length){
            int left = i<<1;
            int right = left+1;
            int flag =i;
            if (nodes[left].get()<nodes[i].get()){
                flag =left;
            }
            if (right<nodes.length&&nodes[flag].get()>nodes[right].get()){
                flag =right;
            }
            if (flag!=i){
                Node tmp = nodes[i];
                nodes[i] = nodes[flag];
                nodes[flag] =tmp;
                i=flag;
            }else {
                break;
            }

        }
    }

    void  req(){
        System.out.println("--------------------");
        Node node = nodes[1];
        System.out.println(node.name+" accept");
        node.inc();
        System.out.println("before"+ Arrays.toString(nodes));
        down(1);
        System.out.println("after"+ Arrays.toString(nodes));

    }

    public static void main(String[] args) {
        LeastConnect lc = new LeastConnect("a,b,c,d,e,f,g");
        for (int i=0;i<10;i++){
            lc.req();
        }
    }
}
