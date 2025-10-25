package com.daa.assignment3;

import java.util.*;


public class Kruskal {
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationCount;

    public Kruskal() {
        mstEdges = new ArrayList<>();
        totalCost = 0;
        operationCount = 0;
    }

    public void execute(Graph graph) {
        mstEdges.clear();
        totalCost = 0;
        operationCount = 0;

        if (graph.getNodes().isEmpty()) {
            return;
        }

        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);

        operationCount += sortedEdges.size() * (int)(Math.log(sortedEdges.size()) / Math.log(2));

        DisjointSet disjointSet = new DisjointSet();
        for (String node : graph.getNodes()) {
            disjointSet.makeSet(node);
            operationCount++;
        }


        for (Edge edge : sortedEdges) {
            operationCount++;

            String from = edge.getFrom();
            String to = edge.getTo();

            if (!disjointSet.find(from).equals(disjointSet.find(to))) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();

                disjointSet.union(from, to);

                operationCount += 2;

                if (mstEdges.size() == graph.getVertexCount() - 1) {
                    break;
                }
            }
        }

        operationCount += disjointSet.getOperationCount();
    }

    public List<Edge> getMstEdges() {
        return mstEdges;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getOperationCount() {
        return operationCount;
    }
}