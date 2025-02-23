package com.LinkedListAndArrays;

public class Leetcode485_MaxConsecutiveOnes {
	
	class Solution {
	    public int findMaxConsecutiveOnes(int[] nums) {
	        int cnt = 0;  // Stores the current count of consecutive 1s
	        int maxx = 0; // Stores the maximum count of consecutive 1s found so far

	        // Iterate through each element in the array
	        for (int num : nums) {
	            if (num == 1) {
	                cnt++; // Increase count if the current element is 1
	                maxx = Math.max(maxx, cnt); // Update maxx if the new count is greater
	            } else {
	                cnt = 0; // Reset count when encountering a 0
	            }
	        }
	        return maxx; // Return the maximum number of consecutive 1s found
	    }
	}

	/**
	 * Intuition:
	 * - We need to find the maximum number of consecutive 1s in the array.
	 * - We maintain a counter `cnt` to track the current streak of 1s.
	 * - If we encounter a `1`, we increment `cnt` and update `maxx` if needed.
	 * - If we encounter a `0`, we reset `cnt` because the sequence is broken.
	 * - The final answer is stored in `maxx`, which keeps track of the maximum streak.

	 * Dry Run Example:
	 * Input: nums = [1, 1, 0, 1, 1, 1]
	 *
	 * Iteration 1: num = 1 → cnt = 1, maxx = 1
	 * Iteration 2: num = 1 → cnt = 2, maxx = 2
	 * Iteration 3: num = 0 → cnt = 0 (reset)
	 * Iteration 4: num = 1 → cnt = 1, maxx = 2
	 * Iteration 5: num = 1 → cnt = 2, maxx = 2
	 * Iteration 6: num = 1 → cnt = 3, maxx = 3
	 *
	 * Output: 3 (maximum consecutive 1s found)
	 *
	 * Time Complexity: O(N) - We traverse the array once.
	 * Space Complexity: O(1) - We use only a few integer variables.
	 */


}
