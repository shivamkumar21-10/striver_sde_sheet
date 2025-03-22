package com.graph;
import java.util.*;
public class Leetcode210_CourseScheduleII {
	


	static class Solution {
	    public int[] findOrder(int numCourses, int[][] prerequisites) {
	        // Intuition:
	        // We are given `numCourses` courses labeled from `0` to `numCourses-1`.
	        // Some courses require prerequisites before they can be taken.
	        // We need to return an order of courses such that all prerequisites are satisfied.
	        // If there is a cycle (i.e., impossible to complete all courses), return an empty array.
	        // This is a classic "Topological Sorting" problem, which we can solve using Kahn's Algorithm (BFS).

	        // Step 1: Create an adjacency list (graph) to represent dependencies.
	        Map<Integer, List<Integer>> graph = new HashMap<>();

	        // Step 2: Create an `indegree` array to track the number of prerequisites for each course.
	        int[] indegree = new int[numCourses]; 

	        // Step 3: Populate the adjacency list and compute indegree of each course.
	        for (int i = 0; i < prerequisites.length; i++) {
	            int a = prerequisites[i][0];  // Course to be taken
	            int b = prerequisites[i][1];  // Prerequisite course
	            
	            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a); // Add edge b -> a
	            indegree[a]++;  // Increase indegree of `a` as it depends on `b`
	        }

	        // Step 4: Initialize a queue with all courses having `indegree = 0` (no prerequisites).
	        Queue<Integer> q = new LinkedList<>();
	        for (int i = 0; i < numCourses; i++) {
	            if (indegree[i] == 0) {  // No prerequisites, can be taken first
	                q.add(i);
	            }
	        }

	        // Step 5: Perform BFS (Kahn's Algorithm) for Topological Sorting.
	        List<Integer> lst = new ArrayList<>();
	        while (!q.isEmpty()) {
	            int front = q.poll();  // Take a course that can be taken now
	            lst.add(front);  // Add it to the course order

	            // Reduce indegree of dependent courses
	            for (int nbr : graph.getOrDefault(front, new ArrayList<>())) {
	                indegree[nbr]--;  // One prerequisite is completed
	                if (indegree[nbr] == 0) { // If no remaining prerequisites, add to queue
	                    q.add(nbr);
	                }
	            }
	        }

	        // Step 6: If we couldn't process all courses, it means there's a cycle → return an empty array.
	        if (lst.size() != numCourses) {
	            return new int[0];
	        }

	        // Step 7: Convert List to Array and return the result.
	        int[] ans = new int[numCourses];
	        for (int i = 0; i < lst.size(); i++) {
	            ans[i] = lst.get(i);
	        }

	        return ans;
	    }

	    public static void main(String[] args) {
	        Solution sol = new Solution();
	        int numCourses = 4;
	        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

	        System.out.println("Course Order: " + Arrays.toString(sol.findOrder(numCourses, prerequisites)));
	    }
	}

	/*
	======================
	📌 Dry Run Example
	======================

	🔹 Input:
	numCourses = 4
	prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}}

	🔹 Step 1: Graph Representation (Adjacency List)
	    0 → [1, 2]
	    1 → [3]
	    2 → [3]
	    3 → []

	🔹 Step 2: Compute `indegree[]`
	    indegree[0] = 0
	    indegree[1] = 1
	    indegree[2] = 1
	    indegree[3] = 2

	🔹 Step 3: Initialize Queue with 0 indegree courses
	    Queue: [0]

	🔹 Step 4: Process the Queue
	    - Remove 0 → Order: [0]
	        → Reduce indegree[1] = 0 (Add 1 to queue)
	        → Reduce indegree[2] = 0 (Add 2 to queue)

	    - Remove 1 → Order: [0, 1]
	        → Reduce indegree[3] = 1

	    - Remove 2 → Order: [0, 1, 2]
	        → Reduce indegree[3] = 0 (Add 3 to queue)

	    - Remove 3 → Order: [0, 1, 2, 3]

	🔹 Step 5: Final Order Output
	    ✅ [0, 1, 2, 3] (Valid order)
	    
	🔹 Edge Case Considerations:
	1️⃣ If prerequisites form a cycle, `lst.size() != numCourses` → Return `[]`.
	2️⃣ If no prerequisites, output could be `[0, 1, 2, 3]` or any valid topological order.

	=========================
	📌 Time & Space Complexity
	=========================
	- **Time Complexity**: O(V + E)  (Building graph + BFS traversal)
	- **Space Complexity**: O(V + E)  (Adjacency list + queue storage)
	*/


}
