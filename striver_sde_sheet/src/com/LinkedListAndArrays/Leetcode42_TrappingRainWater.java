package com.LinkedListAndArrays;

public class Leetcode42_TrappingRainWater {
	
	/**
	 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
	 * this function computes how much water it can trap after raining.
	 *
	 * Intuition:
	 * - Water trapped at a given index depends on the maximum height to its left and right.
	 * - For each index, the water level is determined by the minimum of the maximum heights to the left and right,
	 *   minus the height at that index.
	 * - By precomputing these maximum heights from the left and right (using arrays `mxl` and `mxr`),
	 *   we can efficiently compute the water trapped at each index.
	 *
	 * Logic:
	 * 1. Create two arrays:
	 *    - `mxl`: For each index, stores the maximum height from the start (left) up to that index.
	 *    - `mxr`: For each index, stores the maximum height from the end (right) up to that index.
	 * 2. Precompute `mxl` by traversing the `height` array from left to right.
	 * 3. Precompute `mxr` by traversing the `height` array from right to left.
	 * 4. For each index, compute the water trapped as:
	 *       water[i] = min(mxl[i], mxr[i]) - height[i]
	 *    (If this value is negative, it means no water is trapped at that index.)
	 * 5. Sum the water trapped at each index to obtain the total water trapped.
	 *
	 * Dry Run Example:
	 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
	 * 
	 * Step 1: Compute mxl array (max height to the left including current):
	 *   mxl[0] = 0
	 *   mxl[1] = max(0, 1) = 1
	 *   mxl[2] = max(1, 0) = 1
	 *   mxl[3] = max(1, 2) = 2
	 *   ...
	 *   Final mxl: [0,1,1,2,2,2,2,3,3,3,3,3]
	 *
	 * Step 2: Compute mxr array (max height to the right including current):
	 *   mxr[11] = 1
	 *   mxr[10] = max(1, 2) = 2
	 *   mxr[9] = max(2, 1) = 2
	 *   mxr[8] = max(2, 2) = 2
	 *   ...
	 *   Final mxr: [3,3,3,3,3,3,3,3,2,2,2,1]
	 *
	 * Step 3: Compute water trapped at each index:
	 *   water[0] = min(0, 3) - 0 = 0
	 *   water[1] = min(1, 3) - 1 = 0
	 *   water[2] = min(1, 3) - 0 = 1
	 *   water[3] = min(2, 3) - 2 = 0
	 *   water[4] = min(2, 3) - 1 = 1
	 *   water[5] = min(2, 3) - 0 = 2
	 *   water[6] = min(2, 3) - 1 = 1
	 *   water[7] = min(3, 3) - 3 = 0
	 *   water[8] = min(3, 2) - 2 = 0
	 *   water[9] = min(3, 2) - 1 = 1
	 *   water[10] = min(3, 2) - 2 = 0
	 *   water[11] = min(3, 1) - 1 = 0
	 * 
	 * Total water = 0+0+1+0+1+2+1+0+0+1+0+0 = 6
	 *
	 * Time Complexity: O(n) because we traverse the array a few times.
	 * Space Complexity: O(n) due to the extra arrays (mxl, mxr, water) used.
	 */
	class Solution {
	    public int trap(int[] height) {
	        int n = height.length;
	        int[] mxl = new int[n];    // mxl[i] will hold the maximum height encountered from left up to index i.
	        int[] mxr = new int[n];    // mxr[i] will hold the maximum height encountered from right up to index i.
	        int[] water = new int[n];  // water[i] will store the water trapped at index i.

	        int totalWater = 0; // This will hold the sum of water trapped at all indices.

	        // Step 1: Initialize the first element of mxl with the first height.
	        mxl[0] = height[0];
	        // Initialize the last element of mxr with the last height.
	        mxr[n - 1] = height[n - 1];

	        // Step 2: Fill mxl array by traversing from left to right.
	        // For each index, mxl[i] is the maximum of mxl[i-1] and height[i].
	        for (int i = 1; i < n; i++) {
	            mxl[i] = Math.max(mxl[i - 1], height[i]);
	        }

	        // Step 3: Fill mxr array by traversing from right to left.
	        // For each index, mxr[i] is the maximum of mxr[i+1] and height[i].
	        for (int i = n - 2; i >= 0; i--) {
	            mxr[i] = Math.max(mxr[i + 1], height[i]);
	        }

	        // Step 4: Calculate water trapped at each index.
	        // Water level at index i is the minimum of the max heights to the left and right,
	        // minus the height at that index.
	        for (int i = 0; i < n; i++) {
	            water[i] = Math.min(mxl[i], mxr[i]) - height[i];
	        }

	        // Step 5: Sum all trapped water values.
	        for (int i = 0; i < n; i++) {
	            totalWater += water[i];
	        }

	        // Return the total amount of trapped water.
	        return totalWater;
	    }
	}


}
