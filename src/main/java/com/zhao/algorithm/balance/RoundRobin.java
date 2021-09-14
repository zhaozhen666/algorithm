package com.zhao.algorithm.balance;

import java.util.Random;

public class RoundRobin {
    class Server{
        Server prev;
        Server next;
        String name;
        public Server(String name){
            this.name =name;
        }
    }

    Server current;

    public RoundRobin(String serverName){
        System.out.println("init server list:"+serverName);
        String [] names =serverName.split(",");
        for (int i=0;i<names.length;i++){
            Server server = new Server(names[i]);
            if (current==null){
                this.current =server;
                current.prev = current;
                current.next = current;
            }else {
                addServer(names[i]);
            }
        }
    }

    void  addServer(String serverName){
        System.out.println("add Server :"+serverName);
        Server server = new Server(serverName);
        Server next = this.current.next;
        this.current.next=server;
        server.prev = this.current;
        server.next = next;
        next.prev =server;
    }

    void  remove(){
        System.out.println("remove current ="+current.name);
        this.current.prev.next = this.current.next;
        this.current.next.prev = this.current.prev;
        this.current = current.next;
    }

    void req(){
        System.out.println(this.current.name);
        this.current = current.next;
    }

    public static void main(String[] args) throws InterruptedException {
        RoundRobin rr = new RoundRobin("192.168.0.1,192.168.0.2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    rr.req();
                }
            }
        }).start();

        Thread.currentThread().sleep(3000);
        rr.addServer("192.168.0.3");

        Thread.currentThread().sleep(3000);
        rr.remove();
    }
}
