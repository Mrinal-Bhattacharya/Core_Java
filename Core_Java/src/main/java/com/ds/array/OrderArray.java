//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.array;

public class OrderArray {
    private long[] array;
    private int numberOfElements;

    public OrderArray(final int maxSize) {
        this.array = new long[maxSize];
        this.numberOfElements = 0;
    }

    public int find(final long value) {
        int lowerbound = 0;
        int upperbound = this.numberOfElements;
        while (true) {
            final int mid = (lowerbound + upperbound) / 2;
            if (this.array[mid] == value) {
                return mid + 1;
            } else if (lowerbound > upperbound) {
                return -1;
            } else {
                if (this.array[mid] > value) {
                    upperbound = mid - 1;
                } else {
                    lowerbound = mid + 1;
                }
            }
        }
    }

    public void insert(final long value) {
        int i = 0;
        for (; i < this.numberOfElements; i++) {
            if (this.array[i] > value) {
                break;
            }
        }
        for (int j = this.numberOfElements; j > i; j--) {
            this.array[j] = this.array[j - 1];
        }
        this.array[i] = value;
        this.numberOfElements++;
    }

    public void delete(final long value) {
        final int position = find(value) - 1;
        for (int i = position; i < this.numberOfElements; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.array[this.numberOfElements] = 0;
        this.numberOfElements--;
    }

    public void display() {
        for (int j = 0; j < this.numberOfElements; j++)
            System.out.print(this.array[j] + " ");
        System.out.println("");
    }

    public int size() {
        return this.numberOfElements;
    }

    public static void main(final String[] args) {
        final OrderArray orderArray = new OrderArray(10);
        orderArray.insert(11);
        orderArray.insert(22);
        orderArray.insert(33);
        orderArray.insert(44);
        orderArray.insert(34);
        orderArray.insert(15);
        orderArray.display();
        System.out.println(orderArray.find(34));
        orderArray.delete(34);
        orderArray.display();
    }
}
