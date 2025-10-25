package com.daa.assignment3;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Main class to execute MST algorithms and generate results.
 */
public class Main {
    private static final String INPUT_FILE = "ass_3_input.json";
    private static final String OUTPUT_FILE = "ass_3_output.json";

    public static void main(String[] args) {
        try {
            System.out.println("=== MST Algorithm Analyzer ===\n");

            // read input JSON
            String inputJson = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
            JsonObject inputData = JsonParser.parseString(inputJson).getAsJsonObject();
            JsonArray graphsArray = inputData.getAsJsonArray("graphs");

            // prepare results
            JsonObject outputData = new JsonObject();
            JsonArray resultsArray = new JsonArray();

            // process each graph
            for (JsonElement graphElement : graphsArray) {
                JsonObject graphObj = graphElement.getAsJsonObject();
                Graph graph = parseGraph(graphObj);

                System.out.println("Processing " + graph + "...\n");

                /**
                 * run Prim's Algorithm
                 */
                Prim prim = new Prim();
                long primStart = System.nanoTime();
                prim.execute(graph);
                long primEnd = System.nanoTime();
                double primTime = (primEnd - primStart) / 1_000_000.0;

                System.out.println("Prim's Algorithm:");
                System.out.println("  MST Cost: " + prim.getTotalCost());
                System.out.println("  Operations: " + prim.getOperationCount());
                System.out.println("  Time: " + String.format("%.2f", primTime) + " ms");
                System.out.println("  Edges: " + prim.getMstEdges());

                /**
                 * run Kruskal's Algorithm
                 */
                Kruskal kruskal = new Kruskal();
                long kruskalStart = System.nanoTime();
                kruskal.execute(graph);
                long kruskalEnd = System.nanoTime();
                double kruskalTime = (kruskalEnd - kruskalStart) / 1_000_000.0;

                System.out.println("\nKruskal's Algorithm:");
                System.out.println("  MST Cost: " + kruskal.getTotalCost());
                System.out.println("  Operations: " + kruskal.getOperationCount());
                System.out.println("  Time: " + String.format("%.2f", kruskalTime) + " ms");
                System.out.println("  Edges: " + kruskal.getMstEdges());

                // verify that both algorithms produce same cost
                if (prim.getTotalCost() == kruskal.getTotalCost()) {
                    System.out.println("\n✓ Both algorithms produced the same MST cost!");
                } else {
                    System.out.println("\n✗ WARNING: Algorithms produced different costs!");
                }

                System.out.println("\n" + "=".repeat(50) + "\n");

                // build result JSON for graph
                JsonObject result = buildResult(graph, prim, primTime, kruskal, kruskalTime);
                resultsArray.add(result);
            }

            outputData.add("results", resultsArray);

            // write output JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String outputJson = gson.toJson(outputData);
            Files.write(Paths.get(OUTPUT_FILE), outputJson.getBytes());

            System.out.println("Results written to " + OUTPUT_FILE);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Parse graph from JSON object
     */
    private static Graph parseGraph(JsonObject graphObj) {
        int id = graphObj.get("id").getAsInt();

        // parse nodes
        List<String> nodes = new ArrayList<>();
        JsonArray nodesArray = graphObj.getAsJsonArray("nodes");
        for (JsonElement node : nodesArray) {
            nodes.add(node.getAsString());
        }

        // parse edges
        List<Edge> edges = new ArrayList<>();
        JsonArray edgesArray = graphObj.getAsJsonArray("edges");
        for (JsonElement edgeElement : edgesArray) {
            JsonObject edgeObj = edgeElement.getAsJsonObject();
            String from = edgeObj.get("from").getAsString();
            String to = edgeObj.get("to").getAsString();
            int weight = edgeObj.get("weight").getAsInt();
            edges.add(new Edge(from, to, weight));
        }

        return new Graph(id, nodes, edges);
    }

    /**
     * Build result JSON object for a graph
     */
    private static JsonObject buildResult(Graph graph, Prim prim, double primTime,
                                          Kruskal kruskal, double kruskalTime) {
        JsonObject result = new JsonObject();
        result.addProperty("graph_id", graph.getId());


        JsonObject inputStats = new JsonObject();
        inputStats.addProperty("vertices", graph.getVertexCount());
        inputStats.addProperty("edges", graph.getEdgeCount());
        result.add("input_stats", inputStats);

        // Prim's results
        JsonObject primResult = new JsonObject();
        primResult.add("mst_edges", edgesToJsonArray(prim.getMstEdges()));
        primResult.addProperty("total_cost", prim.getTotalCost());
        primResult.addProperty("operations_count", prim.getOperationCount());
        primResult.addProperty("execution_time_ms", Math.round(primTime * 100.0) / 100.0);
        result.add("prim", primResult);

        // Kruskal's results
        JsonObject kruskalResult = new JsonObject();
        kruskalResult.add("mst_edges", edgesToJsonArray(kruskal.getMstEdges()));
        kruskalResult.addProperty("total_cost", kruskal.getTotalCost());
        kruskalResult.addProperty("operations_count", kruskal.getOperationCount());
        kruskalResult.addProperty("execution_time_ms", Math.round(kruskalTime * 100.0) / 100.0);
        result.add("kruskal", kruskalResult);

        return result;
    }

    /**
     * convert list of edges to JSON array
     */
    private static JsonArray edgesToJsonArray(List<Edge> edges) {
        JsonArray array = new JsonArray();
        for (Edge edge : edges) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", edge.getFrom());
            edgeObj.addProperty("to", edge.getTo());
            edgeObj.addProperty("weight", edge.getWeight());
            array.add(edgeObj);
        }
        return array;
    }
}