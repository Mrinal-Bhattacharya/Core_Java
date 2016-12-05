//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    public void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }

    }

    public void stageTwo() {

        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }

    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting ...");

        final long start = System.currentTimeMillis();
        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Runnable() {
            public void run() {
                process();
            }
        });
        service.submit(new Runnable() {
            public void run() {
                process();
            }
        });

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (final InterruptedException e) {
        }

        final long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: "
                           + list2.size());
    }

    public static void main(final String[] args) {
        final Worker workerObject = new Worker();
        workerObject.main();
    }
}
