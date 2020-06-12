//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package threadTest;

public class StaticThreadTest implements Runnable {
    public void run() {
        if (Thread.currentThread().getName().equals("Thread1")) {
            try {
                this.method1();
            } catch (final InterruptedException e) {
            }
        } else if (Thread.currentThread().getName().equals("Thread3")) {
            try {
                this.method3();
            } catch (final InterruptedException e) {
            }
        } else if (Thread.currentThread().getName().equals("Thread2")) {
            try {
                this.method2();
            } catch (final InterruptedException e) {
            }
        }
    }

    public synchronized void method1() throws InterruptedException {
        System.out.println("Method One enter");
        Thread.sleep(3000);
        for (int i = 0; i < 1000; i++) {

        }
        System.out.println("Method one exit");
    }

    public static synchronized void method2() throws InterruptedException {
        System.out.println("Method two enter");
        Thread.sleep(3000);
        for (int i = 0; i < 5000; i++) {
        }
        System.out.println("Method two exit");
    }

    public static synchronized void method3() throws InterruptedException {
        System.out.println("Method three Enter");
        Thread.sleep(3000);
        for (int i = 0; i < 100000; i++) {
        }
        System.out.println("Method three exit");
    }

    public static void main(final String[] args) {
        final StaticThreadTest r1 = new StaticThreadTest();
        final StaticThreadTest r2 = new StaticThreadTest();
        final StaticThreadTest r3 = new StaticThreadTest();
        final Thread t1 = new Thread(r1);
        t1.setName("Thread1");
        final Thread t2 = new Thread(r2);
        t2.setName("Thread2");
        final Thread t3 = new Thread(r1);
        t3.setName("Thread3");
        t3.start();
        t1.start();
        t2.start();

    }

}
