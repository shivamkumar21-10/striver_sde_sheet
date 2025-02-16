package com.Array3;
import java.util.*;

public class Leetcode493_ReversePairs {
	
	class Solution {
	    int count; // Global counter to store the count of reverse pairs

	    /**
	     * This function merges two sorted halves while maintaining order.
	     * Merge Sort helps in sorting and also enables efficient pair counting.
	     * 
	     * @param arr The input array
	     * @param low Starting index of the left subarray
	     * @param mid Midpoint index dividing left and right subarrays
	     * @param high Ending index of the right subarray
	     */
	    private void merge(int[] arr, int low, int mid, int high) {
	        ArrayList<Integer> temp = new ArrayList<>();
	        int left = low;
	        int right = mid + 1;

	        // Merge the two sorted halves into a temporary list
	        while (left <= mid && right <= high) {
	            if (arr[left] <= arr[right]) {
	                temp.add(arr[left]);
	                left++;
	            } else {
	                temp.add(arr[right]);
	                right++;
	            }
	        }

	        // Add remaining elements from the left half
	        while (left <= mid) {
	            temp.add(arr[left]);
	            left++;
	        }

	        // Add remaining elements from the right half
	        while (right <= high) {
	            temp.add(arr[right]);
	            right++;
	        }

	        // Copy the sorted elements back into the original array
	        for (int i = low; i <= high; i++) {
	            arr[i] = temp.get(i - low);
	        }
	    }

	    /**
	     * This function counts the number of reverse pairs before merging.
	     * It iterates through the left half and finds the number of valid j's in the right half.
	     * 
	     * @param arr The input array
	     * @param low Starting index of the left subarray
	     * @param high Ending index of the right subarray
	     * @param mid Midpoint index dividing left and right subarrays
	     */
	    public void countPairs(int[] arr, int low, int high, int mid) {
	        int right = mid + 1;

	        // Iterate over the left half elements
	        for (int i = low; i <= mid; i++) {
	            // Find the first element in the right half that is *not* a reverse pair
	            while (right <= high && (long) arr[i] > 2L * arr[right]) right++;
	            count += right - (mid + 1); // Count valid pairs
	        }
	    }

	    /**
	     * The main function implementing Merge Sort with Reverse Pair counting.
	     * It follows the divide and conquer approach:
	     * 1. Recursively sort left and right halves
	     * 2. Count reverse pairs before merging
	     * 3. Merge the two halves
	     * 
	     * @param arr The input array
	     * @param low Starting index
	     * @param high Ending index
	     */
	    public void mergeSort(int[] arr, int low, int high) {
	        if (low >= high) return; // Base condition for recursion

	        int mid = (low + high) / 2; // Find the midpoint

	        // Recursively sort the left half
	        mergeSort(arr, low, mid);

	        // Recursively sort the right half
	        mergeSort(arr, mid + 1, high);

	        // Count valid reverse pairs before merging
	        countPairs(arr, low, high, mid);

	        // Merge the sorted halves
	        merge(arr, low, mid, high);
	    }

	    /**
	     * The main function that initializes the counting process.
	     * It calls mergeSort, which recursively sorts and counts reverse pairs.
	     * 
	     * @param nums The input array
	     * @return Total count of reverse pairs
	     */
	    public int reversePairs(int[] nums) {
	        count = 0; // Initialize counter
	        mergeSort(nums, 0, nums.length - 1);
	        return count; // Return final count
	    }
	}

	/**
	 * 🔍 **Intuition & Observations**
	 * --------------------------------
	 * 1️⃣ **Brute Force Approach** (O(n²))
	 * - A naive approach is to check each (i, j) pair and count if `nums[i] > 2 * nums[j]`.
	 * - This results in **TLE (Time Limit Exceeded)** for large inputs.
	 * 
	 * 2️⃣ **Optimized Approach - Merge Sort (O(n log n))**
	 * - Instead of checking every pair individually, we **sort** the array and count valid pairs **before merging**.
	 * - The key idea is that the left half and right half are already sorted.
	 * - This allows us to efficiently count valid pairs in **O(n)** time instead of O(n²).
	 * 
	 * 🔍 **Dry Run Example**
	 * --------------------------------
	 * **Input:** `[5, 2, 6, 1]`
	 * 
	 * **Step 1: Recursive Breakdown**
	 * ```
	 *  [5, 2, 6, 1]
	 *     /     \
	 *  [5, 2]  [6, 1]
	 *   /  \    /   \
	 * [5]  [2] [6]  [1]
	 * ```
	 * 
	 * **Step 2: Count Reverse Pairs**
	 * - Merge `[5]` and `[2]`: **Count = 1** (`5 > 2 * 2`)
	 * - Merge `[6]` and `[1]`: **Count = 1** (`6 > 2 * 1`)
	 * - Merge `[2, 5]` and `[1, 6]`: **Final Count = 2**
	 * 
	 * **Final Sorted Array:** `[1, 2, 5, 6]`
	 * **Total Reverse Pairs:** `2`
	 * 
	 * 📌 **Key Takeaways**
	 * - Sorting **helps in efficiently counting** reverse pairs.
	 * - **Merge Sort** divides the array and counts pairs before merging.
	 * - Instead of checking all pairs, we use a **two-pointer approach** while merging.
	 */


}
