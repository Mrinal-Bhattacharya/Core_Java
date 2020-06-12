//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.recursion;

public class Recursion {
    public static void main(final String[] args) {
        System.out.println(triangle(5));
        System.out.println(triangleReality(5));
        System.out.println(factorial(5));

    }

    // The condition that leads to a recursive method returning without making
    // another
    // recursive call is referred to as the base case. It’s critical that every
    // recursive method
    // have a base case to prevent infinite recursion and the consequent demise
    // of the
    // program.
    private static int triangle(final int i) {
        if (i == 1)
            return 1;
        return (i + triangle(i - 1));

    }

    public static int triangleReality(final int n) {
        System.out.println("Entering: n=" + n);
        if (n == 1) {
            System.out.println("Returning 1");
            return 1;
        } else {
            final int temp = n + triangleReality(n - 1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }

    private static int factorial(final int x) {
        if (x == 0) {
            return 1;
        }
        return (x * factorial(x - 1));
    }
}
