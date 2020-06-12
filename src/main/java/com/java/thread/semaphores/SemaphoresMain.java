//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoresMain {
    public static void main(final String[] args) throws Exception {

        final ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
        	
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
            System.out.println(i);
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}
