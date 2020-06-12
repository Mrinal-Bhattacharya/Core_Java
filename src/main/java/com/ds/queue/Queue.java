//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.queue;

public class Queue {
    private long[] array;
    private int front;
    private int rear;

    public Queue(final int maxSize) {
        this.array = new long[maxSize];
        this.front = 0;
        this.rear = -1;
    }

    public static void main(final String[] args) {
        final Queue queue = new Queue(2);
        queue.insert(10);
        queue.insert(20);
        queue.display();
        System.out.println();
        long result = queue.delete();
        System.out.println(result);
        result = queue.delete();
        System.out.println(result);
        result = queue.delete();
        System.out.println(result);
        queue.insert(30);
        queue.display();
    }

    private long delete() {
        long result = -1;
        if (rear != -1) {
            result = this.array[front++];
            if (front == this.array.length) {
                front = 0;
                rear = -1;
            }
        }
        return result;
    }

    private void insert(final int i) {
        if (this.array.length - 1 > rear)
            this.array[++rear] = i;
    }

    private void display() {
        for (int j = 0; j <= this.rear; j++)
            System.out.print(array[j] + " ");
    }
}
