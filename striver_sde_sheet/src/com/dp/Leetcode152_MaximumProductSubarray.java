package com.dp;

public class Leetcode152_MaximumProductSubarray {
	
	class Solution {
	    public int maxProduct(int[] nums) {
	        // Variables to store prefix and suffix product
	        int pre = 1; // Prefix product (left to right)
	        int suff = 1; // Suffix product (right to left)
	        int n = nums.length; // Length of input array

	        // Variable to track the maximum product found so far
	        int maxP = Integer.MIN_VALUE;

	        /*
	         * Observations and Intuition:
	         * 1. A subarray's product can be maximized by considering both left-to-right and right-to-left calculations.
	         * 2. The reason for maintaining two products (pre and suff) is to handle negative numbers and zeros.
	         * 3. Negative Numbers Handling:
	         *    - A single negative number reduces the product, but if there are **even** negative numbers, the result can be positive.
	         *    - If there's an **odd** count of negatives, the product becomes negative.
	         *    - By checking both prefix and suffix, we account for cases where removing an initial or ending negative number can maximize the product.
	         *    - Example: `[-1, -2, -3, -4]` (even negatives → maximum product = 24)
	         *               `[-1, -2, -3]` (odd negatives → best product comes by excluding one)
	         * 4. Handling Zeroes:
	         *    - If a zero is encountered, the product becomes zero.
	         *    - Reset `pre` and `suff` to `1` whenever a zero is encountered since multiplication with zero nullifies the product.
	         * 5. This approach allows us to scan the array in **O(n) time complexity** while considering all contiguous subarrays efficiently.
	         */

	        for (int i = 0; i < n; i++) {
	            // If the prefix product becomes zero, reset it to 1
	            if (pre == 0) pre = 1;
	            // If the suffix product becomes zero, reset it to 1
	            if (suff == 0) suff = 1;

	            // Update prefix product (calculates product from left to right)
	            pre *= nums[i];
	            // Update suffix product (calculates product from right to left)
	            suff *= nums[n - i - 1];

	            // Update maxP with the maximum found so far
	            maxP = Math.max(maxP, Math.max(pre, suff));
	        }
	        
	        return maxP; // Return the maximum product found
	    }
	}

	/*
	Dry Run:
	nums = [2, 3, -2, 4]

	Iteration 1:
	pre = 1 * 2 = 2
	suff = 1 * 4 = 4
	maxP = max(-∞, max(2, 4)) = 4

	Iteration 2:
	pre = 2 * 3 = 6
	suff = 4 * -2 = -8
	maxP = max(4, max(6, -8)) = 6

	Iteration 3:
	pre = 6 * -2 = -12
	suff = -8 * 3 = -24
	maxP = max(6, max(-12, -24)) = 6

	Iteration 4:
	pre = -12 * 4 = -48
	suff = -24 * 2 = -48
	maxP = max(6, max(-48, -48)) = 6

	Final Output: 6

	-------------------------------------
	Handling Negative Cases:
	Example 1:
	nums = [-1, -2, -3, -4]

	Iteration 1:
	pre = -1, suff = -4, maxP = max(-∞, max(-1, -4)) = -1

	Iteration 2:
	pre = -1 * -2 = 2
	suff = -4 * -3 = 12
	maxP = max(-1, max(2, 12)) = 12

	Iteration 3:
	pre = 2 * -3 = -6
	suff = 12 * -2 = -24
	maxP = max(12, max(-6, -24)) = 12

	Iteration 4:
	pre = -6 * -4 = 24
	suff = -24 * -1 = 24
	maxP = max(12, max(24, 24)) = 24

	Final Output: 24 (even negatives result in positive max product)

	-------------------------------------
	Example 2:
	nums = [-1, -2, -3]

	Iteration 1:
	pre = -1, suff = -3, maxP = max(-∞, max(-1, -3)) = -1

	Iteration 2:
	pre = -1 * -2 = 2
	suff = -3 * -2 = 6
	maxP = max(-1, max(2, 6)) = 6

	Iteration 3:
	pre = 2 * -3 = -6
	suff = 6 * -1 = -6
	maxP = max(6, max(-6, -6)) = 6

	Final Output: 6 (odd negatives cause product to become negative, best found by skipping a negative)
	*/



}
