package com.heap;

import java.util.*;

public class Leetcode347_TopKFrequentElements {
	
	class Solution {

	    // ✅ Pair class to hold frequency and number
	    class Pair {
	        int first;   // frequency
	        int second;  // number

	        public Pair(int first, int second) {
	            this.first = first;
	            this.second = second;
	        }

	        public int getFirst() {
	            return first;
	        }

	        public int getSecond() {
	            return second;
	        }
	    }

	    public int[] topKFrequent(int[] nums, int k) {

	        // ✅ Step 1: Use HashMap to count the frequency of each number
	        HashMap<Integer, Integer> mp = new HashMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
	        }

	        // ✅ Step 2: Use a Min Heap (PriorityQueue) to keep top k frequent elements
	        // Priority Queue will store Pairs of (frequency, number)
	        // Comparator ensures the least frequent element is on top (so we can remove it when size > k)
	        PriorityQueue<Pair> pq = new PriorityQueue<>(
	            (a, b) -> {
	                if (b.getFirst() != a.getFirst()) {
	                    // Compare by frequency (ascending for min heap)
	                    return Integer.compare(a.getFirst(), b.getFirst());
	                } else {
	                    // If frequency is same, compare by number (optional)
	                    return Integer.compare(a.getSecond(), b.getSecond());
	                }
	            });

	        // ✅ Step 3: Iterate map entries and add to heap
	        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
	            pq.add(new Pair(entry.getValue(), entry.getKey()));  // (freq, num)
	            if (pq.size() > k) {
	                pq.remove();  // remove the least frequent one to maintain top k
	            }
	        }

	        // ✅ Step 4: Extract elements from heap to build result
	        int[] res = new int[k];
	        for (int i = 0; i < k; i++) {
	            res[i] = pq.peek().getSecond();  // get number
	            pq.remove();
	        }

	        return res;
	    }
	}

	/*
	-----------------------------------------
	✅ Observations:
	- We need to find the k most frequent elements from the array.
	- Frequencies must be counted, and only top k highest must be returned.

	✅ Intuition:
	- Use a HashMap to store frequencies.
	- Use a min heap of size k to track the top k elements.
	- This avoids sorting the entire frequency list which takes more time.

	✅ Dry Run:
	Input: nums = [1,1,1,2,2,3], k = 2

	Step 1: Frequency Map = {
	  1 -> 3,
	  2 -> 2,
	  3 -> 1
	}

	Step 2: Heap:
	- Add (3,1): pq = [(3,1)]
	- Add (2,2): pq = [(2,2), (3,1)]
	- Add (1,3): pq = [(1,3), (3,1), (2,2)] → remove (1,3) → pq = [(2,2), (3,1)]

	Step 3: Extract elements:
	- peek = (2,2) → res[0] = 2
	- peek = (3,1) → res[1] = 1

	Output: [2,1]

	✅ Time Complexity:
	- Counting frequencies: O(N)
	- Heap operations: O(M log K), where M is number of unique elements
	- Extracting top k: O(K)

	Overall: O(N + M log K)

	✅ Space Complexity:
	- HashMap: O(M)
	- PriorityQueue: O(K)
	- Result array: O(K)
	Total: O(M + K)

	-----------------------------------------
	*/


}
