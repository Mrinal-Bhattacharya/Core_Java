//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ProducerConsumerBlockingQueue {
    private static BlockingQueue<Integer> queue =
            new ArrayBlockingQueue<Integer>(10);
    static PriorityBlockingQueue<Integer> a=new PriorityBlockingQueue<>();
    

    public static void main(final String[] args) {
    	        final ProducerConsumerBlockingQueue producerConsumerBlockingQueue =
                new ProducerConsumerBlockingQueue();
        producerConsumerBlockingQueue.main();
    }

    public void main() {
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consumer();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }

    private void producer() throws InterruptedException {
        final Random random = new Random();

        while (true) {
            final int nextInt = random.nextInt(100);
            queue.put(nextInt);// Inserts the specified element at the tail of
                               // this queue, waiting for space to become
                               // available if the queue is full.
            System.out.println("Put value: " + nextInt + "; Queue size is: "
                               + queue.size());
        }
    }

    private void consumer() throws InterruptedException {
        final Random random = new Random();

        while (true) {
            Thread.sleep(100);

            if (random.nextInt(10) == 0) {
                final Integer value = queue.take();// Retrieves and removes the
                                                   // head of this queue,
                                                   // waiting if necessary until
                                                   // an element becomes
                                                   // available.

                System.out.println("Taken value: " + value
                                   + "; Queue size is: " + queue.size());
            }
        }
    }

}
