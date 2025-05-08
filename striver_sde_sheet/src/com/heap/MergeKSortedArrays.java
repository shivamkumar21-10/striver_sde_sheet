package com.heap;
import java.util.*;
public class MergeKSortedArrays {
	


	static class Pair {
	    int value, arrayIndex, elementIndex;

	    /**
	     * Constructor to store:
	     * - `value` -> Current element value from the array.
	     * - `arrayIndex` -> Which array this element belongs to.
	     * - `elementIndex` -> Index of the element within its respective array.
	     */
	    public Pair(int value, int arrayIndex, int elementIndex) {
	        this.value = value;
	        this.arrayIndex = arrayIndex;
	        this.elementIndex = elementIndex;
	    }
	}

	    /**
	     * Function to merge K sorted arrays into a single sorted list.
	     *
	     * Intuition:
	     * - Since each array is sorted, the **smallest element** among all arrays should be 
	     *   picked first, followed by the next smallest.
	     * - A **Min Heap (PriorityQueue)** helps efficiently track the smallest element.
	     * - After picking the smallest, we **insert the next element** from the same array 
	     *   into the heap.
	     * - Continue this process until all elements are merged.
	     * 
	     * Logic:
	     * 1. **Push the first element** of each array into a Min Heap (PriorityQueue).
	     * 2. **Extract the smallest element** (top of the heap) and add it to the result.
	     * 3. **Insert the next element** from the same array into the heap.
	     * 4. Repeat steps **2 & 3** until the heap is empty.
	     *
	     * Key Observations:
	     * - **Min Heap** ensures we always pick the smallest element efficiently.
	     * - **Time Complexity**:  
	     *   - **Heap operations** take \( O(\log K) \) for each insertion/deletion.
	     *   - **Total elements to process** = \( N \) (total number of elements across all arrays).
	     *   - **Final Complexity** = \( O(N \log K) \).
	     * - **Space Complexity**: \( O(K) \) due to the heap storing elements from at most **K** arrays at a time.
	     *
	     * @param lists - List of sorted lists to be merged
	     * @return Merged sorted list
	     */
	    public static List<Integer> mergeKSortedArrays(List<List<Integer>> lists) {
	        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
	        List<Integer> result = new ArrayList<>();

	        // Step 1: Add the first element of each array to the heap
	        for (int i = 0; i < lists.size(); i++) {
	            if (!lists.get(i).isEmpty()) {
	                minHeap.add(new Pair(lists.get(i).get(0), i, 0)); // (value, arrayIndex, elementIndex)
	            }
	        }

	        // Step 2: Process the heap and build the sorted output
	        while (!minHeap.isEmpty()) {
	            Pair curr = minHeap.poll(); // Extract the smallest element
	            result.add(curr.value); // Add to result list

	            int nextIndex = curr.elementIndex + 1; // Get next element index in the same array
	            if (nextIndex < lists.get(curr.arrayIndex).size()) {
	                // Push the next element from the same array into the heap
	                minHeap.add(new Pair(lists.get(curr.arrayIndex).get(nextIndex), curr.arrayIndex, nextIndex));
	            }
	        }

	        return result; // Return the fully merged sorted list
	    }

	    public static void main(String[] args) {
	        List<List<Integer>> lists = new ArrayList<>();
	        lists.add(Arrays.asList(1, 4, 7));
	        lists.add(Arrays.asList(2, 5, 8));
	        lists.add(Arrays.asList(3, 6, 9));

	        System.out.println("Merged Sorted List: " + mergeKSortedArrays(lists));  
	        // Expected Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
	    }
	}

	/**
	 * Dry Run:
	 * Given:
	 * lists = [
	 *   [1, 4, 7],
	 *   [2, 5, 8],
	 *   [3, 6, 9]
	 * ]
	 * 
	 * Step 1: Push first elements to heap -> MinHeap = [1, 2, 3]
	 * Step 2: Extract 1 -> Add 4 from same array -> Heap = [2, 3, 4]
	 * Step 3: Extract 2 -> Add 5 from same array -> Heap = [3, 4, 5]
	 * Step 4: Extract 3 -> Add 6 from same array -> Heap = [4, 5, 6]
	 * Step 5: Extract 4 -> Add 7 from same array -> Heap = [5, 6, 7]
	 * Step 6: Extract 5 -> Add 8 from same array -> Heap = [6, 7, 8]
	 * Step 7: Extract 6 -> Add 9 from same array -> Heap = [7, 8, 9]
	 * Step 8: Extract 7 -> Heap = [8, 9]
	 * Step 9: Extract 8 -> Heap = [9]
	 * Step 10: Extract 9 -> Heap = []
	 * 
	 * Final Merged List: [1, 2, 3, 4, 5, 6, 7, 8, 9]
	 */

