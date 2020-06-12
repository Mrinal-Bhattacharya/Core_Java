//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.graph;

import java.util.Stack;

class Vertex {
    public char label; // label (e.g. ‘A’)
    public boolean wasVisited;

    public Vertex(final char label) // constructor
    {
        this.label = label;
        this.wasVisited = false;
    }
}

public class Graph {
    private static final int MAX_VERTEX = 20;
    private int[][] adjacencyMatrix;
    private Vertex[] vertexList;
    private int noOfVertex;

    public Graph() {
        this.vertexList = new Vertex[MAX_VERTEX];
        this.noOfVertex = 0;
        this.adjacencyMatrix = new int[MAX_VERTEX][MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                this.adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void mst() {
        final Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        this.vertexList[0].wasVisited = true;
        while (!stack.empty()) {
            final int currentVertix = stack.peek();
            final int nextUnVisitedNeighbor =
                    getVisitedColumnByRowInAdjacencyMatrix(currentVertix);
            if (nextUnVisitedNeighbor == -1) {
                stack.pop();
            } else {
                markVisited(nextUnVisitedNeighbor);
                stack.push(nextUnVisitedNeighbor);
                displayVertex(currentVertix);
                displayVertex(nextUnVisitedNeighbor);
                System.out.print(" ");
            }
        }
        markAllUnVisited();
    }

    private void markAllUnVisited() {
        for (int i = 0; i < this.noOfVertex; i++) {
            markUnvisited(i);
        }
    }

    private void markUnvisited(final int i) {
        this.vertexList[i].wasVisited = false;
    }

    private void markVisited(final int wasVisited) {
        this.vertexList[wasVisited].wasVisited = true;
    }

    private int getVisitedColumnByRowInAdjacencyMatrix(final Integer rowPosition) {
        for (int column = 0; column < this.noOfVertex; column++) {
            if (checkForVisited(rowPosition, column)) {
                return column;
            }
        }
        return -1;
    }

    private boolean checkForVisited(final Integer rowPosition,
            final int columnNo) {
        return this.adjacencyMatrix[rowPosition][columnNo] == 1
               && this.vertexList[columnNo].wasVisited == false;
    }

    private void addEdge(final int i, final int j) {
        this.adjacencyMatrix[i][j] = 1;
        this.adjacencyMatrix[j][i] = 1;
    }

    private void addVertex(final char label) {
        this.vertexList[this.noOfVertex++] = new Vertex(label);
    }

    public void displayVertex(final int value) {
        System.out.print(this.vertexList[value].label);
    }

    public static void main(final String[] args) {
        final Graph graph = new Graph();
        graph.addVertex('A');// 0
        graph.addVertex('B');// 1
        graph.addVertex('C');// 2
        graph.addVertex('D');// 3
        graph.addVertex('E');// 4
        graph.addVertex('F');// 5
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 5);
        // System.out.println();
        System.out.println("Minnimum Spanning tree ");
        graph.mst();
    }

}
