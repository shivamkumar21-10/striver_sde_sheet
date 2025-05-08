package com.Array1;

public class Leetcode31_NextPermuataion {
	
//	    1. Find the Breakpoint (First Loop):
//
//		A breakpoint is the first index from the right where nums[i] < nums[i+1].
//		This tells us where the array stops increasing from right to left.
//		If there is no breakpoint, the array is already the largest permutation, so we just reverse it to get the smallest.
	
//		2.Find the Smallest Greater Element (Second Loop):
//
//		Swap the number at the breakpoint with the smallest larger number to its right.
//		This ensures the next permutation is just slightly greater.
	
//		3. Reverse the Right Part:
//
//		The right side of the breakpoint is currently in descending order.
//		Reversing it ensures it's in ascending order, which gives the next smallest possible permutation
	
	
	// Breakpoint dundhenge wha jha n+1 wala n se bara ho agr chota hua to arrange krke v usse bara 
	//	no ni milega to koi arrangement possible ni h
	
	// agr breakpoint mila hi ni to pura array decreasing order m hoga to usko reverse krdo
	
	
	// Breakpoint mil jaega to right side ka sara element piche se dkhe to increasing hoga to right 
	// side se ek loop chlynge aur firse element find krnge jo bara ho breakpoint index se to bol skte h 
	// wo element just bara hoga breakpoint idx se kuki increasing order me h to usse aage jaane pe aur bara ho jaega
	
	//ab jb dono ko swap krnge to jo uske baad wala hoga mtlb breakpoint idx se array k last tk wo to av v increasing hi h
	// to usko reverse krnge to wo sort ho jaega to hmko next permutation mil gya
	
	
	/**
     * Intuition:
     * ----------
     * - We need to find the **next lexicographically greater permutation** of an array.
     * - If no such permutation exists (i.e., the array is sorted in descending order), we return the **smallest permutation** (sorted in ascending order).
     *
     * Observations:
     * ------------
     * - The next permutation is formed by **modifying the existing sequence minimally** to get the next greater order.
     * - We must **swap** and **rearrange** elements while keeping changes minimal.
     *
     * Logic:
     * ------
     * 1. **Find the pivot (breakpoint):**
     *    - Scan from **right to left** and find the first index `i` where `nums[i] < nums[i+1]`.
     *    - This index is the **pivot** (i.e., `nums[i]` is the number that needs to be increased).
     * 2. **Find the successor:**
     *    - Scan from **right to left** again and find the smallest number **greater than** `nums[i]`.
     *    - Swap it with `nums[i]`.
     * 3. **Reverse the suffix:**
     *    - Reverse the sequence to the right of `nums[i]` to get the **smallest order**.
     *
     * Dry Run:
     * --------
     * **Input:** [1, 3, 5, 4, 2]
     *
     * Step 1: Find the pivot
     * ----------------------
     * - Traverse from the right:
     *   - `5 > 4` (no pivot)
     *   - `4 > 2` (no pivot)
     *   - `3 < 5`  ⬅️ **Pivot found at index `1` (value `3`)**
     *
     * Step 2: Find the successor
     * --------------------------
     * - Scan from the right to find the smallest element **greater than** `3`:
     *   - `2` (not greater)
     *   - `4` (greater)  ✅
     *   - Swap `3` and `4`
     *
     * **Array after swap:** [1, 4, 5, 3, 2]
     *
     * Step 3: Reverse the suffix
     * --------------------------
     * - Reverse elements after index `1`:  
     *   - Reverse `[5, 3, 2]` → `[2, 3, 5]`
     *
     * **Final Output:** [1, 4, 2, 3, 5]  ✅
     *
     * Time Complexity:
     * ----------------
     * - `O(N)`, where `N` is the length of the array.
     *
     * Space Complexity:
     * -----------------
     * - `O(1)`, as we modify the array in place.
     */

	
	class Solution {

	    // Utility function to reverse a portion of the array from `start` to `end`
	    void reverse(int[] nums, int start, int end) {
	        while (start < end) {
	            int temp = nums[start];
	            nums[start] = nums[end];
	            nums[end] = temp;
	            start++;
	            end--;
	        }
	    }

	    public void nextPermutation(int[] nums) {
	        int n = nums.length;
	        int idx = -1; // Store the index of the first decreasing element (breakpoint)

	        /* 🔹 Step 1: Find the **breakpoint**
	         * - Traverse from right to left to find the first index `i` such that nums[i] < nums[i+1]
	         * - This identifies the point where the order starts decreasing.
	         */
	        for (int i = n - 2; i >= 0; i--) {
	            if (nums[i] < nums[i + 1]) {
	                idx = i;  // Store the breakpoint index
	                break;
	            }
	        }

	        /* 🔹 Step 2: If no breakpoint is found, the array is in descending order
	         * - This means the array is already the largest permutation.
	         * - We **reverse the entire array** to get the smallest permutation.
	         */
	        if (idx == -1) {
	            reverse(nums, 0, n - 1);
	            return;
	        }

	        /* 🔹 Step 3: Find the **smallest number greater than nums[idx]** on its right side
	         * - This ensures we swap with the smallest larger number to get the next permutation.
	         */
	        for (int i = n - 1; i > idx; i--) {
	            if (nums[i] > nums[idx]) { 
	                // Swap nums[idx] with nums[i] (smallest greater element)
	                int temp = nums[i];
	                nums[i] = nums[idx];
	                nums[idx] = temp;
	                break; // Swap only once
	            }
	        }

	        /* 🔹 Step 4: Reverse the right half to get the next smallest permutation
	         * - The right side was initially in descending order.
	         * - Reversing it makes it the **smallest lexicographical order**.
	         */
	        reverse(nums, idx + 1, n - 1);
	    }
	}

	
	
	

}
