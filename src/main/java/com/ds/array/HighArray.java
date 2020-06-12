//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.array;

public class HighArray {
    private long[] array;
    private int numberOfElements;

    public HighArray(final int maxSize) {
        this.array = new long[maxSize];
        this.numberOfElements = 0;
    }

    public void insert(final long value) {
        this.array[this.numberOfElements] = value;
        this.numberOfElements++;
    }

    public int find(final long value) {
        for (int i = 0; i < this.numberOfElements; i++) {
            if (this.array[i] == value)
                return i;
        }
        return -1;
    }

    public boolean delete(final long value) {
        int i = 0;
        for (; i < this.numberOfElements; i++) {
            if (this.array[i] == value) {
                break;
            }
        }
        if (i == this.numberOfElements) {
            return false;
        }
        for (int j = i; j < this.numberOfElements; j++) {
            this.array[j] = this.array[j + 1];
        }
        this.array[this.numberOfElements] = 0;
        this.numberOfElements--;
        return true;
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

        final int max = 100;
        final HighArray array = new HighArray(max);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        System.out.println("Size is " + array.size());
        System.out.println("Find 40 " + array.find(40));
        System.out.println("Find 20 " + array.find(20));
        System.out.println("Delete 40 " + array.delete(40));
        System.out.println("Size is " + array.size());
        array.display();
        System.out.println("Delete 20 " + array.delete(20));
        System.out.println("Size is " + array.size());
        array.display();
    }

    public long[] getArray() {

        return this.array;

    }
}
