//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Processor implements Runnable {
    private CyclicBarrier barrier;

    public Processor(final CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()
                           + " is waiting for all other threads to reach common barrier point");

        try {
            Thread.sleep(1000);
            /*
             * when all 3 parties will call await() method (i.e. common barrier point)
             * CyclicBarrrierEvent will be triggered and all waiting threads will be released.
             */
            barrier.await();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        } catch (final BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("As all threads have reached common barrier point "
                           + Thread.currentThread().getName()
                           + " has been released");
    }

    public static void main(final String[] args) throws InterruptedException,
            BrokenBarrierException {

        final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {

            @Override
            public void run() {
                System.out.println("done broken");
            }
        });
        //barrier.
        final Processor processor = new Processor(barrier);
        new Thread(processor, "Thread-1").start();
        new Thread(processor, "Thread-2").start();
        new Thread(processor, "Thread-3").start();

        Thread.sleep(1000);

        new Thread(processor, "Thread-4").start();
        new Thread(processor, "Thread-5").start();
        new Thread(processor, "Thread-6").start();
    }
}
