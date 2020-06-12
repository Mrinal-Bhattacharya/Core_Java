//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.sort;

public class SelectionSort {
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
        final SelectionSort selectionSort = new SelectionSort();
        selectionSort.display(array, 10);
        selectionSort.sort(array, 10);

    }

    private void sort(final long[] array, final int numberOfElements) {
        for (int outer = 0; outer < numberOfElements - 1; outer++) {
            int min = outer;
            for (int inner = outer + 1; inner < numberOfElements; inner++) {
                if (array[inner] < array[min]) {
                    min = inner;
                }
            }
            swap(outer, min, array);
        }
        System.out.println("");
        System.out.println("------------After Sort------------");
        display(array, numberOfElements);
    }

    private void display(final long[] array, final int numberOfElements) {
        for (int j = 0; j < numberOfElements; j++)
            System.out.print(array[j] + " ");
    }

    private void swap(final int one, final int two, final long[] array) {
        final long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }
}
