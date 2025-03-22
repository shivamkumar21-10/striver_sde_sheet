package com.graph;

import java.util.*;

public class CycleDetectionDirectedDFS {

    /**
     * Performs DFS traversal to detect a cycle in a directed graph.
     *
     * @param source       The current node being explored
     * @param graph        Adjacency list representing the directed graph
     * @param isVisited    Set to track visited nodes
     * @param current_path Array to track the recursion stack (used to detect cycles)
     * @return true if a cycle is detected, false otherwise
     */
    static boolean DFSCycleDetection(int source, Map<Integer, List<Integer>> graph, Set<Integer> isVisited, int[] current_path) {
        isVisited.add(source); // Mark current node as visited
        current_path[source] = 1; // Mark node as part of the current recursion stack

        // Traverse all neighbors (adjacent nodes)
        for (int nbr : graph.getOrDefault(source, new ArrayList<>())) {
            if (!isVisited.contains(nbr)) {  
                // If the neighbor is not visited, do DFS on it
                if (DFSCycleDetection(nbr, graph, isVisited, current_path)) {
                    return true; // Cycle detected in recursion
                }
            } else if (current_path[nbr] == 1) {  
                // If neighbor is already in the recursion stack → cycle detected
                return true;
            }
        }

        // Remove node from the current path (backtracking)
        current_path[source] = 0;
        return false; // No cycle found in this DFS path
    }

    public static void main(String[] args) {
        // Step 1: Define the edges of a directed graph
        List<List<Integer>> edgeList = new ArrayList<>();
        edgeList.add(Arrays.asList(1, 2));
        edgeList.add(Arrays.asList(2, 3));
        edgeList.add(Arrays.asList(3, 4));
        edgeList.add(Arrays.asList(4, 1)); // This creates a cycle: 1 → 2 → 3 → 4 → 1

        // Step 2: Build adjacency list representation of the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = 0; // To track the highest numbered node

        for (List<Integer> edge : edgeList) {
            int a = edge.get(0);
            int b = edge.get(1);
            n = Math.max(n, Math.max(a, b)); // Get the maximum node index
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b); // Add directed edge (a → b)
        }
        n++; // Adjust size for zero-based indexing

        // Step 3: Initialize helper structures
        Set<Integer> isVisited = new HashSet<>();
        int[] current_path = new int[n]; // Track nodes in the current recursion stack

        // Step 4: Perform DFS cycle detection
        boolean cycleExists = false;
        for (int node : graph.keySet()) {
            if (!isVisited.contains(node)) { 
                if (DFSCycleDetection(node, graph, isVisited, current_path)) { 
                    cycleExists = true;
                    break; // Exit as soon as a cycle is found
                }
            }
        }

        // Step 5: Print output
        System.out.println("Cycle Exists in Directed Graph: " + cycleExists);

        /**
         * ====================== DRY RUN =========================
         * Input Graph (Directed):
         *  1 → 2
         *  ↓   ↓
         *  4 ← 3
         *  
         *  Adjacency List Representation:
         *  1 → [2]
         *  2 → [3]
         *  3 → [4]
         *  4 → [1]
         *  
         *  Execution Steps:
         *  1. Start DFS from node 1
         *     isVisited = {1}, current_path = [0,1,0,0,0]
         *     → Go to 2
         *  
         *  2. DFS at node 2
         *     isVisited = {1,2}, current_path = [0,1,1,0,0]
         *     → Go to 3
         *  
         *  3. DFS at node 3
         *     isVisited = {1,2,3}, current_path = [0,1,1,1,0]
         *     → Go to 4
         *  
         *  4. DFS at node 4
         *     isVisited = {1,2,3,4}, current_path = [0,1,1,1,1]
         *     → Go to 1 (neighbor)
         *  
         *  5. Cycle detected!
         *     Since 1 is already in current_path[], return true and propagate back.
         * 
         *  6. Print "Cycle Exists in Directed Graph: true"
         *  ========================================================
         */
    }
}
