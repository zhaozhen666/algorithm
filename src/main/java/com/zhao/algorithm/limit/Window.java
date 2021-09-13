package com.zhao.algorithm.limit;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {
    final  int totalMax = 5;
    final  int sliceMax = 5;
    final  int slice = 3;
    final LinkedList<Long> linkedList = new LinkedList<>();
    Map<Long, AtomicInteger> map = new TreeMap<>();
    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    private Long getKey(){
        return System.currentTimeMillis()/1000;

    }

    public Window(){
        Long key = getKey();
        for (int i=0;i<slice;i++){
            linkedList.addFirst(key-i);
            map.put(key-i,new AtomicInteger(0));

        }
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                    Long key =getKey();
                    linkedList.addLast(key);
                    map.put(key,new AtomicInteger());

                    map.remove(linkedList.getFirst());
                    linkedList.removeFirst();

                System.out.println("step:"+key+":"+map);
            }
        },1000,1000, TimeUnit.MILLISECONDS);
    }

    public boolean checkCurrentSlice(){
        long key = getKey();
        AtomicInteger integer = map.get(key);
        if (integer!=null){
            return integer.get()<sliceMax;
        }
        return true;
    }

    public boolean checkAllCount(){
        return map.values().stream().mapToInt(value -> value.get()).sum()<totalMax;
    }

    public void  req(){
        Long key = getKey();
        while (linkedList.getLast()<key){
            try {
                Thread.sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (checkAllCount()&&checkCurrentSlice()){
            map.get(key).incrementAndGet();
            System.out.println(key+"=ok:"+map);
        }else {
            System.out.println(key+"=reject:"+map);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Window window = new Window();
        for (int i=0;i<10;i++){
            Thread.sleep(200);
            window.req();

        }
        Thread.sleep(3000);
        System.out.println("-------------------------");
        for (int i=0;i<10;i++){
            window.req();
        }
    }
}
