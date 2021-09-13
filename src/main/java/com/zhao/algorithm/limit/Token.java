package com.zhao.algorithm.limit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Token {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);
        System.out.println(semaphore.availablePermits());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (semaphore.availablePermits()<3){
                    semaphore.release();
                }
            }
        },1000,1000, TimeUnit.MILLISECONDS);

        Thread.sleep(5);

        for (int i=0;i<5;i++){
            semaphore.acquire();
            System.out.println("洪峰"+i);
        }

        for (int i=0;i<3;i++){
            Thread.sleep(1000);
            semaphore.acquire();
            System.out.println("日常"+i);
            Thread.sleep(1000);
        }

        for (int i=0;i<5;i++){
            semaphore.acquire();
            System.out.println("第二次洪峰"+i);
        }

        for (int i=0;i<5;i++){
            Thread.sleep(2000);
            System.out.println("令牌剩余"+semaphore.availablePermits());
        }
    }
}
