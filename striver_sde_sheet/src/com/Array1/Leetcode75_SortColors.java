package com.Array1;

public class Leetcode75_SortColors {
	
	class Solution {
	    public void sortColors(int[] nums) {
	        int i = 0;                 // Pointer for placing 0s (left boundary)
	        int j = nums.length - 1;    // Pointer for placing 2s (right boundary)
	        int k = 0;                  // Traversal pointer

	        /* 🔹 Dutch National Flag Algorithm
	         * - Traverse the array with `k`
	         * - Swap elements to place 0s on the left and 2s on the right
	         * - `1s` automatically settle in the middle
	         */
	        while (k <= j) {
	            if (nums[k] == 0) { 
	                // Swap current element with nums[i] to move 0s to the left
	                int temp = nums[k];
	                nums[k] = nums[i];
	                nums[i] = temp;
	                i++;    // Move left boundary forward
	                k++;    // Move to the next element
	            } 
	            else if (nums[k] == 1) { 
	                // 1s are already in the correct place
	                k++;
	            } 
	            else if (nums[k] == 2) { 
	                // Swap current element with nums[j] to move 2s to the right
	                int temp = nums[k];
	                nums[k] = nums[j];
	                nums[j] = temp;
	                j--;    // Move right boundary backward
	                // Do NOT increment `k` here, as the swapped value needs to be rechecked
	            } 
	        }
	    }
	}


}
