package com.daa.assignment3;

import java.util.*;


public class Graph {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;
    private Map<String, List<Edge>> adjacencyList;

    public Graph(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
        this.adjacencyList = new HashMap<>();
        buildAdjacencyList();
    }


    private void buildAdjacencyList() {
        for (String node : nodes) {
            adjacencyList.put(node, new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjacencyList.get(edge.getFrom()).add(edge);
            adjacencyList.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }
    }

    public int getId() {
        return id;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getVertexCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "Graph " + id + " [Vertices: " + nodes.size() + ", Edges: " + edges.size() + "]";
    }
}