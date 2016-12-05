//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.test.sort;

public class DualPivotQuicksort {

    public static void sort(final int[] a) {
        sort(a, 0, a.length);
    }

    public static void sort(final int[] a, final int fromIndex,
            final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        dualPivotQuicksort(a, fromIndex, toIndex - 1, 3);
    }

    private static void rangeCheck(final int length, final int fromIndex,
            final int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex
                                               + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > length) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    private static void swap(final int[] a, final int i, final int j) {
        final int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void dualPivotQuicksort(final int[] a, final int left,
            final int right, int div) {
        final int len = right - left;

        if (len < 4) { // insertion sort for tiny array
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; j > left && a[j] < a[j - 1]; j--) {
                    swap(a, j, j - 1);
                }
            }
            return;
        }
        final int third = len / div;

        // "medians"
        int m1 = left + third;
        int m2 = right - third;

        if (m1 <= left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (a[m1] < a[m2]) {
            swap(a, m1, left);
            swap(a, m2, right);
        } else {
            swap(a, m1, right);
            swap(a, m2, left);
        }
        // pivots
        final int pivot1 = a[left];
        final int pivot2 = a[right];

        // pointers
        int less = left + 1;
        int great = right - 1;

        // sorting
        for (int k = less; k <= great; k++) {
            if (a[k] < pivot1) {
                swap(a, k, less++);
            } else if (a[k] > pivot2) {
                while (k < great && a[great] > pivot2) {
                    great--;
                }
                swap(a, k, great--);

                if (a[k] < pivot1) {
                    swap(a, k, less++);
                }
            }
        }
        // swaps
        final int dist = great - less;

        if (dist < 13) {
            div++;
        }
        swap(a, less - 1, left);
        swap(a, great + 1, right);

        // subarrays
        dualPivotQuicksort(a, left, less - 2, div);
        dualPivotQuicksort(a, great + 2, right, div);

        // equal elements
        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (a[k] == pivot1) {
                    swap(a, k, less++);
                } else if (a[k] == pivot2) {
                    swap(a, k, great--);

                    if (a[k] == pivot1) {
                        swap(a, k, less++);
                    }
                }
            }
        }
        // subarray
        if (pivot1 < pivot2) {
            dualPivotQuicksort(a, less, great, div);
        }
    }

    public static void main(final String arg[]) {
        final int a[] = new int[] {96, 85, 43, 70, 56};
        /*for (int i = 0; i < a.length; i++) {
            a[i] = (int)((Math.random() * (100 - 10)) + 10);
        }*/
        final DualPivotQuicksort dualPivotQuicksort = new DualPivotQuicksort();
        dualPivotQuicksort.sort(a);
    }
}
