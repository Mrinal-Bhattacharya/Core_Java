//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.recursion;

public class TowersOfHanoi {
    static int nDisks = 3;

    public static void main(final String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }

    // -----------------------------------------------------------
    public static void doTowers(final int topN, final char from,
            final char inter, final char to) {
        if (topN == 1)
            System.out.println("Disk 1 from " + from + " to " + to);
        else {
            doTowers(topN - 1, from, to, inter); // from-->inter
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to); // inter-->to
        }
    }
    // ----------------------------------------------------------
} // end class TowersApp

