//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.lock;

import java.util.concurrent.Exchanger;

public class FillAndEmpty {

    public static void main(final String[] args) {
        new FillAndEmpty().start();
    }

    Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
    DataBuffer initialEmptyBuffer = new DataBuffer();
    DataBuffer initialFullBuffer = new DataBuffer();
    int countDown = 10;

    class FillingLoop implements Runnable {
        public void run() {
            DataBuffer currentBuffer = initialEmptyBuffer;
            try {
                while (currentBuffer != null && countDown > 0) {
                    addToBuffer(currentBuffer);
                    // Thread.sleep(1000);
                    // System.out.println("Add to buffer");
                    if (currentBuffer.isFull()) {
                        // System.out.println("write buffer is full");
                        currentBuffer = exchanger.exchange(currentBuffer);
                        // System.out.println("write buffer exchange");
                    }
                }
            } catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private void addToBuffer(final DataBuffer<Integer> currentBuffer) {
            currentBuffer.put(countDown--);
        }
    }

    class EmptyingLoop implements Runnable {
        public void run() {
            DataBuffer currentBuffer = initialFullBuffer;
            try {
                while (currentBuffer != null) {
                    takeFromBuffer(currentBuffer);
                    // System.out.println("read from buffer");
                    if (currentBuffer.isEmpty()) {
                        // System.out.println("buffer is Empty waiting for full buffer");
                        currentBuffer = exchanger.exchange(currentBuffer);
                        // System.out.println("read full buffer");

                    }
                }
            } catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private void takeFromBuffer(final DataBuffer<Integer> currentBuffer) {
            System.out.println(currentBuffer.get());
        }
    }

    void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptyingLoop()).start();
    }

    class DataBuffer<T> {

        T data = null;

        public boolean isFull() {
            return data != null;
        }

        public boolean isEmpty() {
            return data == null;
        }

        public T get() {
            final T d = data;
            data = null;
            return d;
        }

        public void put(final T data) {
            this.data = data;
        }
    }

}
