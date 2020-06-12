//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

public class InhertiaceTest {
    public static void main(final String[] args) {
        final A obj1 = new C();
        System.out.println(obj1.x);
        final B obj2 = new B();
        System.out.println(obj2.x);
        A obj3 = obj1;
        obj3.doit();
        System.out.println(obj3.x);
        obj3 = obj2;
        obj3.doit();
        System.out.println(obj3.x);
        // obj3.call() it will not compile
        final String s = "hello";
        final Object o = s;
        if (o.equals(s)) {
            System.out.println("AA");
        } else {
            System.out.println("BB");
        }
        if (s.equals(o)) {
            System.out.println("CC");
        } else {
            System.out.println("DD");
        }
        final A a1 = new A();
        final C c1 = (C)a1;// throw runtime exception

    }
}

class A {
    int x = 6;

    A() {

    }

    A(final int x) {

    }

    void doit() {
        System.out.println("A");
    }

}

class B extends A {
    int x = 7;
    private int z;

    B() {

    }

    B(final int x) {

    }

    @Override
    void doit() {
        System.out.println("B");
    }

    void call() {

    }
}

class C extends B {
    int x = 8;

    @Override
    void doit() {
        // super.z;
        System.out.println("C");
    }
}

class Test {
    public static void main(final String[] args) {
        int i = 1;
        switch (i) {
        case 1:
            System.out.println("one");
        case 2:
            System.out.println("two");
        case 3:
            System.out.println("three");
        }
        i = 0;
    }
}

// http://www.withoutbook.com/OnlineTest.php
interface Foo141 {
    int k = 0;
}

class Test141 implements Foo141 {
    public static void main(final String args[]) {
        int i;
        final int result = 6 / 2 * 3;
        System.out.println(result);
        final Test141 test141 = new Test141();
        i = test141.k;
        i = Test141.k;
        i = Foo141.k;
    }
}
