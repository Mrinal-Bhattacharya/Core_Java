package com.thread.waitnotify;

import java.util.Scanner;

public class Processor {

    Object lock1 = new Object();
    Object lock2 = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("Producer thread running ....");
            lock1.wait();
            System.out.println("Resumed." + Thread.currentThread().getName());
        }
    }

    public void consume() throws InterruptedException {

        final Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (lock2) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            lock2.notify();
            Thread.sleep(5000);
        }
    }

    public static void main(final String[] args) throws InterruptedException {

        final Processor processor = new Processor();

        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer 1");
        final Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer 2");
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        t1.start();
        t2.start();
        t3.start();
    }
}
