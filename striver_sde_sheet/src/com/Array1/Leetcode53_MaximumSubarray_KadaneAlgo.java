package com.Array1;

public class Leetcode53_MaximumSubarray_KadaneAlgo {
	
//			Why Reset sum = 0 for Negative Sum?

//			If sum becomes negative, carrying it forward only reduces the overall sum.
//			Instead, it's better to start fresh from the next element.
//			This ensures we maximize the sum at every step.
//			Kadane’s Algorithm works in a single pass (O(n)) by continuously checking the sum at each index.
//
//			Time Complexity: O(n), since we traverse the array only once.
//
//			Space Complexity: O(1), since we use only a few variables.
	
	/* Dry Run:
			nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
			maxSum = -∞
			sum = 0
			
			🔸 Step 1: num = -2
			    sum = 0 + (-2) = -2
			    maxSum = max(-∞, -2) = -2
			    sum < 0 → Reset sum to 0
			
			🔸 Step 2: num = 1
			    sum = 0 + 1 = 1
			    maxSum = max(-2, 1) = 1
			
			🔸 Step 3: num = -3
			    sum = 1 + (-3) = -2
			    maxSum = max(1, -2) = 1
			    sum < 0 → Reset sum to 0
			
			🔸 Step 4: num = 4
			    sum = 0 + 4 = 4
			    maxSum = max(1, 4) = 4
			
			🔸 Step 5: num = -1
			    sum = 4 + (-1) = 3
			    maxSum = max(4, 3) = 4
			
			🔸 Step 6: num = 2
			    sum = 3 + 2 = 5
			    maxSum = max(4, 5) = 5
			
			🔸 Step 7: num = 1
			    sum = 5 + 1 = 6
			    maxSum = max(5, 6) = 6
			
			🔸 Step 8: num = -5
			    sum = 6 + (-5) = 1
			    maxSum = max(6, 1) = 6
			
			🔸 Step 9: num = 4
			    sum = 1 + 4 = 5
			    maxSum = max(6, 5) = 6
			
			✅ Final Output: 6 
		*/
	
	class Solution {
	    public int maxSubArray(int[] nums) {
	        int maxSum = Integer.MIN_VALUE;  // Stores the maximum subarray sum found
	        int sum = 0;  // Stores the current subarray sum

	        /* 🔹 Kadane's Algorithm Explanation:
	         * - We iterate over the array while maintaining a running sum.
	         * - If the sum becomes negative, we reset it to 0 (Why? Explained below)
	         */
	        for (int num : nums) {
	            sum += num;  // Add current element to running sum
	            maxSum = Math.max(maxSum, sum);  // Update maxSum if sum is greater

	            /* 🔹 Why do we reset `sum` to 0 when it becomes negative?
	             * - A negative sum means the subarray is **reducing** the overall sum.
	             * - Instead of carrying forward a negative sum, it's **better to start fresh**.
	             * - This ensures we always start with a **positive contribution**.
	             */
	            if (sum < 0) {
	                sum = 0;  // Reset sum because negative sum won't contribute positively
	            }
	        }

	        return maxSum;  // Return the maximum subarray sum found
	    }
	}



}
