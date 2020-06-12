//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.lock;

public class Test2 {

    // Obj1 and Obj2
    // Obj1-->t1,t2,t4
    // t1-->static method
    // t2-->object method
    // t4-->object method
    // t1 and t2 will work simultaneously. even lock on static method.
    // Obj2-->t3,t4
    // t3--> object method
    //
    public static void main(final String[] args) {
        final Test2 obj1 = new Test2();
        final Test2 obj2 = new Test2();
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj1.testing();
            }
        }, "t1");
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj2.testing2();
            }
        }, "t2");
        final Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj2.testing3();
            }
        }, "t3");
        final Thread t4 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj1.testing2();
            }
        }, "t4");
        t1.start();
        t2.start();
       // t3.start();
       // t4.start();
    }

    public synchronized static void testing() {
        for (int i = 0; i < 100; i++) {
            System.out.println("testing static "
                               + Thread.currentThread().getName() + " " + i);
        }
    }

    public synchronized void testing2() {
        for (int i = 0; i < 100; i++) {
            System.out.println("testing2 object "
                               + Thread.currentThread().getName() + " " + i);
        }
    }

    public synchronized void testing3() {
        for (int i = 0; i < 100; i++) {
            System.out.println("testing3 object "
                               + Thread.currentThread().getName() + " " + i);
        }
    }
}
