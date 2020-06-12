package com.ds.stack;

//****************************************************************
//* Copyright (c) 2015. All Rights Reserved.
//****************************************************************

public class Stack {
    private long[] array;
    private int top;

    public Stack(final int maxSize) {
        this.array = new long[maxSize];
        this.top = -1;
    }

    public long peek() {
        return this.array[this.top];
    }

    public boolean isEmpty() {
        return (this.top == -1);
    }

    public long pop() {
        return this.array[this.top--];
    }

    public void push(final int element) {
        this.array[++this.top] = element;
    }

    public boolean isFull() {
        return this.top < this.array.length;
    }
}
