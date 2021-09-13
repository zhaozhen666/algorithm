package com.zhao.algorithm.limit;

import java.util.concurrent.*;

public class Barrel {
    public static void main(String[] args) {
        final LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>(3);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int v =queue.poll();
                System.out.println("处理"+v);
            }
        },2000,2000, TimeUnit.MILLISECONDS);
        int i=0;
        while (true){
            i++;
            try {
                System.out.println("put"+i);
                //如果1秒内无法进入队列就会被丢弃
                queue.offer(i,1000,TimeUnit.MILLISECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
