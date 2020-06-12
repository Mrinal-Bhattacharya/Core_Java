//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.shortest;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DijkstraShortestGraph {
    private static final Graph.Edge[] GRAPH = {new Graph.Edge("A", "B", 50),
                                               new Graph.Edge("A", "D", 80),
                                               new Graph.Edge("B", "C", 60),
                                               new Graph.Edge("B", "D", 90),
                                               new Graph.Edge("C", "E", 40),
                                               new Graph.Edge("D", "C", 20),
                                               new Graph.Edge("D", "E", 70),};
    private static final String START = "A";
    private static final String END = "E";

    public static void main(final String[] args) {
        final Graph g = new Graph(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        // g.printAllPaths();
    }
}

class Graph {
    private final Map<String, Vertex> graph; // mapping of vertex names to
                                             // Vertex objects, built from a set
                                             // of Edges

    /** One edge of the graph (only used by Graph constructor) */
    public static class Edge {
        public final String v1, v2;
        public final int dist;

        public Edge(final String v1, final String v2, final int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    /** One vertex of the graph, complete with mappings to neighbouring vertices */
    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap();

        public Vertex(final String name) {
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        public int compareTo(final Vertex other) {
            return Double.compare(dist, other.dist);
        }
    }

    /** Builds a graph from a set of edges */
    public Graph(final Edge[] edges) {
        graph = new HashMap(edges.length);

        // one pass to find all vertices
        for (final Edge e : edges) {
            if (!graph.containsKey(e.v1))
                graph.put(e.v1, new Vertex(e.v1));
            if (!graph.containsKey(e.v2))
                graph.put(e.v2, new Vertex(e.v2));
        }

        // another pass to set neighbouring vertices
        for (final Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            // graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also
            // do this for an undirected graph
        }
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(final String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n",
                    startName);
            return;
        }
        final Vertex source = graph.get(startName);
        final NavigableSet<Vertex> q = new TreeSet();
        source.previous = source;
        source.dist = 0;
        // // set-up vertices
        // for (final Vertex v : graph.values()) {
        // v.previous = v == source ? source : null;
        // v.dist = v == source ? 0 : Integer.MAX_VALUE;
        // System.out.println(q.add(v));
        // }
        q.add(source);
        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration
                               // will return source)
            if (u.dist == Integer.MAX_VALUE)
                break; // we can ignore u (and any other remaining vertices)
                       // since they are unreachable

            // look at distances to each neighbour
            for (final Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); // the neighbour in this iteration

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    public void printPath(final String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n",
                    endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }

    /**
     * Prints the path from the source to every vertex (output order is not
     * guaranteed)
     */
    public void printAllPaths() {
        for (final Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }
}
