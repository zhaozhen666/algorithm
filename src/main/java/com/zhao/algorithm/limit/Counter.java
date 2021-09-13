package com.zhao.algorithm.limit;

import java.util.concurrent.*;

public class Counter {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                semaphore.release(3);
            }
        }, 3000, 3000, TimeUnit.MILLISECONDS);

        while (true){
            try {
                semaphore.acquire();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("ok");
        }
    }
}
