package com.graph;

import java.util.*;

public class Leetcode994_RottingOranges {
	
	class Solution {
	    // Directions array to move in 4 directions: right, left, down, and up
	    int[] dx = new int[] { 0, 0, 1, -1 };
	    int[] dy = new int[] { 1, -1, 0, 0 };

	    // Helper class to store the coordinates of rotten oranges
	    class Pair {
	        int first;
	        int second;

	        public Pair(int first, int second) {
	            this.first = first;
	            this.second = second;
	        }
	    }

	    /**
	     * Intuition:
	     * - We use **BFS (Breadth-First Search)** because rotting spreads level by level (similar to shortest path problems).
	     * - A fresh orange at (i, j) will rot if a rotten orange is in its **4-neighboring** cells.
	     * - We need to track time levels, so we process oranges **level by level** using BFS.
	     * 
	     * Observations:
	     * - If there are no rotten oranges, fresh oranges will never rot -> return -1.
	     * - If there are no fresh oranges at the beginning, return 0 immediately.
	     * - Use a queue to store initially rotten oranges and expand the rot BFS-style.
	     */
	    public int orangesRotting(int[][] grid) {
	        int r = grid.length;  // Number of rows
	        int c = grid[0].length; // Number of columns
	        int ans = 0; // To store the total time required for all oranges to rot

	        Queue<Pair> q = new LinkedList<>(); // BFS queue to store rotten oranges
	        int freshCount = 0; // To track number of fresh oranges

	        // Step 1: Store initial rotten oranges and count fresh oranges
	        for (int i = 0; i < r; i++) {
	            for (int j = 0; j < c; j++) {
	                if (grid[i][j] == 2) {
	                    q.add(new Pair(i, j)); // Push initial rotten oranges into queue
	                } else if (grid[i][j] == 1) {
	                    freshCount++; // Count number of fresh oranges
	                }
	            }
	        }

	        // If there are no fresh oranges, return 0 immediately
	        if (freshCount == 0) return 0;

	        // Step 2: BFS traversal to spread rot level by level
	        while (!q.isEmpty()) {
	            int size = q.size(); // Number of oranges to process at this level
	            int temp = 0; // **Tracks if at least one fresh orange is rotted in this round**
                // **If `temp` remains 0 after a full level of BFS, no new orange rotted**
                // **In that case, we should stop increasing `ans` further.**

	            while (size-- > 0) {
	                Pair front = q.poll(); // Get the front element of queue
	                int i = front.first;
	                int j = front.second;

	                // Check all 4 possible directions
	                for (int k = 0; k <= 3; k++) {
	                    int newX = i + dx[k]; // Move in x direction
	                    int newY = j + dy[k]; // Move in y direction

	                    // Check if new position is within bounds and contains a fresh orange
	                    if (newX >= 0 && newY >= 0 && newX < r && newY < c && grid[newX][newY] == 1) {
	                        q.add(new Pair(newX, newY)); // Add newly rotted orange to queue
	                        grid[newX][newY] = 2; // Mark orange as rotten
	                        freshCount--; // Reduce fresh orange count
	                        temp = 1; // Indicate at least one orange rotted this round
	                    }
	                }
	            }
	            ans += temp; // If at least one orange rotted in this round, increment time
	        }

	        // If there are still fresh oranges left, return -1 (not all oranges could rot)
	        return (freshCount == 0) ? ans : -1;
	    }

	    /**
	     * Dry Run Example:
	     * 
	     * Input:
	     * [[2,1,1],
	     *  [1,1,0],
	     *  [0,1,1]]
	     * 
	     * Step 1: Initial setup
	     * Rotten oranges found at (0,0)
	     * Fresh count = 5
	     * 
	     * Step 2: BFS Level 1
	     * Rotten (0,1), (1,0) -> Fresh count = 3
	     * Time = 1
	     * 
	     * Step 3: BFS Level 2
	     * Rotten (1,1), (2,1) -> Fresh count = 1
	     * Time = 2
	     * 
	     * Step 4: BFS Level 3
	     * Rotten (2,2) -> Fresh count = 0
	     * Time = 3
	     * 
	     * Output: 3 (All oranges rotted in 3 minutes)
	     */
	}

}
