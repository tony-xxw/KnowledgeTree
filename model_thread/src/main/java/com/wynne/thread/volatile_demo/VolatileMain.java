package com.wynne.thread.volatile_demo;


import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

class VolatileMain implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new VolatileMain();
        Thread thread = new Thread(r);
        Thread thread1 = new Thread(r);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.printf("a " + ((VolatileMain)r).a);
        System.out.printf("AtomicInteger " + ((VolatileMain)r).realA.get());

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111111");
            }
        });
        thread2.start();
        Thread.sleep(500);
        System.out.println("state " + thread2.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}