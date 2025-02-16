package com.Array3;

public class Leetcode74_Searcha2DMatrix {
	
	class Solution {
	    // Binary search function to find the target in a sorted 1D array
	    boolean bs(int[] nums, int target) {
	        int i = 0; // Left pointer
	        int j = nums.length - 1; // Right pointer

	        while (i <= j) { // Continue searching while left <= right
	            int mid = (i + j) / 2; // Calculate the middle index

	            if (nums[mid] == target) { 
	                return true; // Target found
	            } 
	            else if (nums[mid] > target) { 
	                // If middle element is greater than target, search in left half
	                j = mid - 1;
	            } 
	            else { 
	                // If middle element is smaller than target, search in right half
	                i = mid + 1;
	            }
	        }
	        return false; // Target not found
	    }

	    public boolean searchMatrix(int[][] matrix, int target) {
	        int rowNum = -1; // Variable to store the row index where target might be found
	        
	        // Step 1: Find the correct row where the target might be present
	        for (int i = 0; i < matrix.length; i++) {
	            int firstElement = matrix[i][0]; // First element of the row
	            int lastElement = matrix[i][matrix[0].length - 1]; // Last element of the row

	            // Check if the target is within the range of the current row
	            if (target >= firstElement && target <= lastElement) {
	                rowNum = i; // Set rowNum to the current row index
	                break; // Stop checking further rows as we found the right row
	            }
	        }

	        // Step 2: If no valid row is found, return false
	        if (rowNum == -1) {
	            return false;
	        }

	        // Step 3: Perform binary search in the identified row
	        return bs(matrix[rowNum], target);
	    }
	}


}
