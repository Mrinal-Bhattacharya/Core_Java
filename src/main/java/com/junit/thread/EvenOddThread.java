//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.junit.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddThread {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private boolean isEven = false;
    private final int limit = 20;
    Object lock = new Object();

    public void printEven() throws InterruptedException {
        while (atomicInteger.get() <= limit) {
            synchronized (lock) {
                while (!isEven) {
                    lock.wait();
                }
                if (atomicInteger.get() < limit) {
                    System.out.println("Even "
                                       + atomicInteger.incrementAndGet());
                    isEven = false;
                    lock.notifyAll();
                }
            }
        }
    }

    public void printOdd() throws InterruptedException {
        while (atomicInteger.get() <= limit) {
            synchronized (lock) {
                while (isEven) {
                    lock.wait();
                }
                if (atomicInteger.get() < limit) {
                    System.out.println("Odd " + atomicInteger.incrementAndGet());
                    isEven = true;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(final String[] args) {
        final EvenOddThread evenOddThread = new EvenOddThread();
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    evenOddThread.printEven();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    evenOddThread.printOdd();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    evenOddThread.printEven();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread t4 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    evenOddThread.printOdd();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        final long s1 = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        while (evenOddThread.atomicInteger.get() != 20) {

        }
        final long s2 = System.currentTimeMillis();

        System.out.println("done " + (s2 - s1));
    }
}
