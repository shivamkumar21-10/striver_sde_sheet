package com.BinarySearch;

public class Leetcode540_SingleElementinaSortedArray {
	
	class Solution {
	    public int singleNonDuplicate(int[] nums) {
	        int n = nums.length; // Get the length of the array
	        int low = 0, high = n - 1; // Set up the binary search range

	        // Edge case: If the array contains only one element, return that element directly
	        if (n == 1) return nums[0];

	        // Edge case: If the first element is unique (not equal to the second one)
	        if (nums[0] != nums[1]) return nums[0];

	        // Edge case: If the last element is unique (not equal to the second last one)
	        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

	        // Binary search approach to find the unique element
	        while (low <= high) {
	            int mid = (low + high) / 2; // Find the middle index
	            
	            // Check if mid itself is the unique element
	            if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid]) {
	                return nums[mid]; // Return the single non-duplicate element
	            }

	            /*
	            Intuition:
	            - The array consists of pairs of elements, except for one unique element.
	            - Normally, elements appear twice in a sorted array.
	            - The unique element disrupts the normal pairing pattern.

	            Key Observations:
	            - If mid is at an **even index** and its **previous element is the same**, 
	              it means the unique element is on the **left side** → Move `high` to `mid - 1`.
	            - If mid is at an **odd index** and its **next element is the same**, 
	              it also means the unique element is on the **left side** → Move `high` to `mid - 1`.
	            - Otherwise, the unique element is on the **right side** → Move `low` to `mid + 1`.
	            */

	            if ((mid % 2 == 0 && nums[mid] == nums[mid - 1]) || 
	                (mid % 2 == 1 && nums[mid] == nums[mid + 1])) {
	                high = mid - 1; // Move to the left half
	            } else {
	                low = mid + 1; // Move to the right half
	            }
	        }

	        // This return statement is just a safeguard (the loop will always find the answer)
	        return -1;
	    }
	}

	/*
	-----------------------------------
	    Dry Run Example:
	-----------------------------------
	Input: nums = [1,1,2,3,3,4,4,8,8]
	- Initial low = 0, high = 8

	1st iteration:
	  mid = (0+8)/2 = 4 (index 4 → value = 3)
	  nums[mid] == nums[mid-1] (3 == 3) → Move left, high = 3

	2nd iteration:
	  mid = (0+3)/2 = 1 (index 1 → value = 1)
	  nums[mid] == nums[mid-1] (1 == 1) → Move right, low = 2

	3rd iteration:
	  mid = (2+3)/2 = 2 (index 2 → value = 2)
	  nums[mid-1] != nums[mid] && nums[mid+1] != nums[mid]
	  Found unique element → Return 2

	Output: 2
	*/


}
