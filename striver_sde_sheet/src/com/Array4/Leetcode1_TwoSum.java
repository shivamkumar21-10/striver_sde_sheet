package com.Array4;
import java.util.Arrays;
public class Leetcode1_TwoSum {
	


	class Solution {
	    /**
	     * Finds two numbers in the array that add up to the target and returns their indices.
	     * 
	     * @param nums   The input array of integers.
	     * @param target The target sum.
	     * @return An array of two indices whose values sum up to the target.
	     */
	    public int[] twoSum(int[] nums, int target) {
	        int n = nums.length;

	        // Step 1: Store numbers along with their original indices in a 2D array.
	        int[][] arr = new int[n][2];

	        // This array will store the indices of the two numbers that form the sum.
	        int[] ans = new int[2];

	        // Populate the 2D array with values and their corresponding indices.
	        for (int i = 0; i < n; i++) {
	            arr[i][0] = nums[i]; // Store the value
	            arr[i][1] = i;       // Store the original index
	        }

	        // Step 2: Sort the array based on values (first column).
	        // Sorting helps in efficiently searching for the target sum using the two-pointer technique.
	        Arrays.sort(arr, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));

	        // Step 3: Use two pointers to find the pair that sums to the target.
	        int i = 0;        // Left pointer
	        int j = n - 1;    // Right pointer

	        while (i < j) {
	            int sum = arr[i][0] + arr[j][0]; // Compute the sum of the two selected elements.

	            if (sum == target) { 
	                // Step 4: If we find a match, return the original indices.
	                ans[0] = arr[i][1];
	                ans[1] = arr[j][1];
	                return ans;
	            } 
	            else if (sum > target) { 
	                // If the sum is greater than the target, move the right pointer to a smaller number.
	                j--;
	            } 
	            else { 
	                // If the sum is less than the target, move the left pointer to a larger number.
	                i++;
	            }
	        }

	        // If no valid pair is found, return {-1, -1}.
	        return new int[]{-1, -1};
	    }
	}

	/**
	 * 🔍 **Intuition & Why We Use Sorting + Two-Pointer Technique**
	 * ------------------------------------------------------------
	 * - A **brute force approach** would involve checking all pairs, which takes **O(n²)** time.
	 * - Instead, **sorting** allows us to use **two pointers** to find the pair in **O(n log n)** (due to sorting) + **O(n)** (for searching).
	 * - The **main observation**:
	 *     - **Sorting helps us efficiently move pointers based on the sum.**
	 *     - If sum is **too large**, we reduce the larger number (`j--`).
	 *     - If sum is **too small**, we increase the smaller number (`i++`).
	 *
	 * **🔍 Dry Run Example**
	 * ----------------------
	 * **Input:** `nums = [3, 2, 4], target = 6`
	 * 
	 * **Step 1: Store values with indices**  
	 * ```
	 * arr = [[3, 0], [2, 1], [4, 2]]
	 * ```
	 * 
	 * **Step 2: Sort by values**  
	 * ```
	 * arr = [[2, 1], [3, 0], [4, 2]]
	 * ```
	 * 
	 * **Step 3: Two-pointer search**  
	 * - `2 + 4 = 6` (Match found) → return `[1, 2]`
	 *
	 * **Final Complexity:**  
	 * - **O(n log n)** due to sorting.
	 * - **O(n)** for two-pointer traversal.
	 * - **Overall: O(n log n), better than O(n²).**
	 */


}
