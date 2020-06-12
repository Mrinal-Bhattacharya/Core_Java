//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.weightedgraph;

public class Graph {
    private final int MAX_VERTS = 6;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][]; // adjacency matrix
    private int totalVerts; // current number of vertices
    private PriorityQ thePQ;

    public Graph() // constructor
    {
        this.vertexList = new Vertex[this.MAX_VERTS];
        // adjacency matrix
        this.adjMat = new int[this.MAX_VERTS][this.MAX_VERTS];
        this.totalVerts = 0;
        for (int j = 0; j < this.MAX_VERTS; j++)
            // set adjacency
            for (int k = 0; k < this.MAX_VERTS; k++)
                // matrix to 0
                this.adjMat[j][k] = this.INFINITY;
        this.thePQ = new PriorityQ();
    }

    public void addVertex(final char lab) {
        this.vertexList[this.totalVerts++] = new Vertex(lab);
    }

    public void addEdge(final int start, final int end, final int weight) {
        this.adjMat[start][end] = weight;
        this.adjMat[end][start] = weight;
    }

    public void displayVertex(final int v) {
        System.out.print(this.vertexList[v].label);
    }

    public void mstw() {
        int row = 0; // start at 0
        int startingVertsPos = 0; // number of verts in tree
        while (startingVertsPos < this.totalVerts - 1) // while not all verts in
        // tree
        { // put currentVert in tree
            this.vertexList[row].isInTree = true;
            startingVertsPos++;
            // insert edges adjacent to currentVert into PQ
            for (int column = 0; column < this.totalVerts; column++) // for each vertex, for a
                                                      // particular row column
                                                      // wise loop
            {
                if (column == row) // skip if it’s us
                    continue;
                if (this.vertexList[column].isInTree) // skip if in the tree
                    continue;
                final int distance = this.adjMat[row][column];
                if (distance == this.INFINITY) // skip if no edge
                    continue;
                putInPQ(row, column, distance); // put it in PQ (maybe)
            }
            if (this.thePQ.size() == 0) // no vertices in PQ?
            {
                System.out.println(" GRAPH NOT CONNECTED");
                return;
            }
            // remove edge with minimum distance, from PQ
            final Edge theEdge = this.thePQ.removeMin();
            final int sourceVert = theEdge.srcVert;
            row = theEdge.destVert;
            displayEdgeFromSourceToCurrent(sourceVert, row);
        } // end while(not all verts in tree)
          // mst is complete
        unMarkAllVertex();
    } // end mstw

    private void displayEdgeFromSourceToCurrent(final int sourceVert,
            final int targetVert) {
        // display edge from source to current
        System.out.print(this.vertexList[sourceVert].label);
        System.out.print(this.vertexList[targetVert].label);
        System.out.print(" ");
    }

    private void unMarkAllVertex() {
        for (int j = 0; j < this.totalVerts; j++)
            // unmark vertices
            this.vertexList[j].isInTree = false;
    }

    public void putInPQ(final int row, final int column,
            final int newDist) {
        // is there another edge with the same destination vertex?
        final int queueIndex = this.thePQ.findByDestination(column);
        if (queueIndex != -1) // got edge’s index
        {
            final Edge tempEdge = this.thePQ.peekN(queueIndex); // get edge
            final int oldDist = tempEdge.distance;
            if (oldDist > newDist) // if new edge shorter,
            {
                this.thePQ.removeN(queueIndex); // remove old edge
                final Edge theEdge = new Edge(row, column, newDist);
                this.thePQ.insert(theEdge); // insert new edge
            }
            // else no action; just leave the old vertex there
        } // end if
        else // no edge with same destination vertex
        { // so insert new one
            final Edge theEdge = new Edge(row, column, newDist);
            this.thePQ.insert(theEdge);
        }
    } // end putInPQ()

}
