//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

public class TreadWrittenTest {
    public static void main(final String[] args) {

        final char a = '3';
        final char b = '1';
        System.out.println(a - b);
        final T1 t1 = new T1();
        try {
            Thread.currentThread().join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }

}

// http://stackoverflow.com/questions/3068912/what-is-the-most-used-pattern-in-java-io
class T1 {
    Thread t = null;

    T1() {
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("hi1");
                    t.join(1000);// join itself so deadlock
                    System.out.println("test");
                } catch (final InterruptedException e) {
                }
            }
        });
        t.start();
    }
}
