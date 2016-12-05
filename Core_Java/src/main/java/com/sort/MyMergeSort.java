//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.sort;

public class MyMergeSort {

    private void merge(final int[] left, final int[] right, final int[] orignal) {
        final int nL = left.length;
        final int nR = right.length;
        int leftArrUnpickedElement = 0;
        int rightArrUnpickedElement = 0;
        int orignalArrIndex = 0;

        while (leftArrUnpickedElement < nL && rightArrUnpickedElement < nR) {
            if (left[leftArrUnpickedElement] <= right[rightArrUnpickedElement]) {
                orignal[orignalArrIndex] = left[leftArrUnpickedElement];
                leftArrUnpickedElement++;
            } else {
                orignal[orignalArrIndex] = right[rightArrUnpickedElement];
                rightArrUnpickedElement++;
            }
            orignalArrIndex++;
        }
        while (leftArrUnpickedElement < nL) {
            orignal[orignalArrIndex] = left[leftArrUnpickedElement];
            leftArrUnpickedElement++;
            orignalArrIndex++;
        }
        while (rightArrUnpickedElement < nL) {
            orignal[orignalArrIndex] = right[rightArrUnpickedElement];
            rightArrUnpickedElement++;
            orignalArrIndex++;
        }

    }

    private void mergeSort(final int[] orignal) {
        final int lenghtOrignalArr = orignal.length;
        if (lenghtOrignalArr < 2) {
            return;
        }
        final int mid = lenghtOrignalArr / 2;
        final int[] left = new int[mid];
        final int[] right = new int[lenghtOrignalArr - mid];
        for (int i = 0; i <= mid - 1; i++) {
            left[i] = orignal[i];
        }
        for (int i = mid; i <= lenghtOrignalArr - 1; i++) {
            right[i - mid] = orignal[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(left, right, orignal);
    }

    public static void main(final String[] args) {
        final int[] arr = {12, 11, 23, 1, 5, 7, 44, 2};
        final MyMergeSort myMergeSort = new MyMergeSort();
        myMergeSort.mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
