//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.prep.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerThread {
    public static void main(final String[] args) {
        final Data data = new Data();
        final Producer producer = new Producer(data);
        final Consumer consumer = new Consumer(data);
    }
}

class Consumer implements Runnable {

    private Data data;

    public Consumer(final Data data) {
        this.data = data;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Get Data " + data.getData());
        }
    }
}

class Producer implements Runnable {
    Data data;

    public Producer(final Data data) {
        this.data = data;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            data.addData("" + i);
        }
    }
}

class Data {
    List<String> list = new ArrayList<String>();

    void addData(final String value) {
        synchronized (list) {
            System.out.println("put data: " + value);
            list.add(value);
            list.notify();
            while (list.size() > 4) {
                try {
                    list.wait();
                } catch (final InterruptedException e) {
                }
            }
        }
    }

    String getData() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    list.wait();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final String result = list.remove(list.size() - 1);
            list.notify();
            return result;
        }
    }
}
