package com.graph;

import java.util.*;

public class TopologicalSortDFS {
    
    // Stack to store the topological order
    static Deque<Integer> dq = new ArrayDeque<>();

    /**
     * Performs Depth-First Search (DFS) and populates the stack for Topological Sorting.
     * 
     * **Intuition:**
     * - We use **DFS** to explore nodes.
     * - Each node is **pushed onto the stack** after all its dependencies (children) are visited.
     * - This ensures that each node appears **before** its dependencies in the final order.
     */
    private static void DFStraversal(int source, Map<Integer, List<Integer>> graph, Set<Integer> isVisited) {
        // Mark the current node as visited
        isVisited.add(source);

        // Traverse all the adjacent (neighbor) nodes of the current node
        for (int nbr : graph.getOrDefault(source, new ArrayList<>())) {
            // If the neighbor has not been visited, recursively visit it
            if (!isVisited.contains(nbr)) {
                DFStraversal(nbr, graph, isVisited);
            }
        }

        // After visiting all children, push the node onto the stack
        dq.push(source);
    }

    /**
     * Topological Sorting using DFS approach.
     */
    static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, int nodes) {
        Set<Integer> isVisited = new HashSet<>();

        // Perform DFS for every node to cover disconnected components
        for (int i = 0; i < nodes; i++) {
            if (!isVisited.contains(i)) {
                DFStraversal(i, graph, isVisited);
            }
        }

        // Convert stack to list for result
        return new ArrayList<>(dq);
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

        // **Step 2: Build adjacency list representation**
        for (List<Integer> edge : edgeList) {
            int a = edge.get(0);
            int b = edge.get(1);
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        // **Step 3: Ensure all nodes exist in the graph (even if they have no outgoing edges)**
        for (int i = 0; i < nodes; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
        }

        // **Step 4: Perform Topological Sorting using DFS**
        System.out.println("Topological Order (DFS): " + topologicalSort(graph, nodes));
    }
}

/**
 * **Dry Run**
 *
 * **Graph Representation (Adjacency List)**
 * 0 → [1]
 * 1 → [4, 2]
 * 2 → [3]
 * 3 → []
 * 4 → []
 *
 * **DFS Traversal & Stack Push Order**
 * 1. Start DFS from node `0`
 *     - Visit `0`
 *     - Move to `1`
 *         - Move to `4` → (No children, push `4` onto stack)
 *         - Move to `2`
 *             - Move to `3` → (No children, push `3` onto stack)
 *             - Push `2` onto stack
 *         - Push `1` onto stack
 *     - Push `0` onto stack
 *
 * **Final Stack (Topological Order)**
 * Topological Order: `[0, 1, 2, 3, 4]`
 */
