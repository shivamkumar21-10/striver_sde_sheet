package com.Array2;

import java.util.*; 
import java.io.*; 

public class Leetcode3193_CounttheNumberofInversions {
	
	/* Intution
	 * 
	 * lets forget about the problem for know lets build a intution 
	 * 
	 * left = [2,3,5,6]     right = [2,2,4,4,8]  -  both are sorted array
	 * 
	 * now we need to find number of pairs left > right
	 * 
	 * take two pointers i and j , i--0 and j==0
	 * 
	 * STEP-1
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8]
	 *  |                        |
	 *  V                        V
	 *  i                        j
	 *  
	 *  count = 0
	 *  
	 *  now left[i] > right[j] is false we move i++;
	 *  
	 *  STEP-2
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *     |                     |
	 *     V                     V
	 *     i                     j
	 *     
	 *     count = 0+3;
	 * 
	 * now left[i] > right[j] is true 
	 * so all the elements right to 3 in left array can make a pair with right[j] kuki sorted array h.
	 * we increase count by 3 and move j++
	 *
	 *STEP-3
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *     |                        |
	 *     V                        V
	 *     i                        j
	 *     
	 *     count = 0+3+3;
	 *     
	 * now left[i] > right[j] is true 
	 * so all the elements right to 3 in left array can make a pair with right[j] kuki sorted array h.
	 * we increase count by 3 and move j++
	 * 
	 * STEP-4
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *     |                           |
	 *     V                           V
	 *     i                           j
	 *     
	 * now left[i] > right[j] is false we move i++;
	 * 
	 * STEP-5
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *        |                        |
	 *        V                        V
	 *        i                        j
	 *     
	 *     count = 0+3+3+2;
	 *     
	 * now left[i] > right[j] is true 
	 * so all the elements right to 5 in left array can make a pair with right[j] kuki sorted array h.
	 * we increase count by 3 and move j++
	 * 
	 * STEP-6
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *        |                           |
	 *        V                           V
	 *        i                           j
	 *     
	 *     count = 0+3+3+2+2;
	 *     
	 * now left[i] > right[j] is true 
	 * so all the elements right to 5 in left array can make a pair with right[j] kuki sorted array h.
	 * we increase count by 3 and move j++
	 * 
	 * STEP-7
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *        |                              |
	 *        V                              V
	 *        i                              j
	 *        
	 *        count = 0+3+3+2+2;
	 *     
	 * now left[i] > right[j] is false we move i++; 
	 * 
	 * STEP-8
	 * [2, 3, 5, 6]             [2, 2, 4, 4, 8] 
	 *           |                           |
	 *           V                           V
	 *           i                           j
	 *        
	 *        count = 0+3+3+2+2;
	 *     
	 * now left[i] > right[j] is false we move i++; 
	 * 
	 *  left array ended to count = 10 is final 
	 */
	
	/* LOGIC from intution
	 *  WE can make use of merge sort and at the time of merge we do this logic to count 
	 *  it becuse in merge sort every time both side array is sorted
	 * 
	 * /*
		    Given Array: [5, 3, 2, 4, 1]
		
		    Merge Sort follows a "Divide and Conquer" approach:
		    - Recursively divide the array into halves until each half contains only one element.
		    - Merge the sorted halves back together in sorted order.
		
		    Recursive Tree for Merge Sort on [5, 3, 2, 4, 1]:
		
		                           [5, 3, 2, 4, 1]
		                          /               \
		                [5, 3, 2]                  [4, 1]
		               /        \                 /     \
		          [5, 3]       [2]            [4]       [1]
		         /     \
		       [5]     [3]
		
		    
		    lets see [5] & [3] - 5 > 3 and all elements 5 can make pair but no element so add 1 to count -- count = 1
		    lets see [3, 5] & [2] - 3 > 2 and all elements 3 can make pair so add 2 to count then j++ is out -- count = 3
		    lets see [4] & [1] - 4 > 1 and all elements 4 can make pair but no element so add 1 to count -- count = 4
		    lets see [2,3,5] & [1,4] - 
		    	2 > 1 so add 3 to count then j++ 
		    	2 < 4 so no add i++
		    	3 < 4 so no add i++
		    	5 > 4 so add 1 to count then j++ ended
		    	
		    	--- count = 8


	 */
	
	//code


	public class Solution {
	    // Global variable to keep track of inversion count
	    static long count;

	    /**
	     * This function merges two sorted halves of the array and counts inversions.
	     * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
	     * Since both halves are already sorted, we can efficiently count such pairs while merging.
	     *
	     * @param arr  The array to be sorted
	     * @param low  The starting index of the first half
	     * @param mid  The ending index of the first half
	     * @param high The ending index of the second half
	     */
	    private static void merge(long[] arr, int low, int mid, int high) {
	        ArrayList<Long> temp = new ArrayList<>(); // Temporary array for merging
	        int left = low;  // Pointer for the left half
	        int right = mid + 1; // Pointer for the right half

	        // Merging both halves while counting inversions
	        while (left <= mid && right <= high) {
	            if (arr[left] <= arr[right]) {
	                // No inversion since arr[left] <= arr[right], so just add arr[left]
	                temp.add(arr[left]);
	                left++;
	            } else {
	                // arr[left] > arr[right] means every element from left to mid forms an inversion with arr[right]
	                count += (mid - left + 1); // Count all elements in left half that are greater than arr[right]
	                temp.add(arr[right]);
	                right++;
	            }
	        }

	        // Copy remaining elements from the left half (if any)
	        while (left <= mid) {
	            temp.add(arr[left]);
	            left++;
	        }

	        // Copy remaining elements from the right half (if any)
	        while (right <= high) {
	            temp.add(arr[right]);
	            right++;
	        }

	        // Copy sorted elements back to the original array
	        for (int i = low; i <= high; i++) {
	            arr[i] = temp.get(i - low);
	        }
	    }

	    /**
	     * Recursive function to apply Merge Sort and count inversions.
	     * It divides the array into halves, sorts them, and merges while counting inversions.
	     *
	     * @param arr  The array to be sorted
	     * @param low  The starting index
	     * @param high The ending index
	     */
	    public static void mergeSort(long[] arr, int low, int high) {
	        if (low >= high) return; // Base case: Single element is already sorted
	        
	        int mid = (low + high) / 2;

	        // Recursively sort the left half
	        mergeSort(arr, low, mid);
	        // Recursively sort the right half
	        mergeSort(arr, mid + 1, high);
	        // Merge both halves and count inversions
	        merge(arr, low, mid, high);
	    }

	    /**
	     * Function to get the total number of inversions in the array.
	     * 
	     * @param arr The input array
	     * @param n   The size of the array
	     * @return The number of inversions in the array
	     */
	    public static long getInversions(long arr[], int n) {
	        count = 0; // Reset count before each function call
	        mergeSort(arr, 0, n - 1); // Apply Merge Sort
	        return count; // Return the total count of inversions
	    }
	}


}
