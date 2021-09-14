package com.zhao.algorithm.balance;

import java.util.ArrayList;
import java.util.Random;

public class RandomBalance {
    ArrayList<String> ips;
    public RandomBalance(String nodeNames){
        System.out.println("init list:"+nodeNames);
        String [] nodes = nodeNames.split(",");
        ips = new ArrayList<>(nodes.length);
        for (String node:nodes){
            ips.add(node);
        }
    }

    void  req(){
        int i = new Random().nextInt(ips.size());
        System.out.println(ips.get(i));
    }
    void addNode(String nodeName){
        System.out.println("add Node :"+nodeName);
        ips.add(nodeName);
    }

    void remove(String nodeName){
        System.out.println("remove node : "+nodeName);
        ips.remove(nodeName);
    }

    public static void main(String[] args) throws InterruptedException {
        RandomBalance random = new RandomBalance("192.168.0.1,192.168.0.2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(200);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    random.req();
                }
            }
        }).start();
        Thread.currentThread().sleep(3000);
        random.addNode("192.168.0.3");

        Thread.currentThread().sleep(3000);
        random.remove("192.168.0.2");
    }
}
