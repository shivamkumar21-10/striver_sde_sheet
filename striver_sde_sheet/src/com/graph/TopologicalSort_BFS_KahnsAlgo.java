package com.graph;

import java.util.*;

public class TopologicalSort_BFS_KahnsAlgo {

    /**
     * Performs Topological Sorting using Kahn's Algorithm (BFS Approach).
     * 
     * **Intuition:**
     * - Topological sorting is valid for **DAGs (Directed Acyclic Graphs)**.
     * - We use **Kahn's Algorithm**, which is a BFS-based approach.
     * - We maintain an **indegree array**, where:
     *    - `indegree[i]` stores the number of incoming edges to node `i`.
     * - Start with nodes having **0 indegree** (no dependencies).
     * - Process nodes in **BFS manner**:
     *    - Remove a node from queue, add it to result.
     *    - Decrease indegree of its neighbors.
     *    - If any neighbor's indegree becomes `0`, push it to the queue.
     * - If we process all nodes, the graph is a **DAG**; otherwise, a **cycle exists**.
     */
    static List<Integer> kahnAlgo(Map<Integer, List<Integer>> graph, int nodes) {
        List<Integer> ans = new ArrayList<>();  // Stores the topological order
        int[] indegree = new int[nodes];  // Stores the indegree of each node

        // Step 1: Calculate indegree for each node
        for (List<Integer> edges : graph.values()) {
            for (Integer edge : edges) {
                indegree[edge]++; // Increment indegree for each incoming edge
            }
        }

        // Step 2: Initialize the queue with all nodes having indegree = 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < nodes; i++) {
            if (indegree[i] == 0) { // If node has no incoming edges, add to queue
                q.add(i);
            }
        }

        // Step 3: Process nodes using BFS
        while (!q.isEmpty()) {
            int front = q.poll(); // Remove node from queue
            ans.add(front); // Add to topological order result

            // Step 4: Reduce indegree of neighbors and add them to queue if their indegree becomes 0
            for (int nbr : graph.getOrDefault(front, new ArrayList<>())) {
                indegree[nbr]--; // Reduce indegree of neighbor
                if (indegree[nbr] == 0) { // If indegree becomes 0, add to queue
                    q.add(nbr);
                }
            }
        }

        // Step 5: Check if a cycle exists (if all nodes are not processed)
        if (ans.size() != nodes) {
            throw new IllegalArgumentException("Graph contains a cycle!");
        }

        return ans;  // Return the topological order
    }

    public static void main(String[] args) {
        // **Step 1: Define the graph as an edge list**
        List<List<Integer>> edgeList = Arrays.asList(
            Arrays.asList(0, 1),  // 0 → 1
            Arrays.asList(1, 4),  // 1 → 4
            Arrays.asList(1, 2),  // 1 → 2
            Arrays.asList(2, 3)   // 2 → 3
        );

        int nodes = 5; // Number of nodes in the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // **Step 2: Build the adjacency list representation of the graph**
        for (List<Integer> edge : edgeList) {
            int a = edge.get(0);
            int b = edge.get(1);
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        // **Step 3: Ensure all nodes exist in the graph (even if they have no outgoing edges)**
        for (int i = 0; i < nodes; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
        }

        // **Step 4: Perform Topological Sorting using Kahn's Algorithm**
        System.out.println("Topological Order: " + kahnAlgo(graph, nodes));
    }
}

/**
 * Dry Run:
 * 
 * **Graph Representation (Adjacency List)**
 * 0 → [1]
 * 1 → [4, 2]
 * 2 → [3]
 * 3 → []
 * 4 → []
 *
 * **Indegree Calculation**
 * Node 0 → Indegree = 0
 * Node 1 → Indegree = 1 (from 0 → 1)
 * Node 2 → Indegree = 1 (from 1 → 2)
 * Node 3 → Indegree = 1 (from 2 → 3)
 * Node 4 → Indegree = 1 (from 1 → 4)
 *
 * **Initial Queue (Nodes with Indegree 0)**
 * Queue: [0]
 *
 * **Processing Steps**
 * -------------------------------------
 * | Step | Node Processed | Queue After Processing | Updated Indegree | Topological Order |
 * |------|--------------|----------------------|------------------|------------------|
 * | 1    | 0            | [1]                   | 1 → 0            | [0]              |
 * | 2    | 1            | [2, 4]                | 2 → 0, 4 → 0      | [0, 1]           |
 * | 3    | 2            | [4, 3]                | 3 → 0             | [0, 1, 2]        |
 * | 4    | 4            | [3]                   | No update        | [0, 1, 2, 4]     |
 * | 5    | 3            | []                     | No update        | [0, 1, 2, 4, 3]  |
 * -------------------------------------
 *
 * **Final Output**
 * Topological Order: [0, 1, 2, 4, 3]
 */
