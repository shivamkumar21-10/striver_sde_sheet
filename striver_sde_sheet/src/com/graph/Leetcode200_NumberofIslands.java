package com.graph;

public class Leetcode200_NumberofIslands {
	
	class Solution {
	    // Arrays to define possible movements in 4 directions (Right, Left, Down, Up)
	    int[] dx = new int[] { 0, 0, 1, -1 };
	    int[] dy = new int[] { 1, -1, 0, 0 };
	    int r; // Number of rows in the grid
	    int c; // Number of columns in the grid

	    /**
	     * DFS function to traverse and mark all connected land ('1') as visited.
	     * It ensures that once an island is counted, all its parts are explored.
	     */
	    void dfs(char[][] grid, int i, int j) {
	        // Base case: If out of bounds OR cell is already visited OR it's water ('0'), return.
	        if (i < 0 || j < 0 || i >= r || j >= c || grid[i][j] != '1') return;

	        // Mark the current cell as visited to avoid revisiting
	        grid[i][j] = '2'; // Using '2' to mark as visited (any non-'1' character would work)

	        // Explore all 4 possible directions (Right, Left, Down, Up)
	        for (int k = 0; k < 4; k++) { // Loop over direction arrays
	            int ii = i + dx[k]; // New row index
	            int jj = j + dy[k]; // New column index
	            dfs(grid, ii, jj);  // Recursive call to explore neighboring cells
	        }
	    }

	    /**
	     * Function to count the number of islands in the grid.
	     * An island is a group of connected '1's (land) surrounded by '0's (water).
	     */
	    public int numIslands(char[][] grid) {
	        r = grid.length;     // Get number of rows
	        c = grid[0].length;  // Get number of columns
	        int count = 0;       // Initialize island count

	        // Iterate through each cell in the grid
	        for (int i = 0; i < r; i++) {
	            for (int j = 0; j < c; j++) {
	                if (grid[i][j] == '1') { // If the cell is land ('1'), start DFS
	                    dfs(grid, i, j); // Explore the entire island
	                    count++; // Increase the island count
	                }
	            }
	        }
	        return count; // Return total number of islands found
	    }
	}

	/**
	 * Observations & Intuition:
	 * -------------------------
	 * - A matrix/grid represents a **map of land ('1') and water ('0')**.
	 * - An **island is a group of adjacent land cells ('1')**.
	 * - Our goal is to count how many **distinct islands** exist.
	 * - Two cells belong to the same island **if they are connected horizontally or vertically**.
	 *
	 * Logic Breakdown:
	 * ----------------
	 * - Traverse each cell in the grid.
	 * - If we find a land cell ('1'), it means we've found a new island.
	 * - Perform **DFS (Depth-First Search)** to mark the entire island as visited.
	 * - Increase the island count.
	 * - Continue exploring until all islands are counted.
	 *
	 * Dry Run:
	 * --------
	 * Input Grid:
	 * 1 1 0 0 0
	 * 1 1 0 0 0
	 * 0 0 1 0 0
	 * 0 0 0 1 1
	 *
	 * Step-by-Step Execution:
	 * 1. Start at (0,0) → '1' found → Start DFS → Mark all connected land as '2'.
	 * 2. Move to (2,2) → '1' found → Start DFS → Mark as '2'.
	 * 3. Move to (3,3) → '1' found → Start DFS → Mark (3,3) & (3,4) as '2'.
	 * 4. Continue traversing, no more unvisited '1's found.
	 * 5. Island count = 3.
	 *
	 * Output: 3 (since there are 3 separate islands)
	 *
	 * Time Complexity: **O(N*M)**
	 * - We visit each cell **once**.
	 * - DFS spreads over all connected components (islands).
	 *
	 * Space Complexity: **O(N*M)**
	 * - In the worst case (all land), the recursion stack could be **O(N*M)**.
	 */


}
