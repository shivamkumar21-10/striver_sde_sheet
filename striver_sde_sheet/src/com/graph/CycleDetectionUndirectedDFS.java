package com.graph;

import java.util.*;

public class CycleDetectionUndirectedDFS {

    /**
     * DFS function to detect cycles in an undirected graph.
     * 
     * Intuition:
     * - A cycle exists in an undirected graph if we visit a node that is already visited 
     *   and is NOT the parent of the current node.
     * - Since an undirected graph has bidirectional edges, the `parent` argument helps 
     *   avoid falsely detecting cycles when we visit a node's immediate parent.
     * - We explore all connected components to ensure we detect cycles in a disconnected graph as well.
     */
    static boolean DFSCycleDetection(int source, Map<Integer, List<Integer>> graph, Set<Integer> isVisited, int parent) {
        isVisited.add(source); // Mark the current node as visited

        // Traverse all the neighbors (adjacent nodes) of the current node
        for (int nbr : graph.get(source)) {
            if (!isVisited.contains(nbr)) {  
                // If the neighbor is not visited, do DFS on it
                boolean ans = DFSCycleDetection(nbr, graph, isVisited, source);
                if (ans) return true; // If any DFS call detects a cycle, return true
            } 
            else if (nbr != parent) {  
                // If the neighbor is already visited and is NOT the parent of the current node, a cycle is detected.
                return true;
            }
        }
        return false; // If no cycle found in this DFS path, return false
    }

    public static void main(String[] args) {
        // Defining edges of the graph
        List<List<Integer>> edgeList = new ArrayList<>();
        edgeList.add(Arrays.asList(1, 2));
        edgeList.add(Arrays.asList(2, 3));
        edgeList.add(Arrays.asList(3, 4));
        edgeList.add(Arrays.asList(4, 1));

        // Adjacency list representation of the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Build the graph using edge list
        for (List<Integer> edge : edgeList) {
            int a = edge.get(0);
            int b = edge.get(1);

            // Add bidirectional edges for an undirected graph
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        // Set to track visited nodes
        Set<Integer> isVisited = new HashSet<>();

        /**
         * Key Logic: Traversing in Components
         * - The graph may have multiple disconnected components.
         * - We must iterate over all nodes to ensure each component is checked for cycles.
         */
        boolean cycleExists = false;
        for (int node : graph.keySet()) {
            if (!isVisited.contains(node)) { // Start DFS for each unvisited component
                if (DFSCycleDetection(node, graph, isVisited, -1)) { // -1 indicates no parent for the root
                    cycleExists = true;
                    break; // If a cycle is detected, we can stop further processing
                }
            }
        }

        // Output result
        System.out.println("DFS Traversal of Graph:");
        System.out.println(cycleExists);
    }
}

/**
 * Dry Run (Step-by-step Execution)
 * 
 * Given edges:
 * 1 -- 2
 * |    |
 * 4 -- 3
 * 
 * 1. Start DFS from node 1
 *    - Mark 1 as visited
 *    - Visit 2 (Neighbor of 1)
 *      - Mark 2 as visited
 *      - Visit 3 (Neighbor of 2)
 *        - Mark 3 as visited
 *        - Visit 4 (Neighbor of 3)
 *          - Mark 4 as visited
 *          - Visit 1 (Neighbor of 4) [Already visited and NOT parent → Cycle detected!]
 * 
 * Output: true (Cycle detected)
 * 
 * Edge Cases Considered:
 * - If the graph has no cycle, returns false.
 * - If the graph is disconnected, checks each component.
 * - Handles cases with single-node and empty graphs.
 */
