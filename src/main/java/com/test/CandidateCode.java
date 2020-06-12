package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class CandidateCode {
    static class Graph {
        private static int depthCount;
        private static int bridgeCount;
        final Map<String, Vertex> graph; // mapping of vertex names to
                                         // Vertex objects, built from a set
                                         // of Edges

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
                graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also
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

        /**
         * One vertex of the graph, complete with mappings to neighbouring
         * vertices
         */
        public static class Vertex implements Comparable<Vertex> {
            public final String name;
            public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be
                                                 // infinity
            public Vertex previous = null;
            public final Map<Vertex, Integer> neighbours = new HashMap();
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
                return Double.compare(dist, other.dist);
            }
        }

        // Run DFS on the graph from the specified vertex.
        // Parent tracks the node from which this vertex is being visited.
        public static void depthFirstSearch(final Vertex vertex,
                final Vertex parent, final StringBuffer buffer) {
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
                        depthFirstSearch(connection, vertex, buffer);

                        // A bridge exists when the depth values of two
                        // connecting
                        // nodes differs after DFS.
                        if (connection.depth > vertex.depth) {
                            buffer.insert(0, "(" + vertex.name + ","
                                             + connection.name + ")");
                            // System.out.println("A bridge was detected: "
                            // + vertex.name + "-"
                            // + connection.name);
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

        // Run DFS on the graph from the specified vertex.
        // Parent tracks the node from which this vertex is being visited.
        public static void bFirstSearch(final Vertex vertex,
                final Vertex parent, final StringBuffer buffer) {
            vertex.visited = true;
            // vertex.depth = depthCount++;
            final Queue<Vertex> queue = new LinkedList<Vertex>();
            queue.add(vertex);
            while (!queue.isEmpty()) {
                final Vertex currentVertex = queue.remove();
                System.out.println(currentVertex.name + " has been visited.");
                currentVertex.depth = depthCount++;
                for (final Map.Entry<Vertex, Integer> neighbour : currentVertex.neighbours.entrySet()) {
                    final Vertex connection = neighbour.getKey();
                    currentVertex.depth = depthCount++;
                    if (!connection.visited) {

                        connection.visited = true;
                        queue.add(connection);
                        if (connection.depth > currentVertex.depth) {
                            buffer.insert(0, "(" + currentVertex.name + ","
                                             + connection.name + ")");

                            System.out.println("A bridge was detected: "
                                               + vertex.name + "-"
                                               + connection.name);
                            bridgeCount++;
                        }
                        // Update depth values to correspond to the DFS.
                        currentVertex.depth =
                                Math.min(currentVertex.depth, connection.depth);
                    } else {
                        currentVertex.depth =
                                Math.min(currentVertex.depth, connection.depth);
                    }

                }
            }
        }
    }

    public static void main(final String[] args) {
        final String input2 =
                "({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})";
        System.out.println(criticalBridges(1, input2));

    }

    public static String criticalBridges(final int input1, String input2) {

        String finalResult = "{";
        input2 = input2.replace("}),", "})|");
        final StringTokenizer stringTokenizer =
                new StringTokenizer(input2, "\\|");
        List<String> vertexList = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            String sets = stringTokenizer.nextToken();
            sets = sets.replace("},", "}|");
            final String problem[] = sets.split("\\|");

            problem[0] = problem[0].replace("}", "");
            problem[0] = problem[0].replace("(", "");
            problem[0] = problem[0].replace("{", "");
            final String vertex[] = problem[0].split(",");

            vertexList = Arrays.asList(vertex);
            problem[1] = problem[1].replace("}", "");
            problem[1] = problem[1].replace("{", "");
            problem[1] = problem[1].substring(0, problem[1].length() - 1);
            final String edges = problem[1].replace("),", ")|");
            final String edge[] = edges.split("\\|");
            final Graph.Edge[] GRAPH = new Graph.Edge[edge.length];
            String firstVertex = "";
            for (int i = 0; i < edge.length; i++) {

                final String finalEdge =
                        edge[i].substring(1, edge[i].length() - 1);
                final String source = finalEdge.split(",")[0];
                final String destination = finalEdge.split(",")[1];
                if (firstVertex.equals("")) {
                    firstVertex = source;
                }
                final Graph.Edge edge2 = new Graph.Edge(source, destination, 0);
                GRAPH[i] = edge2;
                // final int sourceIndex = vertexList.indexOf(source);
                // final int distIndex = vertexList.indexOf(destination);
                // adjencyMatrix[sourceIndex][distIndex] = 1;
                // adjencyMatrix[distIndex][sourceIndex] = 1;
            }
            final Graph g = new Graph(GRAPH);
            final StringBuffer buffer = new StringBuffer();
            g.bFirstSearch(g.graph.get(firstVertex), null, buffer);
            System.out.println(buffer.toString());
            if (buffer.toString().equals("")) {
                buffer.append("NA");
            }
            if (finalResult.equals("{")) {
                finalResult = finalResult + "{" + buffer.toString() + "}";
            } else {
                finalResult = finalResult + ",{" + buffer.toString() + "}";
            }
        }
        // final String criticalEdge =
        // findCriticalEdges(adjencyMatrix, vertexList);

        //
        // }
        if (input1 == 1) {
            return finalResult.substring(1, finalResult.length());
        }
        return finalResult + "}";

    }
}
