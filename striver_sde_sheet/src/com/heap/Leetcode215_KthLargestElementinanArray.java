package com.heap;

import java.util.PriorityQueue;

public class Leetcode215_KthLargestElementinanArray {
	
	class Solution {
	    /**
	     * Finds the Kth largest element in an unsorted array.
	     * Uses a Min-Heap (PriorityQueue) to efficiently maintain the top K largest elements.
	     * 
	     * Intuition:
	     * Instead of sorting the array (O(N log N)), we use a Min-Heap of size K.
	     * The Min-Heap always contains the largest K elements seen so far.
	     * The root of the heap is the Kth largest element.
	     * 
	     * Logic:
	     * 1. Insert elements into the Min-Heap.
	     * 2. If heap size exceeds K, remove the smallest element.
	     * 3. At the end, the top of the heap is the Kth largest element.
	     * 
	     * Complexity:
	     * - Inserting an element in heap: O(log K)
	     * - Removing an element: O(log K)
	     * - Total complexity: O(N log K)
	     */
	    public int findKthLargest(int[] nums, int k) {
	        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min-Heap to store top K largest elements

	        for(int i=0; i<nums.length; i++){
	            minHeap.add(nums[i]); // Add element to heap
	            if(minHeap.size() > k){ // If heap size exceeds K, remove smallest element
	                minHeap.remove();
	            }
	        }
	        return minHeap.peek(); // The root of the heap is the Kth largest element
	    }
	}

	/**
	 * Dry Run:
	 * Input: nums = [3,2,1,5,6,4], k = 2
	 * Step-by-step Min-Heap operations:
	 * 1. Insert 3 -> Heap: [3]
	 * 2. Insert 2 -> Heap: [2,3]
	 * 3. Insert 1 -> Heap: [1,2,3] (remove 1 since size > k) -> Heap: [2,3]
	 * 4. Insert 5 -> Heap: [2,3,5] (remove 2) -> Heap: [3,5]
	 * 5. Insert 6 -> Heap: [3,5,6] (remove 3) -> Heap: [5,6]
	 * 6. Insert 4 -> Heap: [4,5,6] (remove 4) -> Heap: [5,6]
	 * Output: 5 (the second largest element)
	 */


}
