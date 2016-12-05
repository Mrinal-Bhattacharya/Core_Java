//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.puzzel;

public class Puzzel1 {

    /**
     * TODO - Place method description here
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final String METHOD_NAME = "main";
        final int a = -7;
        System.out.println(isOdd(a));
        System.out.println(isOddSolution2(a));
        System.out.println(isOddSolution3(a));

    }

    // Negative values will not come properly
    private static boolean isOdd(final int i) {
        return i % 2 == 1;
    }

    private static boolean isOddSolution1(final int i) {
        return i % 2 != 0;
    }

    private static boolean isOddSolution2(final int i) {
        return (i & 1) != 0;
    }

    private static boolean isOddSolution3(final int i) {
        return (i & 1) == 1;
    }
}
