//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.chalenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CandidateCode {
    static int depthCount = 0;
    static int bridgeCount = 0;

    public static void main(final String[] args) {
        final String result =
                criticalBridges(1,
                        "({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})");
        System.out.println(result);
    }

    public static String criticalBridges(final int input1, String input2) {
        String finalResult = "{";
        input2 = input2.replace("}),", "})|");
        final StringTokenizer stringTokenizer =
                new StringTokenizer(input2, "\\|");
        int[][] adjencyMatrix;
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
            adjencyMatrix = new int[vertexList.size()][vertexList.size()];
            problem[1] = problem[1].replace("}", "");
            problem[1] = problem[1].replace("{", "");
            problem[1] = problem[1].substring(0, problem[1].length() - 1);
            final String edges = problem[1].replace("),", ")|");
            final String edge[] = edges.split("\\|");
            for (int i = 0; i < edge.length; i++) {
                final String finalEdge =
                        edge[i].substring(1, edge[i].length() - 1);
                final String source = finalEdge.split(",")[0];
                final String destination = finalEdge.split(",")[1];

                final int sourceIndex = vertexList.indexOf(source);
                final int distIndex = vertexList.indexOf(destination);
                adjencyMatrix[sourceIndex][distIndex] = 1;
                adjencyMatrix[distIndex][sourceIndex] = 1;
            }
            final String criticalEdge =
                    findCriticalEdges(adjencyMatrix, vertexList);
            if (finalResult.equals("{")) {
                finalResult = finalResult + "{" + criticalEdge + "}";
            } else {
                finalResult = finalResult + ",{" + criticalEdge + "}";
            }

        }
        if (input1 == 1) {
            return finalResult.substring(1, finalResult.length());
        }
        return finalResult + "}";
    }

    private static String findCriticalEdges(final int[][] adjencyMatrix,
            final List<String> vertexList) {

        String result = "NA";

        for (int source = 0; source < adjencyMatrix.length; source++) {
            int check = 0;
            int currentDestIndex = 0;
            int prevDestIndex = 0;
            for (int dest = 0; dest < adjencyMatrix.length; dest++) {
                if (source == dest) {
                    continue;
                }
                if (adjencyMatrix[source][dest] == 1) {
                    prevDestIndex = currentDestIndex;
                    currentDestIndex = dest;
                    check++;
                }

            }
            if (check == 1) {
                if (source == 0) {
                    if (result.equals("NA")) {
                        result =
                                "(" + vertexList.get(source) + ","
                                        + vertexList.get(currentDestIndex)
                                        + ")";
                    } else {
                        result =
                                result + ",(" + vertexList.get(source) + ","
                                        + vertexList.get(currentDestIndex)
                                        + ")";
                    }
                } else {
                    if (result.equals("NA")) {
                        result =
                                "(" + vertexList.get(currentDestIndex) + ","
                                        + vertexList.get(source) + ")";
                    } else {
                        result =
                                result + ",("
                                        + vertexList.get(currentDestIndex)
                                        + "," + vertexList.get(source) + ")";
                    }
                }

            } else if (check == 2) {
                if (adjencyMatrix[prevDestIndex][currentDestIndex] != adjencyMatrix[source][currentDestIndex]) {
                    if (result.equals("NA")) {
                        result =
                                "(" + vertexList.get(prevDestIndex) + ","
                                        + vertexList.get(source) + ")";
                    } else {
                        result =
                                result + ",(" + vertexList.get(prevDestIndex)
                                        + "," + vertexList.get(source) + ")";
                    }
                }
            }
        }
        return result;

    }

}
