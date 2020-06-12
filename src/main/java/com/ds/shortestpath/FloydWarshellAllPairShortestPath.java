//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.shortestpath;

public class FloydWarshellAllPairShortestPath {
    private static int maxValue = 9999;;

    public static void main(final String[] args) {
        final int noOfVertices = 4;
        final int graph[][] =
                { {0, 2, 4, maxValue}, {2, 0, 1, 5}, {3, 1, 0, 3},
                 {maxValue, 5, 3, 0}};
        floydWarshell(graph, noOfVertices);
    }

    static void floydWarshell(final int graph[][], final int noOfVertices) {
        final int dist[][] = new int[noOfVertices][noOfVertices];
        final int seq[][] = new int[noOfVertices][noOfVertices];
        /* Initialize the distance matrix same as input graph matrix. Or 
        we can say the initial values of shortest distances are based
        on shortest paths considering no intermediate vertex. */
        for (int row = 0; row < noOfVertices; row++) {
            for (int column = 0; column < noOfVertices; column++) {
                dist[row][column] = graph[row][column];
                seq[row][column] = column;
            }
        }
        System.out.printf("Following matrix shows the current distances between every pair of vertices \n");
        printSolution(graph);
        for (int k = 0; k < noOfVertices; k++) {
            for (int row = 0; row < noOfVertices; row++) {
                for (int column = 0; column < noOfVertices; column++) {
                    if (dist[row][k] + dist[k][column] < dist[row][column]) {
                        dist[row][column] = dist[row][k] + dist[k][column];
                        seq[row][column] = seq[row][k];
                    }
                }
            }
        }
        System.out.printf("Following matrix shows the shortest distances between every pair of vertices \n");
        printSolution(dist);
        Path(seq, 0, 3);
    }

    private static void Path(final int seq[][], int u, final int v) {
        final StringBuffer path = new StringBuffer();
        path.append(u);
        while (u != v) {
            u = seq[u][v];
            path.append("-->");
            path.append(u);
        }
        System.out.println(path.toString());
    }

    private static void printSolution(final int[][] dist) {

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == maxValue)
                    System.out.printf("%7s", "INF");
                else
                    System.out.printf("%7d", dist[i][j]);
            }
            System.out.printf("\n");
        }
    }
}
