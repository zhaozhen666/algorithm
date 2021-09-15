package com.zhao.algorithm.balance;

import java.util.ArrayList;
import java.util.Random;

public class WeightRandom {
    ArrayList<String> list;
    public WeightRandom(String nodes){
        String [] ns =nodes.split(",");
        list = new ArrayList<>();
        for (String n :ns){
            String[] n1 =n.split("#");
            int weight = Integer.valueOf(n1[1]);
            for (int i=0;i<weight;i++){
                list.add(n1[0]);
            }
        }
    }

    void  request(){
        int i = new Random().nextInt(list.size());
        System.out.println(list.get(i));
    }

    public static void main(String[] args) {
        WeightRandom wr = new WeightRandom("192.168.0.1#4,192.168.0.2#1");
        for (int i=0;i<9;i++){
            wr.request();
        }
    }
}
