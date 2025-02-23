package com.LinkedListAndArrays;

public class Leetcode26_RemoveDuplicatesfromSortedArray {
	
	class Solution {
	    public int removeDuplicates(int[] nums) {
	        // Initialize two pointers: 
	        // i -> slow pointer (tracks unique elements)
	        // j -> fast pointer (scans through the array)
	        int i = 0; 
	        int j = 1;

	        // Traverse the array using j
	        while (j < nums.length) {
	            // If we find a new unique element (not a duplicate)
	            if (nums[j] != nums[i]) {
	                i++; // Move the slow pointer forward
	                nums[i] = nums[j]; // Replace the duplicate position with the unique element
	            }
	            j++; // Move the fast pointer forward
	        }

	        // Since i is zero-based, the length of the unique elements array is i+1
	        return i + 1;
	    }
	}

	/**
	 * Intuition:
	 * - The given array is sorted, meaning duplicates will always appear next to each other.
	 * - We need to remove duplicates in-place and return the number of unique elements.
	 * - Instead of using extra space, we use two pointers:
	 *   - `i` keeps track of the last unique element.
	 *   - `j` scans through the array.
	 *   - Whenever a new unique number is found, `i` moves forward and we overwrite `nums[i]` with `nums[j]`.
	 *
	 * Key Points:
	 * - The array remains partially sorted, with unique elements moved to the front.
	 * - The elements beyond the returned length are irrelevant.
	 * - The function modifies the array in-place without using extra space.
	 *
	 * Dry Run Example:
	 * =================
	 * Input: nums = [1, 1, 2, 2, 3, 4, 4, 5]
	 * 
	 * Initial: i = 0, j = 1
	 * 
	 * Step-by-step Execution:
	 * ---------------------------------
	 * j = 1: nums[1] (1) == nums[0] (1) → Skip j++
	 * j = 2: nums[2] (2) != nums[0] (1) → i++, nums[i] = nums[j] → [1, 2, 2, 2, 3, 4, 4, 5]
	 * j = 3: nums[3] (2) == nums[1] (2) → Skip j++
	 * j = 4: nums[4] (3) != nums[1] (2) → i++, nums[i] = nums[j] → [1, 2, 3, 2, 3, 4, 4, 5]
	 * j = 5: nums[5] (4) != nums[2] (3) → i++, nums[i] = nums[j] → [1, 2, 3, 4, 3, 4, 4, 5]
	 * j = 6: nums[6] (4) == nums[3] (4) → Skip j++
	 * j = 7: nums[7] (5) != nums[3] (4) → i++, nums[i] = nums[j] → [1, 2, 3, 4, 5, 4, 4, 5]
	 * 
	 * Final output: [1, 2, 3, 4, 5, _, _, _] (only first 5 elements matter)
	 * Return value: 5 (number of unique elements)
	 *
	 * Time Complexity: O(N) (We traverse the array once)
	 * Space Complexity: O(1) (In-place modification, no extra space used)
	 */


}
