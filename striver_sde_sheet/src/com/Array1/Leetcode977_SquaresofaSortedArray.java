package com.Array1;

public class Leetcode977_SquaresofaSortedArray {
	
	class Solution {
	    public int[] sortedSquares(int[] nums) {
	        int n = nums.length;
	        int[] ans  = new int[n]; // Output array to store the squared values in sorted order
	        int low = 0, high = n - 1; // Two pointers: low (starting from left) and high (starting from right)

	        /**
	         * Intuition:
	         * - The given array `nums` is sorted in **non-decreasing order**, which means it may contain 
	         *   negative numbers followed by positive numbers.
	         * - Squaring a negative number makes it positive, which disrupts the order.
	         * - The **largest squared value** will be from either the leftmost (negative) or rightmost (positive) element.
	         * - We use a **two-pointer approach**:
	         *   - Compare absolute values at `low` and `high`.
	         *   - Place the **larger squared value** at the **end** of `ans` array.
	         *   - Move the corresponding pointer (`low++` or `high--`).
	         * - This allows us to construct the result in **O(N) time complexity** instead of O(N log N) sorting.
	         */

	        // Iterate from the end of `ans` array (largest squared value first)
	        for (int i = n - 1; i >= 0; i--) {
	            // Compare absolute values of `nums[low]` and `nums[high]`
	            if (Math.abs(nums[low]) >= Math.abs(nums[high])) {
	                ans[i] = nums[low] * nums[low]; // Square the larger value
	                low++; // Move `low` pointer forward
	            } else {
	                ans[i] = nums[high] * nums[high]; // Square the larger value
	                high--; // Move `high` pointer backward
	            }
	        }
	        return ans; // Return the sorted squared array
	    }
	}

	/**
	 * Dry Run Example:
	 * 
	 * Input: nums = [-4, -1, 0, 3, 10]
	 * 
	 * Initial Pointers: 
	 * - low = 0, high = 4
	 * - Result array (ans): [_, _, _, _, _]
	 * 
	 * Iteration 1 (i = 4):
	 * - Compare |nums[0]| = 4 and |nums[4]| = 10 → 10 is larger
	 * - ans = [_, _, _, _, 100]
	 * - Move high to 3
	 * 
	 * Iteration 2 (i = 3):
	 * - Compare |nums[0]| = 4 and |nums[3]| = 3 → 4 is larger
	 * - ans = [_, _, _, 16, 100]
	 * - Move low to 1
	 * 
	 * Iteration 3 (i = 2):
	 * - Compare |nums[1]| = 1 and |nums[3]| = 3 → 3 is larger
	 * - ans = [_, _, 9, 16, 100]
	 * - Move high to 2
	 * 
	 * Iteration 4 (i = 1):
	 * - Compare |nums[1]| = 1 and |nums[2]| = 0 → 1 is larger
	 * - ans = [_, 1, 9, 16, 100]
	 * - Move low to 2
	 * 
	 * Iteration 5 (i = 0):
	 * - Compare |nums[2]| = 0 and |nums[2]| = 0 → 0 is squared
	 * - ans = [0, 1, 9, 16, 100]
	 * - Move low to 3
	 * 
	 * Output: [0, 1, 9, 16, 100] (Sorted Squared Array)
	 */


}
