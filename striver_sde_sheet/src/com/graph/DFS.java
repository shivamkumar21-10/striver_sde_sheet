package com.graph;

import java.util.*;

public class DFS {
    
    /**
     * Performs Depth-First Search (DFS) traversal on the graph.
     * 
     * Intuition:
     *  - DFS explores as far as possible along each branch before backtracking.
     *  - It uses recursion to traverse all reachable nodes from the source.
     *  - A set is used to track visited nodes, ensuring each node is processed only once.
     *  - Works similar to **preorder traversal** in trees.
     *
     * Example Graph (Adjacency List Representation):
     * 
     *     0 --- 1 --- 4
     *          |
     *          2
     *          |
     *          3
     * 
     *  Edge List Representation: 
     *      (0,1), (1,4), (1,2), (2,3)
     * 
     *  Adjacency List Representation:
     *      0 → [1]
     *      1 → [0, 4, 2]
     *      2 → [1, 3]
     *      3 → [2]
     *      4 → [1]
     *
     *  Recursive Call Tree for DFS(0):
     *  
     *      DFS(0)
     *       |
     *       → DFS(1)
     *          |
     *          → DFS(4) (Returns)
     *          → DFS(2)
     *             |
     *             → DFS(3) (Returns)
     * 
     * @param source    The starting node for DFS traversal.
     * @param graph     The adjacency list representing the graph.
     * @param n         The total number of nodes in the graph.
     * @param isVisited A set to keep track of visited nodes.
     */
    private static void DFStraversal(int source, Map<Integer, List<Integer>> graph, int n, Set<Integer> isVisited) {
        // Print the current node
        System.out.print(source + " ");
        
        // Mark the current node as visited
        isVisited.add(source);

        // Traverse all the adjacent (neighbor) nodes of the current node
        for (int nbr : graph.get(source)) {
            // If the neighbor has not been visited, recursively visit it
            if (!isVisited.contains(nbr)) {
                DFStraversal(nbr, graph, n, isVisited);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> edgeList = new ArrayList<>();
        
        // Defining the edges of the graph
        edgeList.add(Arrays.asList(0,1));
        edgeList.add(Arrays.asList(1,4));
        edgeList.add(Arrays.asList(1,2));
        edgeList.add(Arrays.asList(2,3));
        
        // Create adjacency list representation of the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = 5; // Number of nodes in the graph
        
        for (int i = 0; i < edgeList.size(); i++) {
            int a = edgeList.get(i).get(0);
            int b = edgeList.get(i).get(1);
            
            // Add bidirectional edges to adjacency list
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        
        // Source node for DFS traversal
        int source = 0;
        
        // Set to track visited nodes
        Set<Integer> isVisited = new HashSet<>();
        
        // Perform DFS traversal
        System.out.println("DFS Traversal of Graph:");
        DFStraversal(source, graph, n, isVisited);
    }
}
