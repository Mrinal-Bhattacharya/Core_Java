//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.sort;

public class InsertionSort {
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
        final InsertionSort insertionSort = new InsertionSort();
        insertionSort.display(array, 10);
        insertionSort.sort(array, 10);
    }

    private void sort(final long[] array, final int numberOfElements) {
        for (int outer = 1; outer < numberOfElements; outer++) {
            final long temp = array[outer];
            int inner = outer;
            while (inner > 0 && array[inner - 1] >= temp) {
                // array[inner] = array[inner - 1]; shift item to right and it
                // will always copy left hand side value if left hand side value
                // is greater
                // if we have array 00|44|77|33 and our temp value is 33 then
                // this inner loop goto last left hand side value. if any value
                // is greater than
                // for example after first inner data will looks like
                // 00|44|77|77
                array[inner] = array[inner - 1]; // shift item to right and it
                --inner;
            }
            array[inner] = temp;
        }
        System.out.println("");
        System.out.println("------------After Sort------------");
        display(array, numberOfElements);
    }

    private void display(final long[] array, final int numberOfElements) {
        for (int j = 0; j < numberOfElements; j++)
            System.out.print(array[j] + " ");
    }
}
