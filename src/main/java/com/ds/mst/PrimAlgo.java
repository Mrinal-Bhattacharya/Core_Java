//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.mst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Greedy algorithm for finds a minimum spanning tree
 * https://en.wikipedia.org/wiki/Prim's_algorithm
 */
public class PrimAlgo {

    /**
     * TODO - Place method description here
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final Edge[] edges =
                {new Edge("A", "B", 4), new Edge("A", "F", 2),
                 new Edge("B", "C", 6), new Edge("B", "F", 3),
                 new Edge("C", "D", 3), new Edge("C", "F", 1),
                 new Edge("D", "E", 2),};
        final Graph graph = new Graph(edges);
        new PrimAlgo().prim(graph, "A");
    }

    void prim(final Graph g, final String root) {

        // set each E[v] to a special flag value indicating that there is no
        // edge connecting v to earlier vertices.
        final Map<String, String> PARENT = new HashMap<String, String>();
        // C[v] (the cheapest cost of a connection to v) and an edge E[v] (the
        // edge providing that cheapest connection).
        final Map<String, Integer> C = new HashMap<String, Integer>();

        step1(g, PARENT, C);
        C.put(root, 0);

        // Step2
        // https://en.wikipedia.org/wiki/Prim's_algorithm
        // Initialize an empty forest F and a set Q of vertices that have not
        // yet
        // been included in F (initially, all vertices).
        final Map<String, String> F = new LinkedHashMap<String, String>();
        final Set<String> Q = new HashSet<String>(g.graph.keySet());

        // Step3 Repeat the below steps until Q is empty:
        while (!Q.isEmpty()) {
            // 3. a. https://en.wikipedia.org/wiki/Prim's_algorithm
            // Find and remove a vertex v from Q having the minimum possible
            // value of
            // C[v]
            final String u = findMinimumWeight(Q, C);
            Q.remove(u);

            // 3. b. https://en.wikipedia.org/wiki/Prim's_algorithm
            // Add v to F and, if E[v] is not the special flag value, also add
            // E[v] to F
            if (PARENT.get(u) != null) {
                F.put(u, PARENT.get(u)); // This is one edge of MST
            }
            final Vertex vertex = g.graph.get(u);

            // 3. c. https://en.wikipedia.org/wiki/Prim's_algorithm
            // Loop over the edges vw connecting v to other vertices w. For each
            // such edge,
            final Set<Entry<Vertex, Integer>> entrySet =
                    vertex.neighbours.entrySet();
            for (final Entry<Vertex, Integer> entry : entrySet) {
                // if w still belongs to Q and vw has smaller weight than
                // C[w], perform the following steps:
                if (Q.contains(entry.getKey().name)
                    && entry.getValue() < C.get(entry.getKey().name)) {
                    // Set C[w] to the cost of edge vw
                    C.put(entry.getKey().name, entry.getValue());
                    // Set E[w] to point to edge vw.
                    PARENT.put(entry.getKey().name, u);
                }
            }

        }
        final Set<Entry<String, String>> entrySet2 = F.entrySet();
        for (final Entry<String, String> entry : entrySet2) {
            System.out.print(entry.getValue() + " " + entry.getKey()
                             + "        ");
        }

    }

    // https://en.wikipedia.org/wiki/Prim's_algorithm
    // Associate with each vertex v of the graph a number C[v] (the cheapest
    // cost of a connection to v) and an edge E[v] (the edge providing that
    // cheapest connection). To initialize these values, set all values of C[v]
    // to +∞ (or to any number larger than the maximum edge weight) and set each
    // E[v] to a special flag value indicating that there is no edge connecting
    // v to earlier vertices.
    private void step1(final Graph g, final Map<String, String> PARENT,
            final Map<String, Integer> C) {
        for (final String c : g.graph.keySet()) {
            PARENT.put(c, null);
            C.put(c, Integer.MAX_VALUE);
        }
    }

    // 3. a. https://en.wikipedia.org/wiki/Prim's_algorithm
    // Find and remove a vertex v from Q having the minimum possible value of
    // C[v]
    private String findMinimumWeight(final Set<String> q,
            final Map<String, Integer> C) {
        final Set<Entry<String, Integer>> entrySet = C.entrySet();
        int minimum = Integer.MAX_VALUE;
        String minimumVertex = "";
        for (final Entry<String, Integer> entry : entrySet) {
            if (q.contains(entry.getKey()) && entry.getValue() < minimum) {
                minimum = entry.getValue();
                minimumVertex = entry.getKey();
            }
        }
        return minimumVertex;
    }
}

class Vertex {
    String name;
    public final Map<Vertex, Integer> neighbours =
            new HashMap<Vertex, Integer>();

    public Vertex(final String name) {
        this.name = name;
    }
}

class Edge {
    String source, destination;
    int weight = Integer.MAX_VALUE;

    public Edge(final String source, final String destination, final int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    final Map<String, Vertex> graph;

    public Graph(final Edge[] edges) {
        this.graph = new HashMap<String, Vertex>(edges.length);

        // one pass to find all vertices
        for (final Edge e : edges) {
            if (!this.graph.containsKey(e.source))
                this.graph.put(e.source, new Vertex(e.source));
            if (!this.graph.containsKey(e.destination))
                this.graph.put(e.destination, new Vertex(e.destination));
        }

        // another pass to set neighbouring vertices
        for (final Edge e : edges) {
            this.graph.get(e.source).neighbours.put(
                    this.graph.get(e.destination), e.weight);
            this.graph.get(e.destination).neighbours.put(
                    this.graph.get(e.source), e.weight); // also
            // do this for an undirected graph
        }
    }
}