package com.heap;

import java.util.*;

public class MaximumSumCombinations {
	
	public class Solution {

	    // ✅ Custom class to hold sum and index of B array used
	    class Pair {
	        int sum; // current sum = A[i] + B[j]
	        int idx; // index of B[j] used to form the sum

	        public Pair(int sum, int idx) {
	            this.sum = sum;
	            this.idx = idx;
	        }
	    }

	    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {

	        // ✅ Observation:
	        // We want the top C largest sums of the form A[i] + B[j]
	        // Brute force will take O(N^2), which is not optimal for large N.
	        // Instead, we use a PriorityQueue (max heap) to efficiently get top C sums.

	        // ✅ Intuition:
	        // Since we are looking for maximum sums, sort both arrays in ascending order.
	        // Always start by pairing A[i] with the largest element in B (i.e., B[n-1]).
	        // Push these initial n sums into a max heap (priority queue).
	        // Then, greedily extract the maximum and explore next smaller B[j-1] for same A[i].

	        // ✅ Step 1: Sort both arrays to access maximum elements from the end
	        Collections.sort(A); // ascending order
	        Collections.sort(B); // ascending order

	        ArrayList<Integer> ans = new ArrayList<>();
	        int n = A.size();

	        // ✅ Step 2: Use max heap to store largest sum combinations
	        // We need to define a custom comparator to make it work as max heap based on `sum`
	        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.sum - p1.sum);

	        // ✅ Step 3: Initially pair every element in A with the largest B element (B[n-1])
	        // This ensures we have n maximum possible starting points
	        for (int i = 0; i < n; i++) {
	            pq.add(new Pair(A.get(i) + B.get(n - 1), n - 1));
	        }

	        // ✅ Step 4: Now extract the largest sum and try to get next possible max by reducing B index
	        while (C > 0 && !pq.isEmpty()) {
	            Pair p = pq.poll(); // get the current maximum sum
	            int sum = p.sum;
	            int idx = p.idx;

	            ans.add(sum); // add to result

	            // ✅ Try next smaller B[j-1] for the same A[i] to get next max possible pair
	            if (idx - 1 >= 0) {
	                // Since sum = A[i] + B[idx], and we now want A[i] + B[idx-1],
	                // we compute new sum = sum - B[idx] + B[idx - 1]
	                pq.add(new Pair(sum - B.get(idx) + B.get(idx - 1), idx - 1));
	            }

	            C--; // reduce count of top elements needed
	        }

	        return ans;
	    }
	}

	/*
	---------------------------------------------
	✅ Dry Run:

	Let:
	A = [1, 2, 3]
	B = [4, 5, 6]
	C = 3

	After sorting:
	A = [1, 2, 3]
	B = [4, 5, 6]

	Initial heap insertions:
	(1 + 6) = 7
	(2 + 6) = 8
	(3 + 6) = 9   => Max heap: [9, 8, 7]

	Step 1: poll 9 → ans = [9]
	         insert (3 + 5 = 8) → heap = [8, 7, 8]

	Step 2: poll 8 → ans = [9, 8]
	         insert (2 + 5 = 7) → heap = [8, 7, 7]

	Step 3: poll 8 → ans = [9, 8, 8]
	         insert (1 + 5 = 6) → heap = [7, 7, 6]

	✅ Done for C = 3

	---------------------------------------------
	✅ Time Complexity:
	- Sorting both A and B: O(N log N)
	- Initial heap insertions: O(N log N)
	- C iterations of extracting + inserting to priority queue: O(C log N)

	⏱️ Total Time: O(N log N + C log N)

	✅ Space Complexity:
	- PriorityQueue stores at most N elements → O(N)
	- Result array: O(C)
	- Total: O(N + C)

	---------------------------------------------
	*/


}
