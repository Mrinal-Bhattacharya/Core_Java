//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

public class OverLoadTest {

    public static void methodA(final Object a) {
        System.out.println("Object");
    }

    public static void methodA(final String a) {
        System.out.println("String");
    }

    // public static void methodA(final Integer a) {
    // System.out.println("Iinteger");
    // }

    public static void main(final String[] args) {
        final Object z = null;
        OverLoadTest.methodA(z);// object
        OverLoadTest.methodA(null);// String method
        // if u have integer method also then compilation error.
    }
}
