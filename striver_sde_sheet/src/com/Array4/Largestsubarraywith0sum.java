package com.Array4;

import java.util.*;
public class Largestsubarraywith0sum {


	class Solution {
	    /**
	     * Function to find the length of the longest subarray with sum = 0.
	     * Uses the Prefix Sum + HashMap approach.
	     */
	    int maxLen(int arr[]) {
	        // HashMap to store the first occurrence of a prefix sum
	        Map<Integer, Integer> sumMap = new HashMap<>();
	        int sum = 0;  // Variable to store the cumulative sum of elements
	        int maxi = 0; // Variable to store the maximum length of the subarray

	        for (int j = 0; j < arr.length; j++) {
	            sum += arr[j]; // Update the prefix sum
	            
	            // Case 1: If prefix sum becomes zero, entire subarray [0..j] has sum=0
	            if (sum == 0) {
	                maxi = j + 1;
	            }

	            // Case 2: If the same sum has been seen before, it means
	            // the subarray between the previous index and the current index has sum=0.
	            if (sumMap.containsKey(sum)) {
	                // Update maximum length if we found a longer valid subarray
	                maxi = Math.max(maxi, j - sumMap.get(sum));
	            } else {
	                // Store the first occurrence of this sum
	                sumMap.put(sum, j);
	            }
	        }

	        return maxi;
	    }
	}

	/**
	 * 🔹 **Intuition**:
	 * - The problem requires finding the longest subarray with sum=0.
	 * - Using a HashMap, we track **prefix sums** while iterating.
	 * - If the **same prefix sum appears twice**, then the subarray between
	 *   those two indices must sum to zero.
	 * - The first occurrence of each sum is stored in the HashMap to find
	 *   the maximum length of such subarrays efficiently.
	 *
	 * 🔹 **Dry Run**:
	 * Input: arr = [4, -3, -1, 2, 1]
	 *
	 * | Index (j) | arr[j] | Prefix Sum (sum) | First Occurrence in HashMap | maxi |
	 * |-----------|--------|------------------|-----------------------------|------|
	 * | 0         | 4      | 4                | {4 → 0}                     | 0    |
	 * | 1         | -3     | 1                | {4 → 0, 1 → 1}              | 0    |
	 * | 2         | -1     | 0                | {4 → 0, 1 → 1, 0 → 2}       | 3    |
	 * | 3         | 2      | 2                | {4 → 0, 1 → 1, 0 → 2, 2 → 3}| 3    |
	 * | 4         | 1      | 3                | {4 → 0, 1 → 1, 0 → 2, 2 → 3, 3 → 4}| 3 |
	 *
	 * Final Answer: `maxi = 3`
	 *
	 * 🔹 **Observations**:
	 * - If `sum == 0`, then the subarray from `0 → j` is valid.
	 * - If `sum` appears again in `sumMap`, the subarray between first occurrence and `j` has sum=0.
	 * - Instead of brute force **O(N²)**, this approach runs in **O(N)** time.
	 *
	 * 🔹 **Complexity Analysis**:
	 * - **Time Complexity**: `O(N)`, since each element is processed at most twice (once for sum, once for lookup).
	 * - **Space Complexity**: `O(N)`, as we store prefix sums in the HashMap.
	 */


}
