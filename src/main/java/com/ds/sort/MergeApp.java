//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.sort;

public class MergeApp {
    private long[] theArray = {23, 47, 81, 95, 7, 14, 39, 55, 62, 74};

    public static void main(final String[] args) {
        final int[] arrayA = {23, 47, 81, 95};
        final int[] arrayB = {7, 14, 39, 55, 62, 74};
        final int[] arrayC = new int[10];
        merge(arrayA, 4, arrayB, 6, arrayC);
        display(arrayC, 10);

        final MergeApp mergeApp = new MergeApp();
        final long[] workSpace;
        /* mergeApp.recMergeSort(workSpace, 0, mergeApp.theArray.length);
         display(workSpace, 10);*/

    } // end main()
      // -----------------------------------------------------------
      // merge A and B into C

    public static void merge(final int[] arrayA, final int sizeA,
            final int[] arrayB, final int sizeB, final int[] arrayC) {
        int aDex = 0, bDex = 0, cDex = 0;
        while (aDex < sizeA && bDex < sizeB)
            // neither array empty
            if (arrayA[aDex] < arrayB[bDex])
                arrayC[cDex++] = arrayA[aDex++];
            else
                arrayC[cDex++] = arrayB[bDex++];
        while (aDex < sizeA)
            // arrayB is empty,
            arrayC[cDex++] = arrayA[aDex++]; // but arrayA isn’t
        while (bDex < sizeB)
            // arrayA is empty,
            arrayC[cDex++] = arrayB[bDex++]; // but arrayB isn’t
    } // end merge()
      // -----------------------------------------------------------
      // display array

    private void recMergeSort(final long[] workSpace, final int lowerBound,
            final int upperBound) {
        if (lowerBound == upperBound) // if range is 1,
            return; // no use sorting
        else { // find midpoint
            final int mid = (lowerBound + upperBound) / 2;
            // sort low half
            recMergeSort(workSpace, lowerBound, mid);
            // sort high half
            recMergeSort(workSpace, mid + 1, upperBound);
            // merge them
            merge(workSpace, lowerBound, mid + 1, upperBound);
        } // end else
    }

    private void merge(final long[] workSpace, int lowPtr, int highPtr,
            final int upperBound) {
        int j = 0; // workspace index
        final int lowerBound = lowPtr;
        final int mid = highPtr - 1;
        final int n = upperBound - lowerBound + 1; // # of items
        while (lowPtr <= mid && highPtr <= upperBound)
            if (theArray[lowPtr] < theArray[highPtr])
                workSpace[j++] = theArray[lowPtr++];
            else
                workSpace[j++] = theArray[highPtr++];
        while (lowPtr <= mid)
            workSpace[j++] = theArray[lowPtr++];
        while (highPtr <= upperBound)
            workSpace[j++] = theArray[highPtr++];
        for (j = 0; j < n; j++)
            theArray[lowerBound + j] = workSpace[j];
    } // end merge()
      // -----------------------------------------------------------

    public static void display(final int[] theArray, final int size) {
        for (int j = 0; j < size; j++)
            System.out.print(theArray[j] + " ");
        System.out.println("");
    }

    public static void display(final long[] theArray, final int size) {
        for (int j = 0; j < size; j++)
            System.out.print(theArray[j] + " ");
        System.out.println("");
    }
    // -----------------------------------------------------------
}
