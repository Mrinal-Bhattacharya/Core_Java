//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.pool;

public class PoolThread extends Thread {

    private BlockingQueue taskQueue = null;
    private boolean isStopped = false;

    public PoolThread(final BlockingQueue queue) {
        taskQueue = queue;
    }

    @Override
    public void run() {
        while (!isStopped()) {
            try {
                final Runnable runnable = (Runnable)taskQueue.dequeue();
                runnable.run();
            } catch (final Exception e) {
                // log or otherwise report exception,
                // but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.interrupt(); // break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
