//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.annotation;

public class TestLoggerClass {

    @Logger
    public void method1() {
        System.out.println("method 1");
    }

    public void method2() {
        System.out.println("method 2");
    }

    public static void main(final String[] args) {
        final TestLoggerClass loggerClass = new TestLoggerClass();
        loggerClass.method1();
        loggerClass.method2();
    }
}
