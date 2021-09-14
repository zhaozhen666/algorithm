package com.zhao.algorithm.balance;

import java.util.ArrayList;

public class Hash {
    ArrayList<String> ips;

    public Hash(String nodeNames){
        System.out.println("init server list:"+nodeNames);
        String [] nodes =nodeNames.split(",");
        ips = new ArrayList<>(nodes.length);
        for (String node :nodes){
            ips.add(node);
        }
    }

    void addNode(String nodeName){
        System.out.println("add node :"+nodeName);
        ips.add(nodeName);
    }

    void  remove(String nodeName){
        System.out.println("remove node:"+nodeName);
        ips.remove(nodeName);
    }

    private  int hash(String ip){
        int last = Integer.valueOf(ip.substring(ip.lastIndexOf(".")+1,ip.length()));
        return last%ips.size();
    }

    void req(String ip){
        int i = hash(ip);
        System.out.println(ip+"----->"+ips.get(i));
    }

    public static void main(String[] args) {
        Hash hash = new Hash("192.168.0.1,192.168.0.2");
        for (int i=0;i<10;i++){
            String ip = "192.168.1."+i;
            hash.req(ip);
        }
        hash.addNode("192.168.0.3");
        for (int i=0;i<10;i++){
            String ip = "192.168.1."+i;
            hash.req(ip);
        }
    }
}
