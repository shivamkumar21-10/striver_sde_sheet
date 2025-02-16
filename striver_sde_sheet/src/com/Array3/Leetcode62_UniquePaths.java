package com.Array3;
import java.util.*;

public class Leetcode62_UniquePaths {
	
	class Solution {
	    /**
	     * This function finds the number of unique paths from the top-left (0,0) to the bottom-right (m-1, n-1)
	     * in an m x n grid, moving only right or down.
	     * 
	     * Intuition:
	     * 1. The problem can be broken down into subproblems, making it a great candidate for recursion with memoization.
	     * 2. From any cell (i, j), we can move either right (i, j+1) or down (i+1, j).
	     * 3. The number of unique paths to (m-1, n-1) is the sum of paths from the right and downward cells.
	     * 4. Memoization is used to store results of overlapping subproblems to optimize performance.
	     */
	    
	    int helper(int m, int n, int i, int j, int[][] dp) {
	        // If out of bounds, return 0 (invalid path)
	        if (i >= m || j >= n) {
	            dp[i][j] = 0;
	            return 0;
	        }

	        // If we reach the destination (bottom-right cell), return 1
	        if (i == m - 1 && j == n - 1) {
	            dp[i][j] = 1;
	            return 1;
	        }

	        // If already computed, return stored value (memoization)
	        if (dp[i][j] != -1) {
	            return dp[i][j];
	        }

	        // Recursive calls for right and down movements
	        int right = helper(m, n, i, j + 1, dp);
	        int down = helper(m, n, i + 1, j, dp);

	        // Store result and return the sum of right and down paths
	        return dp[i][j] = right + down;
	    }

	    public int uniquePaths(int m, int n) {
	        // DP array to store computed values
	        int[][] dp = new int[m + 1][n + 1];

	        // Initialize all values to -1 (indicating uncomputed states)
	        for (int i = 0; i < m; i++) {
	            Arrays.fill(dp[i], -1);
	        }

	        // Start from (0,0) and calculate the unique paths
	        return helper(m, n, 0, 0, dp);
	    }
	}

	/**
	 * Dry Run Example:
	 * 
	 * Input: m = 3, n = 3
	 * 
	 * Grid Representation:
	 * (0,0) → (0,1) → (0,2)
	 *  ↓         ↓       ↓
	 * (1,0) → (1,1) → (1,2)
	 *  ↓         ↓       ↓
	 * (2,0) → (2,1) → (2,2)
	 * 
	 * Steps:
	 * 1. Start at (0,0), can move right or down.
	 * 2. Each cell stores the sum of paths from its right and down neighbors.
	 * 3. Base case: At (2,2), return 1.
	 * 4. Compute recursively, storing results in `dp` to avoid recomputation.
	 * 
	 * Final DP Table:
	 * ┌───┬───┬───┐
	 * │ 6 │ 3 │ 1 │
	 * ├───┼───┼───┤
	 * │ 3 │ 2 │ 1 │
	 * ├───┼───┼───┤
	 * │ 1 │ 1 │ 1 │
	 * └───┴───┴───┘
	 * 
	 * Output: 6 (There are 6 unique paths from (0,0) to (2,2))
	 */


}
