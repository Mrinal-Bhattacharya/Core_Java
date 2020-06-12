//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.weightedgraph;

public class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private final int SIZE = 20;
    private Edge[] queArray;
    private int size;

    public PriorityQ() // constructor
    {
        this.queArray = new Edge[this.SIZE];
        this.size = 0;
    }

    public void insert(final Edge item) // insert item in sorted order
    {
        int j;
        for (j = 0; j < this.size; j++)
            // find place to insert
            if (item.distance >= this.queArray[j].distance)
                break;
        for (int k = this.size - 1; k >= j; k--)
            // move items up
            this.queArray[k + 1] = this.queArray[k];
        this.queArray[j] = item; // insert item
        this.size++;
    }

    public Edge removeMin() // remove minimum item
    {
        this.size = this.size - 1;
        final Edge temp = this.queArray[this.size];
        this.queArray[this.size] = null;
        return temp;
    }

    public void removeN(final int n) // remove item at n
    {
        for (int j = n; j < this.size - 1; j++)
            // move items down
            this.queArray[j] = this.queArray[j + 1];
        this.size--;
    }

    public Edge peekMin() // peek at minimum item
    {
        return this.queArray[this.size - 1];
    }

    public int size() // return number of items
    {
        return this.size;
    }

    public boolean isEmpty() // true if queue is empty
    {
        return (this.size == 0);
    }

    // -------------------------------------------------------------
    public Edge peekN(final int n) // peek at item n
    {
        return this.queArray[n];
    }

    public int findByDestination(final int findDex) // find item with specified
    { // destVert value
        for (int j = 0; j < this.size; j++)
            if (this.queArray[j].destVert == findDex)
                return j;
        return -1;
    }
}
