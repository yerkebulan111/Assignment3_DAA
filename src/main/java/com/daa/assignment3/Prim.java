package com.daa.assignment3;

import java.util.*;


public class Prim {
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationCount;

    public Prim() {
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

        Set<String> visited = new HashSet<>();

        PriorityQueue<Edge> pq = new PriorityQueue<>();


        String startVertex = graph.getNodes().get(0);
        visited.add(startVertex);
        operationCount++;


        for (Edge edge : graph.getAdjacencyList().get(startVertex)) {
            pq.offer(edge);
            operationCount++;
        }


        while (!pq.isEmpty() && visited.size() < graph.getVertexCount()) {
            Edge minEdge = pq.poll();
            operationCount++;

            String to = minEdge.getTo();

            if (visited.contains(to)) {
                operationCount++;
                continue;
            }

            mstEdges.add(minEdge);
            totalCost += minEdge.getWeight();
            visited.add(to);
            operationCount += 2;

            for (Edge edge : graph.getAdjacencyList().get(to)) {
                operationCount++;
                if (!visited.contains(edge.getTo())) {
                    pq.offer(edge);
                    operationCount++;
                }
            }
        }
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