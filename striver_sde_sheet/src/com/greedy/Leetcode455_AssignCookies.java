package com.greedy;
import java.util.*;
public class Leetcode455_AssignCookies {
	


	static class Solution {
	    public int findContentChildren(int[] g, int[] s) {
	        /**
	         * Intuition:
	         * - Each child has a greed factor (minimum cookie size they need).
	         * - Each cookie has a size.
	         * - A child is content only if they receive a cookie with size >= their greed factor.
	         * - Our goal is to maximize the number of content children.
	         *
	         * Logic:
	         * 1. **Sort both arrays in ascending order** to efficiently distribute cookies.
	         * 2. Use **two pointers**:
	         *    - `i` → iterates over children (`g` array, greed factors).
	         *    - `j` → iterates over cookies (`s` array, cookie sizes).
	         * 3. If the current cookie (`s[j]`) can satisfy the current child (`g[i]`), assign it:
	         *    - Move to the next child (`i++`).
	         *    - Move to the next cookie (`j++`).
	         * 4. Otherwise, if the cookie is too small, try the next larger cookie (`j++`).
	         * 5. Repeat until all cookies or children are checked.
	         *
	         * Time Complexity: O(N log N + M log M) (sorting dominates)
	         * Space Complexity: O(1) (only pointers used)
	         */
	        
	        Arrays.sort(g); // Sort greed factors (ascending order)
	        Arrays.sort(s); // Sort cookie sizes (ascending order)

	        int i = 0, j = 0; // Two pointers: i for children, j for cookies

	        // Process while there are children and cookies left
	        while (i < g.length && j < s.length) {
	            if (g[i] <= s[j]) { // If the current cookie can satisfy the current child
	                i++; // Move to the next child
	                j++; // Move to the next cookie
	            } else {
	                j++; // If the cookie is too small, try a larger one
	            }
	        }
	        
	        return i; // The number of content children
	    }

	    public static void main(String[] args) {
	        Solution sol = new Solution();
	        int[] g = {1, 2, 3};  // Greed factors
	        int[] s = {1, 1};     // Cookie sizes
	        System.out.println(sol.findContentChildren(g, s)); // Output: 1
	        
	        int[] g2 = {1, 2};    
	        int[] s2 = {1, 2, 3};  
	        System.out.println(sol.findContentChildren(g2, s2)); // Output: 2
	    }
	}

	/**
	 * Dry Run:
	 * 
	 * Example 1:
	 * g = [1, 2, 3], s = [1, 1]
	 * Step 1: Sort both -> g = [1, 2, 3], s = [1, 1]
	 * Step 2: i = 0, j = 0
	 * - g[0] = 1, s[0] = 1 (satisfy) -> i = 1, j = 1
	 * - g[1] = 2, s[1] = 1 (not enough) -> j = 2 (out of cookies)
	 * - Output = 1
	 * 
	 * Example 2:
	 * g = [1, 2], s = [1, 2, 3]
	 * Step 1: Sort both -> g = [1, 2], s = [1, 2, 3]
	 * Step 2: i = 0, j = 0
	 * - g[0] = 1, s[0] = 1 (satisfy) -> i = 1, j = 1
	 * - g[1] = 2, s[1] = 2 (satisfy) -> i = 2, j = 2
	 * - Output = 2
	 */


}
