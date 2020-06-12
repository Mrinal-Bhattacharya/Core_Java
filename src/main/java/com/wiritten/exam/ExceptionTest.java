//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.wiritten.exam;

public class ExceptionTest {

    public static void main(final String[] args) {
        final Test1 test1 = new Test1();
        try {
            test1.doit();
        } catch (final MyException e) {
        }

        try {
            throw new NullPointerException();
        } catch (final ArithmeticException e) {
        }

    }

}

class Test1 {
    void doit() throws MyException {
        System.out.println("Greeting...");
        throw new MyException();
    }
}

class MyException extends Exception {

}
