//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.ds.depthfirst;

import java.util.HashMap;
import java.util.Map;

public class DepthFirstRecussive {
    public static void main(final String[] args) {
        final Graph.Edge[] GRAPH =
                {new Graph.Edge("A", "C", 50), new Graph.Edge("A", "B", 50),
                 new Graph.Edge("B", "C", 80), new Graph.Edge("C", "E", 60),
                 new Graph.Edge("B", "E", 90), new Graph.Edge("B", "D", 40),
                 new Graph.Edge("E", "F", 20)};
        final Graph g = new Graph(GRAPH);
        g.depthFirstSearch("A");
    }
}

class Graph {
    final Map<String, Vertex> graph; // mapping of vertex names to
                                     // Vertex objects, built from a set
                                     // of Edges

    /** Builds a graph from a set of edges */
    public Graph(final Edge[] edges) {
        this.graph = new HashMap<String, Vertex>(edges.length);

        // one pass to find all vertices
        for (final Edge e : edges) {
            if (!this.graph.containsKey(e.v1))
                this.graph.put(e.v1, new Vertex(e.v1));
            if (!this.graph.containsKey(e.v2))
                this.graph.put(e.v2, new Vertex(e.v2));
        }

        // another pass to set neighbouring vertices
        for (final Edge e : edges) {
            this.graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            // this.graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); //
            // also
            // do this for an undirected graph
        }
    }

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
        public final Map<Vertex, Integer> neighbours =
                new HashMap<Vertex, Integer>();
        public boolean visited;
        public int depth;

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
            return Double.compare(this.dist, other.dist);
        }
    }

    // Recursive depthFirstSearch of graph starting at node vertex.
    public void depthFirstSearch(final String vertexName) {
        // Mark the vertex as visited.
        final Vertex vertex = this.graph.get(vertexName);
        vertex.visited = true;
        System.out.println(vertex.name + " has been visited.");

        for (final Map.Entry<Vertex, Integer> neighbour : vertex.neighbours.entrySet()) {
            final Vertex connection = neighbour.getKey();
            if (connection.visited == false) {
                depthFirstSearch(connection.name);
            }
        }

    }
}