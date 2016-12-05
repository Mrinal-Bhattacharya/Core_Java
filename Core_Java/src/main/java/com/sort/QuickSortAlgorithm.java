//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.sort;

import java.util.Arrays;

public class QuickSortAlgorithm {
    private int number;
    private int[] numbers;

    public void sort(final int[] values) {
        // check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(final int low, final int high) {

        System.out.println("Array " + Arrays.toString(this.numbers));
        System.out.println("low " + low + " High " + high);
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        final int pivot = numbers[low + (high - low) / 2];

        System.out.println("pivot no " + (low + (high - low) / 2)
                           + " pivot value " + pivot);

        // Divide into two lists
        while (i <= j) {
            System.out.println("Array in loop " + Arrays.toString(this.numbers));
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(final int i, final int j) {
        final int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(final String[] args) {
        /*int[] numbers;
        final int SIZE = 7;
        final int MAX = 20;
        numbers = new int[SIZE];
        final Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(MAX);
        }
        // System.out.println("Before sort " + Arrays.toString(numbers));
        final QuickSortAlgorithm quickSortAlgorithm = new QuickSortAlgorithm();
        quickSortAlgorithm.sort(numbers);
        System.out.println("After sort "
                           + Arrays.toString(quickSortAlgorithm.numbers));*/
        final int[] arr = {1, 12, 5, 26, 7, 14, 3, 7, 2};
        quickSort(arr, 0, 8);
        System.out.println(Arrays.toString(arr));

    }

    // Another method to do quick sort
    static int partition(final int[] arr, final int left, final int right) {
        int i = left, j = right;
        int tmp;
        final int pivot = arr[(right + left) / 2];
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    static void quickSort(final int[] arr, final int left, final int right) {
        final int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }
}
