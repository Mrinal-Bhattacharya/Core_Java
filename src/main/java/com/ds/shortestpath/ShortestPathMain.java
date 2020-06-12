//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.shortestpath;

class DistPar // distance and parent
{ // items stored in sPath array
    public int distance; // distance from start to this vertex
    public int parentVert; // current parent of this vertex

    // -------------------------------------------------------------

    public DistPar(final int pv, final int d) // constructor
    {
        this.distance = d;
        this.parentVert = pv;
    }
} // end class DistPar
// /////////////////////////////////////////////////////////////

class Vertex {
    public char label; // label (e.g. ‘A’)
    public boolean isInTree;

    // -------------------------------------------------------------
    public Vertex(final char lab) // constructor
    {
        this.label = lab;
        this.isInTree = false;
    }
    // -------------------------------------------------------------
} // end class Vertex
// //////////////////////////////////////////////////////////////

class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][]; // adjacency matrix
    private int nVerts; // current number of vertices
    private int nTree; // number of verts in tree
    private DistPar sPath[]; // array for shortest-path data
    private int currentVert; // current vertex
    private int startToCurrent; // distance to currentVert

    // -------------------------------------------------------------

    public Graph() // constructor
    {
        this.vertexList = new Vertex[this.MAX_VERTS];
        // adjacency matrix
        this.adjMat = new int[this.MAX_VERTS][this.MAX_VERTS];
        this.nVerts = 0;
        this.nTree = 0;
        for (int j = 0; j < this.MAX_VERTS; j++)
            // set adjacency
            for (int k = 0; k < this.MAX_VERTS; k++)
                // matrix
                this.adjMat[j][k] = this.INFINITY; // to infinity
        this.sPath = new DistPar[this.MAX_VERTS]; // shortest paths
    } // end constructor
      // -------------------------------------------------------------

    public void addVertex(final char lab) {
        this.vertexList[this.nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(final int start, final int end, final int weight) {
        this.adjMat[start][end] = weight; // (directed)
    }

    // -------------------------------------------------------------
    public void path() // find all shortest paths
    {
        final int startTree = 0; // start at vertex 0
        this.vertexList[startTree].isInTree = true;
        this.nTree = 1; // put it in tree
        // transfer row of distances from adjMat to sPath
        for (int j = 0; j < this.nVerts; j++) {
            final int tempDist = this.adjMat[startTree][j];
            this.sPath[j] = new DistPar(startTree, tempDist);
        }
        // until all vertices are in the tree
        while (this.nTree < this.nVerts) {
            final int indexMin = getMin(); // get minimum from sPath
            final int minDist = this.sPath[indexMin].distance;
            if (minDist == this.INFINITY) // if all infinite
            { // or in tree,
                System.out.println("There are unreachable vertices");
                break; // sPath is complete
            } else { // reset currentVert
                this.currentVert = indexMin; // to closest vert
                this.startToCurrent = this.sPath[indexMin].distance;
                // minimum distance from startTree is
                // to currentVert, and is startToCurrent
            }
            // put current vertex in tree
            this.vertexList[this.currentVert].isInTree = true;
            this.nTree++;
            adjust_sPath(); // update sPath[] array
        } // end while(nTree<nVerts)
        displayPaths(); // display sPath[] contents
        this.nTree = 0; // clear tree
        for (int j = 0; j < this.nVerts; j++)
            this.vertexList[j].isInTree = false;
    } // end path()

    // -------------------------------------------------------------
    public int getMin() // get entry from sPath
    { // with minimum distance
        int minDist = this.INFINITY; // assume minimum
        int indexMin = 0;
        for (int j = 1; j < this.nVerts; j++) // for each vertex,
        { // if it’s in tree and
            if (!this.vertexList[j].isInTree && // smaller than old one
                this.sPath[j].distance < minDist) {
                minDist = this.sPath[j].distance;
                indexMin = j; // update minimum
            }
        } // end for
        return indexMin; // return index of minimum
    } // end getMin()

    // -------------------------------------------------------------
    public void adjust_sPath() {
        // adjust values in shortest-path array sPath
        int column = 1; // skip starting vertex
        while (column < this.nVerts) // go across columns
        {
            // if this column’s vertex already in tree, skip it
            if (this.vertexList[column].isInTree) {
                column++;
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            final int currentToFringe = this.adjMat[this.currentVert][column];
            // add distance from start
            final int startToFringe = this.startToCurrent + currentToFringe;
            // get distance of current sPath entry
            final int sPathDist = this.sPath[column].distance;
            // compare distance from start with sPath entry
            if (startToFringe < sPathDist) // if shorter,
            { // update sPath
                this.sPath[column].parentVert = this.currentVert;
                this.sPath[column].distance = startToFringe;
            }
            column++;
        } // end while(column < nVerts)
    } // end adjust_sPath()
      // -------------------------------------------------------------

    public void displayPaths() {
        for (int j = 0; j < this.nVerts; j++) // display contents of sPath[]
        {
            System.out.print(this.vertexList[j].label + "="); // B=
            if (this.sPath[j].distance == INFINITY)
                System.out.print("inf"); // inf
            else
                System.out.print(this.sPath[j].distance); // 50
            final char parent = this.vertexList[this.sPath[j].parentVert].label;
            System.out.print("(" + parent + ") "); // (A)
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
} // end class Graph

public class ShortestPathMain {
    public static void main(final String[] args) {

        final Graph theGraph = new Graph();
        theGraph.addVertex('A'); // 0 (start)
        theGraph.addVertex('B'); // 2
        theGraph.addVertex('C'); // 1
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 3, 80); // AD 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 4, 40); // CE 40
        theGraph.addEdge(3, 2, 20); // DC 20
        theGraph.addEdge(3, 4, 70); // DE 70
        theGraph.addEdge(4, 1, 50); // EB 50
        System.out.println("Shortest paths");
        theGraph.path(); // shortest paths
        System.out.println();
    }

}
