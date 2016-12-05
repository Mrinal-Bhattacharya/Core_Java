//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.chalenges;

import java.util.HashMap;
import java.util.Map;

public class CriticalBridge {
    public static void main(final String[] args) {

        final Graph.Edge[] GRAPH =
                {new Graph.Edge("A", "C", 50), new Graph.Edge("B", "C", 80),
                 new Graph.Edge("C", "E", 60), new Graph.Edge("B", "E", 90),
                 new Graph.Edge("B", "D", 40), new Graph.Edge("E", "F", 20)};
        final Graph g = new Graph(GRAPH);
        Graph.depthFirstSearch(g.graph.get("A"), null);

    }
}

class Graph {
    private static int depthCount;
    private static int bridgeCount;
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
            this.graph.get(e.v1).neighbours.put(this.graph.get(e.v2), e.dist);
            this.graph.get(e.v2).neighbours.put(this.graph.get(e.v1), e.dist); // also
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

        public int compareTo(final Vertex other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    // Run DFS on the graph from the specified vertex.
    // Parent tracks the node from which this vertex is being visited.
    public static void depthFirstSearch(final Vertex vertex, final Vertex parent) {
        vertex.visited = true;
        vertex.depth = depthCount++;

        // Examine the connections to this node.
        for (final Map.Entry<Vertex, Integer> neighbour : vertex.neighbours.entrySet()) {
            final Vertex connection = neighbour.getKey();
            // Don't examine the parent again.
            if (connection != parent) {
                // If unvisited, run DFS from this connection.
                if (!connection.visited) {
                    // vertex - connection is a forward edge.
                    depthFirstSearch(connection, vertex);

                    // A bridge exists when the depth values of two connecting
                    // nodes differs after DFS.
                    if (connection.depth > vertex.depth) {

                        System.out.println("A bridge was detected: "
                                           + vertex.name + "-"
                                           + connection.name);
                        bridgeCount++;
                    }
                    // Update depth values to correspond to the DFS.
                    vertex.depth = Math.min(vertex.depth, connection.depth);
                }
                // Otherwise, vertex - connection is a back edge. Update
                // vertex's depth value.
                else {
                    vertex.depth = Math.min(vertex.depth, connection.depth);
                }
            }
        }
    }

}
