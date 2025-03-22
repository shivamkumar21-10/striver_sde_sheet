package com.graph;

import java.util.*;

public class CycleDetectionUndirectedBFS {

    /**
     * Function to detect a cycle in an undirected graph using BFS
     * @param source The starting node for BFS traversal
     * @param graph Adjacency list representation of the graph
     * @param isVisited Set to keep track of visited nodes
     * @param parent Array to store the parent of each node
     * @return true if a cycle is detected, false otherwise
     */
    static boolean BFSCycleDetection(int source, Map<Integer, List<Integer>> graph, Set<Integer> isVisited,
                                     int[] parent) {
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS traversal
        q.add(source);
        isVisited.add(source);
        parent[source] = -1; // Mark the source as having no parent since it is the starting node

        // BFS traversal
        while (!q.isEmpty()) {
            int front = q.poll(); // Dequeue the front element

            // Traverse all its neighbors
            for (int nbr : graph.getOrDefault(front, new ArrayList<>())) {
                if (!isVisited.contains(nbr)) {
                    // If the neighbor is not visited, mark it as visited
                    isVisited.add(nbr);
                    q.add(nbr);
                    parent[nbr] = front; // Set parent of `nbr` as `front`
                } else {
                    // If the neighbor is already visited, check if it's not the parent
                    // WHY? Because in an undirected graph, a node always has an edge back to its parent.
                    // However, if we encounter a node that is visited and **not** our parent,
                    // it means we've found a back edge, which indicates a cycle.
                    if (parent[front] != nbr) {
                        return true; // Cycle detected
                    }
                }
            }
        }
        return false; // No cycle found
    }

    public static void main(String[] args) {
        // Edge list representation of the graph
        List<List<Integer>> edgeList = new ArrayList<>();
        edgeList.add(Arrays.asList(0, 1));
        edgeList.add(Arrays.asList(1, 2));
        edgeList.add(Arrays.asList(2, 4));
        edgeList.add(Arrays.asList(3, 4));
        edgeList.add(Arrays.asList(3, 0));

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = 0; // Variable to store the maximum node index

        // Construct the adjacency list from edge list
        for (List<Integer> edge : edgeList) {
            int a = edge.get(0);
            int b = edge.get(1);
            n = Math.max(n, Math.max(a, b)); // Find the largest node value

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        n++; // Adjust `n` to be the number of nodes

        Set<Integer> isVisited = new HashSet<>(); // Set to track visited nodes
        int[] parent = new int[n]; // Parent array to maintain parent-child relationships
        Arrays.fill(parent, -1); // Initialize parent array with -1

        boolean cycleExists = false;
        for (int node : graph.keySet()) {
            if (!isVisited.contains(node)) {
                // Perform BFS from each unvisited node (handles disconnected components)
                if (BFSCycleDetection(node, graph, isVisited, parent)) {
                    cycleExists = true;
                    break;
                }
            }
        }

        System.out.println("Cycle Exists: " + cycleExists);

        /**
         * ============== 📌 DRY RUN 📌 ==============
         * 
         * 🔹 Given edge list:
         *       0 -- 1 -- 2 -- 4
         *       |         /
         *       3 -------
         * 
         * 🔹 Graph Representation:
         *     0 → [1, 3]
         *     1 → [0, 2]
         *     2 → [1, 4]
         *     3 → [0, 4]
         *     4 → [2, 3]
         *
         * 🔹 Step-by-step execution:
         * 
         * 1. Start BFS from **node 0**.
         *      - Mark `0` as visited and push to queue.
         *      - Set `parent[0] = -1` (no parent).
         * 2. Process **node 0**:
         *      - Visit `1` → Mark visited, set `parent[1] = 0`, add to queue.
         *      - Visit `3` → Mark visited, set `parent[3] = 0`, add to queue.
         * 3. Process **node 1**:
         *      - Visit `2` → Mark visited, set `parent[2] = 1`, add to queue.
         *      - Visit `0` → Already visited but is its **parent**, so ignore.
         * 4. Process **node 3**:
         *      - Visit `4` → Mark visited, set `parent[4] = 3`, add to queue.
         *      - Visit `0` → Already visited but is its **parent**, so ignore.
         * 5. Process **node 2**:
         *      - Visit `1` → Already visited but is its **parent**, so ignore.
         *      - Visit `4` → Already visited, **but parent of `2` is `1`, not `4`**, so **cycle detected**!
         * 
         * 🔹 **Cycle Exists → Output: `true`**
         * 
         * ============== 📌 TIME & SPACE COMPLEXITY 📌 ==============
         * - **Time Complexity**: **O(V + E)**  (BFS traversal visits each node & edge once)
         * - **Space Complexity**: **O(V + E)**  (Adjacency list + queue + visited set + parent array)
         */
    }
}
