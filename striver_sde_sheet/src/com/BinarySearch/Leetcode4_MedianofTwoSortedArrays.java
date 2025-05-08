package com.BinarySearch;

public class Leetcode4_MedianofTwoSortedArrays {
	
	class Solution {
	    /**
	     * This function finds the median of two sorted arrays using a two-pointer approach.
	     * 
	     * Intuition:
	     * - We need to find the median of two sorted arrays.
	     * - Instead of merging both arrays explicitly, we track elements using two pointers.
	     * - The median is found by keeping track of the middle elements while iterating.
	     * 
	     * Logic:
	     * - We calculate two indices `idx1` and `idx2` which represent the median positions.
	     * - We iterate through both arrays while maintaining a count (`cnt`) of elements seen.
	     * - When `cnt` reaches `idx1` and `idx2`, we store their corresponding elements.
	     * - If the total length is odd, return `idx1ele`, otherwise return the average of `idx1ele` and `idx2ele`.
	     */
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int i = 0;
	        int j = 0;

	        int n1 = nums1.length;
	        int n2 = nums2.length;
	        int n = (n1 + n2); // Total size of merged array

	        int idx1 = (n - 1) / 2, idx2 = n / 2, cnt = 0; // Median index tracking

	        int idx1ele = -1, idx2ele = -1; // Elements at median positions

	        // Merging two sorted arrays using two-pointer approach
	        while (i < n1 && j < n2) {
	            if (nums1[i] < nums2[j]) { // Pick smaller element from nums1 or nums2
	                if (cnt == idx1)
	                    idx1ele = nums1[i];
	                if (cnt == idx2)
	                    idx2ele = nums1[i];
	                cnt++;
	                i++;
	            } else {
	                if (cnt == idx1)
	                    idx1ele = nums2[j];
	                if (cnt == idx2)
	                    idx2ele = nums2[j];
	                cnt++;
	                j++;
	            }
	        }

	        // If elements remain in nums1, continue processing
	        while (i < n1) {
	            if (cnt == idx1)
	                idx1ele = nums1[i];
	            if (cnt == idx2)
	                idx2ele = nums1[i];
	            cnt++;
	            i++;
	        }

	        // If elements remain in nums2, continue processing
	        while (j < n2) {
	            if (cnt == idx1)
	                idx1ele = nums2[j];
	            if (cnt == idx2)
	                idx2ele = nums2[j];
	            cnt++;
	            j++;
	        }

	        /**
	         * Dry Run Example:
	         * nums1 = [1, 3], nums2 = [2]
	         * Total length = 3, idx1 = 1, idx2 = 1
	         * Merging: [1, 2, 3]
	         * idx1ele = 2, idx2ele = 2
	         * Median = 2
	         */

	        // Return the median
	        if (n % 2 == 1) {
	            return idx1ele; // Odd length, single median element
	        } else {
	            return (idx1ele + idx2ele) / 2.0; // Even length, average of two middle elements
	        }
	    }
	}


}
