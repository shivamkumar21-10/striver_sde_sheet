package com.Array2;
import java.util.*;

public class MergeTwoSortedArrayWithoutExtraSpace {
	
	//Approach 1 - optimal 1
	
	//logic 
	//isme hm ek pointer first array k last p rakhe h aur second ko dusre array ke first pe and dono ko compare kr rhe h eg se samajhte h
	
	// nums1 = [1,3,5,7] , nums2 = [0,2,6,8,9]
	
	//final me kya chiye 
	// output - nums1=[0,1,2,3] nums2=[5,6,7,8,9]
	
	//suru ka charo chota nums1 me rhega and bad ka sb nums 2 me to try yhi krte h suru ka charo sbse chota nums1 m laane ka
	
	// i=3, j=0
	//ab 7 bara h 0 se to 0 ko obviously nums1 ka array m rhna chiye to swap kr dnge aur i--; j++ kr dnge
//	nums1 = [1,3,5,0] , nums2 = [7,2,6,8,9]
	
	//i=2, j=1
	//ab 3 bara h 2 se to 2 ko obviously nums1 ka array m rhna chiye to swap kr dnge aur i--; j++ kr dnge
//	nums1 = [1,3,2,0] , nums2 = [7,5,6,8,9]
	
	//i=1, j=2
	//3 6 se chota h to swap ni hoga aur aage v check krne ka jrurat ni h kuki dono sorted array h
//	nums1 = [1,3,2,0] , nums2 = [7,5,6,8,9]
	
	//ab dono array ko alag alag sort kr dnge bs output mil jaega
	
	class Solution {
	    public void merge(int[] nums1, int m, int[] nums2, int n) {
	        int right = 0;
	        int left = n-1;
	        
	        //

	        while(left>=0 && right<m){
	            if(nums1[left] > nums2[right]){
	                int temp = nums1[left];
	                nums1[left] = nums2[right];
	                nums2[right] = temp;
	                left--;
	                right++;
	            }
	            else{
	                break;
	            }
	        }

	        Arrays.sort(nums1);
	        Arrays.sort(nums2);
	    }
	}
	
	// TC - O(m+n) +    O(nlogn) +       O(mlogm)
//	        for loop    for sort nums1   for sort nums2
	
	//SC - O(1)
	
	
	//Approach -2 optimal 2
	//Gap Method (Shell sort)
	
	class Solution2 {
		class Solution {

		    /**
		     * Helper function to swap elements if the left element is greater than the right element.
		     * This ensures elements are in sorted order when merging two sorted arrays.
		     */
		    void swapIfGreater(int[] nums1, int[] nums2, int idx1, int idx2) {
		        if (nums1[idx1] > nums2[idx2]) {
		            int temp = nums1[idx1];
		            nums1[idx1] = nums2[idx2];
		            nums2[idx2] = temp;
		        }
		    }

		    /**
		     * Function to merge two sorted arrays without extra space.
		     * Uses the Gap Method (Shell Sort Variation) to efficiently merge.
		     */
		    public void merge(int[] nums1, int n, int[] nums2, int m) {
		        // Total length of the merged array
		        int len = n + m;

		        // Initial gap value, calculated as (length / 2) + (length % 2)
		        int gap = len / 2 + (len % 2);

		        // Continue the process until gap reduces to zero
		        while (gap > 0) {
		            int left = 0; // Pointer to track the left element
		            int right = left + gap; // Pointer to track the right element

		            // Traverse both arrays with the given gap
		            while (right < len) {
		                /**
		                 * Case 1: Comparing elements from nums1 and nums2
		                 * If left index is in nums1 and right index is in nums2, compare them
		                 */
		                if (left < n && right >= n) {
		                    swapIfGreater(nums1, nums2, left, right - n);
		                }
		                /**
		                 * Case 2: Comparing elements within nums2 itself
		                 * If both left and right indices are in nums2, compare and swap if needed
		                 */
		                else if (left >= n) {
		                    swapIfGreater(nums2, nums2, left - n, right - n);
		                }
		                /**
		                 * Case 3: Comparing elements within nums1 itself
		                 * If both left and right indices are in nums1, compare and swap if needed
		                 */
		                else {
		                    swapIfGreater(nums1, nums1, left, right);
		                }

		                // Move both pointers forward
		                left++;
		                right++;
		            }

		            // If gap is already 1, we have completed sorting, so break the loop
		            if (gap == 1) break;

		            // Reduce the gap using the formula (gap / 2) + (gap % 2)
		            gap = gap / 2 + (gap % 2);
		        }
		    }
		}

		//Dry run
		// Given Input:
//		nums1 = [1, 4, 7], n = 3
//		nums2 = [2, 5, 6], m = 3

		// Initial setup:
//		len = 6 (total elements in both arrays)
//		gap = (6 / 2) + (6 % 2) = 3

		// First Iteration (gap = 3)
//		left = 0, right = 3
		// Compare nums1[0] = 1 with nums2[0] = 2 → No swap needed

//		left = 1, right = 4
		// Compare nums1[1] = 4 with nums2[1] = 5 → No swap needed

//		left = 2, right = 5
		// Compare nums1[2] = 7 with nums2[2] = 6 → Swap needed
		// Swapping 7 and 6 → nums1 = [1, 4, 6], nums2 = [2, 5, 7]

		// Reduce gap using formula: gap = (3 / 2) + (3 % 2) = 2

		// Second Iteration (gap = 2)
//		left = 0, right = 2
		// Compare nums1[0] = 1 with nums1[2] = 6 → No swap needed

//		left = 1, right = 3
		// Compare nums1[1] = 4 with nums2[0] = 2 → Swap needed
		// Swapping 4 and 2 → nums1 = [1, 2, 6], nums2 = [4, 5, 7]

//		left = 2, right = 4
		// Compare nums1[2] = 6 with nums2[1] = 5 → Swap needed
		// Swapping 6 and 5 → nums1 = [1, 2, 5], nums2 = [4, 6, 7]

//		left = 3, right = 5
		// Compare nums2[0] = 4 with nums2[2] = 7 → No swap needed

		// Reduce gap using formula: gap = (2 / 2) + (2 % 2) = 1

		// Third Iteration (gap = 1)
//		left = 0, right = 1
		// Compare nums1[0] = 1 with nums1[1] = 2 → No swap needed

//		left = 1, right = 2
		// Compare nums1[1] = 2 with nums1[2] = 5 → No swap needed

//		left = 2, right = 3
		// Compare nums1[2] = 5 with nums2[0] = 4 → Swap needed
		// Swapping 5 and 4 → nums1 = [1, 2, 4], nums2 = [5, 6, 7]

//		left = 3, right = 4
		// Compare nums2[0] = 5 with nums2[1] = 6 → No swap needed

//		left = 4, right = 5
		// Compare nums2[1] = 6 with nums2[2] = 7 → No swap needed

		// Final Sorted Arrays:
//		nums1 = [1, 2, 4]
//		nums2 = [5, 6, 7]
		
//		Uses the Gap Method (inspired by Shell Sort) to efficiently merge without extra space.
//		Both arrays remain separate but are sorted after merging.
//		Avoids unnecessary shifting by using direct swaps.
//		Time Complexity: O(n log n) (due to reducing gap approach).
//		Space Complexity: O(1) (no extra space used).

	    
//	    Time Complexity: O(n log n) (due to reducing gap and comparisons)
//	    Space Complexity: O(1)
	}
	

}
