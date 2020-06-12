//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.weightedgraph;

public class Edge {
    public int srcVert; // index of a vertex starting edge
    public int destVert; // index of a vertex ending edge
    public int distance; // distance from src to dest

    public Edge(final int sv, final int dv, final int d) // constructor
    {
        this.srcVert = sv;
        this.destVert = dv;
        this.distance = d;
    }
}
