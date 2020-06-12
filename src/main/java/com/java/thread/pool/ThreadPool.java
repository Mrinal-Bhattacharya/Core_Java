//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.pool;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public ThreadPool(final int noOfThreads, final int maxNoOfTasks) {
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for (int i = 0; i < noOfThreads; i++) {
            threads.add(new PoolThread(taskQueue));
        }
        for (final PoolThread thread : threads) {
            thread.start();
        }
    }

    public synchronized void execute(final Runnable task) throws Exception {
        if (this.isStopped)
            throw new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop() {
        this.isStopped = true;
        for (final PoolThread thread : threads) {
            thread.doStop();
        }
    }

}
