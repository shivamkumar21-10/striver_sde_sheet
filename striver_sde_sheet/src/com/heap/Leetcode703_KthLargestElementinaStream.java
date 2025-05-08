package com.heap;

import java.util.PriorityQueue;

public class Leetcode703_KthLargestElementinaStream {
	
	class KthLargest {

	    int k; // To store the value of 'k' (i.e., we want the kth largest element)
	    PriorityQueue<Integer> pq; // Min-heap to maintain the top k largest elements

	    public KthLargest(int k, int[] nums) {
	        this.k = k; 
	        pq = new PriorityQueue<>(k); // Initialize a min-heap of size k

	        // Add all elements from nums[] to the heap
	        for (int num : nums) {
	            if (pq.size() < k) {
	                // If the heap is not full, add the element
	                pq.offer(num);
	            } 
	            else if (num > pq.peek()) {
	                // If the current element is greater than the smallest in heap (i.e., pq.peek()),
	                // then this element could be among the k largest
	                pq.offer(num); // Add the element
	                if (pq.size() > k) {
	                    pq.poll(); // Maintain only k elements by removing the smallest
	                }
	            }
	            // If the element is smaller than the smallest of the k largest, we ignore it
	        }
	    }

	    public int add(int val) {
	        // Add new value in stream
	        if (pq.size() < k) {
	            // If heap has less than k elements, just add
	            pq.offer(val);
	        } 
	        else if (val > pq.peek()) {
	            // If the new value is larger than the smallest among current k largest
	            pq.offer(val);
	            pq.poll(); // Remove smallest to keep only top k elements
	        }

	        // Top of the heap is the kth largest
	        return pq.peek();
	    }
	}

	/**
	 * Dry Run:
	 * Suppose k = 3 and nums = [4, 5, 8, 2]
	 * After constructor, pq = [5, 8, 4] -> sorted: [4, 5, 8] (min heap keeps 4 at top)
	 * add(3) => 3 < 4 → ignored → pq still [4, 5, 8] → returns 4
	 * add(10) => 10 > 4 → add 10, remove 4 → pq becomes [5, 8, 10] → returns 5
	 * 
	 * Intuition:
	 * - Keep only the k largest elements in a min-heap.
	 * - The top of the heap is always the kth largest element.
	 */


}
