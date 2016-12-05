//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.weightedgraph;

public class Vertex {
    public char label; // label (e.g. ‘A’)
    public boolean isInTree;

    public Vertex(final char lab) // constructor
    {
        this.label = lab;
        this.isInTree = false;
    }
}
