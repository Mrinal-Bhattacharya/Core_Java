//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Worker {
    private AtomicInteger count = new AtomicInteger(0);

    public void run() {
        final Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet();
                }
            }
        });
        thread1.start();

        final Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet();
                }
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }

    public static void main(final String[] args) {
        final Worker worker = new Worker();
        worker.run();
    }
}
