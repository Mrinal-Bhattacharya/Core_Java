//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.sort;

public class BubbleSort {

    private void sort(final long[] array, final int numberOfElements) {
        for (int right = numberOfElements - 1; right > 1; right--) {
            for (int left = 0; left < right; left++) {//
                if (array[left] > array[left + 1]) {
                    swap(left, left + 1, array);
                }
            }
        }
        System.out.println("");
        System.out.println("------------After Sort------------");
        display(array, numberOfElements);

    }

    private void swap(final int one, final int two, final long[] array) {
        final long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public static void main(final String[] args) {
        final long[] array = new long[100]; // create the array
        array[0] = 77; // insert 10 items
        array[1] = 99;
        array[2] = 44;
        array[3] = 55;
        array[4] = 22;
        array[5] = 88;
        array[6] = 11;
        array[7] = 00;
        array[8] = 66;
        array[9] = 33;
        final BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.display(array, 10);
        bubbleSort.sort(array, 10);

    }

    private void display(final long[] array, final int numberOfElements) {
        for (int j = 0; j < numberOfElements; j++)
            System.out.print(array[j] + " ");
    }
}
