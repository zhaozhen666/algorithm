package com.zhao.algorithm.balance;

import java.util.ArrayList;

public class WeightRoundRobin {
    class Node{
        int weight,currentWeight;
        String name;

        public Node(String name,int weight){
            this.name = name;
            this.weight = weight;
            this.currentWeight=0;
        }

        @Override
        public String toString() {
            return String.valueOf(currentWeight);
        }
    }

    ArrayList<Node> list;
    int total;

    public WeightRoundRobin(String nodes){
        String[] ns = nodes.split(",");
        list = new ArrayList<>(ns.length);
        for (String n :ns){
            String[] n1 = n.split("#");
            int weight = Integer.valueOf(n1[1]);
            list.add(new Node(n1[0],weight));
            total+=weight;
        }
    }

    Node getCurrent(){
        for (Node node:list){
            node.currentWeight+=node.weight;
        }
        Node current = list.get(0);
        int i=0;
        for (Node node:list){
            if (node.currentWeight>i){
                i=node.currentWeight;
                current =node;
            }
        }
        return current;
    }

    void req(){
        Node node = this.getCurrent();
        System.out.print(list.toString()+"---");
        System.out.print(node.name+"---");
        node.currentWeight-=total;
        System.out.println(list);
    }

    public static void main(String[] args) {
        WeightRoundRobin wrr = new WeightRoundRobin("a#4,b#2,c#1");
        for (int i=0;i<7;i++){
            wrr.req();
        }
    }
}
