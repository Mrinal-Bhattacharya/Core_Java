//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    private String data;

    public String getData() {
        r.lock();
        try {
            return data;
        } finally {
            r.unlock();
        }
    }

    public void setData(final String data) {
        w.lock();
        try {
            this.data = data;
        } finally {
            w.unlock();
        }
    }

    public static void main(final String[] args) {
        final ReentrantReadWriteLockExample example =
                new ReentrantReadWriteLockExample();
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    example.setData("Data " + i);
                    System.out.println("t1 Set Data " + i);
                }
            }
        });

        final Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    example.setData("Data " + i);
                    System.out.println("t4 Set Data " + i);
                }
            }
        });
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("getting data t2 " + example.getData());
                }
            }
        });

        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("getting data t3 " + example.getData());
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
