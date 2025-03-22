package com.graph;
import java.util.*;
public class Leetcode207_CourseSchedule {
	


	static class Solution {
	    public boolean canFinish(int numCourses, int[][] prerequisites) {
	        // Intuition:
	        // We have `numCourses` courses labeled from `0` to `numCourses-1`.
	        // Some courses require prerequisites before they can be taken.
	        // We need to determine if it is **possible** to finish all courses.
	        // If there is a cycle in the course dependency graph, we **cannot** complete all courses.
	        // This is a classic **Topological Sorting** problem using **Kahn’s Algorithm (BFS)**.

	        // Step 1: Create an adjacency list (graph) representation.
	        Map<Integer, List<Integer>> graph = new HashMap<>();

	        // Step 2: Create an `indegree` array to track the number of prerequisites for each course.
	        int[] indegree = new int[numCourses];

	        // Step 3: Populate the adjacency list and compute indegree for each course.
	        for (int i = 0; i < prerequisites.length; i++) {
	            int a = prerequisites[i][0];  // Course to be taken
	            int b = prerequisites[i][1];  // Prerequisite course
	            
	            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a); // Add edge b -> a
	            indegree[a]++;  // Increase indegree of `a` since it depends on `b`
	        }

	        // Step 4: Initialize a queue with all courses having `indegree = 0` (no prerequisites).
	        Queue<Integer> q = new LinkedList<>();
	        for (int i = 0; i < numCourses; i++) {
	            if (indegree[i] == 0) {  // No prerequisites, can be taken first
	                q.add(i);
	            }
	        }

	        // Step 5: Perform BFS (Kahn’s Algorithm) for Topological Sorting.
	        List<Integer> lst = new ArrayList<>();
	        while (!q.isEmpty()) {
	            int front = q.poll();  // Take a course that can be taken now
	            lst.add(front);  // Add it to the list of completed courses

	            // Reduce indegree of dependent courses
	            for (int nbr : graph.getOrDefault(front, new ArrayList<>())) {
	                indegree[nbr]--;  // One prerequisite is completed
	                if (indegree[nbr] == 0) {  // If no remaining prerequisites, add to queue
	                    q.add(nbr);
	                }
	            }
	        }

	        // Step 6: If we couldn't process all courses, it means there's a cycle → return false.
	        return lst.size() == numCourses;
	    }

	    public static void main(String[] args) {
	        Solution sol = new Solution();
	        int numCourses1 = 2;
	        int[][] prerequisites1 = {{1, 0}};

	        int numCourses2 = 2;
	        int[][] prerequisites2 = {{1, 0}, {0, 1}}; // Cycle exists

	        System.out.println("Can finish courses? " + sol.canFinish(numCourses1, prerequisites1)); // true
	        System.out.println("Can finish courses? " + sol.canFinish(numCourses2, prerequisites2)); // false
	    }
	}

	/*
	======================
	📌 Dry Run Example 1
	======================

	🔹 Input:
	numCourses = 2
	prerequisites = {{1, 0}}

	🔹 Step 1: Graph Representation (Adjacency List)
	    0 → [1]
	    1 → []

	🔹 Step 2: Compute `indegree[]`
	    indegree[0] = 0
	    indegree[1] = 1

	🔹 Step 3: Initialize Queue with 0 indegree courses
	    Queue: [0]

	🔹 Step 4: Process the Queue
	    - Remove 0 → Process 1
	    - Remove 1 → Done

	🔹 Step 5: All courses are processed → Return `true`

	======================
	📌 Dry Run Example 2 (Cycle Case)
	======================

	🔹 Input:
	numCourses = 2
	prerequisites = {{1, 0}, {0, 1}}

	🔹 Step 1: Graph Representation (Adjacency List)
	    0 → [1]
	    1 → [0]

	🔹 Step 2: Compute `indegree[]`
	    indegree[0] = 1
	    indegree[1] = 1

	🔹 Step 3: Initialize Queue with 0 indegree courses
	    Queue: [] (empty, since all nodes have dependencies)

	🔹 Step 4: Since the queue is empty, BFS never runs.

	🔹 Step 5: `lst.size() != numCourses` → Cycle detected → Return `false`

	=========================
	📌 Time & Space Complexity
	=========================
	- **Time Complexity**: O(V + E)  (Building graph + BFS traversal)
	- **Space Complexity**: O(V + E)  (Adjacency list + queue storage)
	*/


}
