//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    LinkedList<Integer> list = new LinkedList<Integer>();
    final int LIMIT = 20;
    Lock l1 = new ReentrantLock();
    Condition con = l1.newCondition();

    public void producer() {
        int value = 0;
        while (true) {
            l1.lock();
            try {
                while (list.size() == LIMIT) {
                    con.await();
                }
                list.add(++value);
                con.signal();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            } finally {
                l1.unlock();
            }
        }
    }

    public void consumer() {
        while (true) {
            l1.lock();
            try {
                while (list.size() == 0) {
                    con.await();
                }
                Thread.sleep(2000);
                System.out.println(list.removeFirst());
                con.signal();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            } finally {
                l1.unlock();
            }
        }
    }

    public static void main(final String[] args) {
        final Test t = new Test();
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                t.producer();
            }
        });
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                t.consumer();
            }
        });

        t1.start();
        t2.start();
    }
}
