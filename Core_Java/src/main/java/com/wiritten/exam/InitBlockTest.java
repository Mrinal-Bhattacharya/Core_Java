//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

public class InitBlockTest {
    public static void main(final String[] args) {
        final A11 b = new B11();
    }
}

class A11 {
    static {
        System.out.println("A");
    }
    {
        System.out.println("B");
    }

    A11() {
        System.out.println("C");
    }
}

class B11 extends A11 {
    static {
        System.out.println("D");
    }
    {
        System.out.println("E");
    }

    B11() {
        System.out.println("F");
    }
}
