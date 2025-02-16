package com.Array4;
import java.util.*;

public class Leetcode128_LongestConsecutiveSequence {
	
	//Approach1 - not optimal due to sorting

	class Solution {
	    /**
	     * Finds the length of the longest consecutive sequence in an unsorted array.
	     * 
	     * @param nums The input array of integers
	     * @return Length of the longest consecutive sequence
	     */
	    public int longestConsecutive(int[] nums) {
	        int n = nums.length;

	        // Edge case: If the array is empty, return 0.
	        if (n == 0) return 0;

	        // Step 1: Sort the array to bring consecutive elements together.
	        Arrays.sort(nums);

	        int longest = 1; // Stores the maximum length of a consecutive sequence
	        int lastSmallest = Integer.MIN_VALUE; // Stores the last unique consecutive number
	        int cnt = 0; // Stores the current sequence length

	        // Step 2: Iterate through the sorted array and count consecutive numbers.
	        for (int i = 0; i < n; i++) {
	            if (nums[i] - 1 == lastSmallest) { // Case: Current number extends the sequence
	                cnt++; // Increase sequence length
	                lastSmallest = nums[i]; // Update the last seen consecutive number
	                longest = Math.max(longest, cnt); // Update the longest sequence found
	            } else if (lastSmallest != nums[i]) { // Case: Reset counter for a new sequence
	                cnt = 1; // Start new sequence
	                lastSmallest = nums[i]; // Update last seen number
	            }
	        }

	        return longest; // Return the longest sequence length found
	    }
	}

	/**
	 * 🔍 **Intuition & Observations**
	 * --------------------------------
	 * 1️⃣ **Brute Force Approach (O(n²))**  
	 * - Check each number and count consecutive elements manually.
	 * - This leads to **Time Limit Exceeded (TLE)** for large inputs.
	 * 
	 * 2️⃣ **Optimized Approach - Sorting (O(n log n))**  
	 * - **Sorting brings consecutive numbers together**, simplifying the problem.
	 * - **Single pass through the array** allows counting consecutive sequences efficiently.
	 * - **Duplicates are ignored** by resetting the counter when a gap is found.
	 * 
	 * 🔍 **Dry Run Example**
	 * --------------------------------
	 * **Input:** `[100, 4, 200, 1, 3, 2]`
	 * 
	 * **Step 1: Sorting**
	 * ```
	 * Sorted: [1, 2, 3, 4, 100, 200]
	 * ```
	 * 
	 * **Step 2: Counting Consecutive Numbers**
	 * ```
	 * i = 0 → 1 (Start) → cnt = 1, longest = 1
	 * i = 1 → 2 (1+1) → cnt = 2, longest = 2
	 * i = 2 → 3 (2+1) → cnt = 3, longest = 3
	 * i = 3 → 4 (3+1) → cnt = 4, longest = 4
	 * i = 4 → 100 (Not consecutive) → Reset cnt = 1
	 * i = 5 → 200 (Not consecutive) → Reset cnt = 1
	 * ```
	 * 
	 * **Final Result:** `4` (Longest consecutive sequence is `[1, 2, 3, 4]`)
	 * 
	 * 📌 **Key Takeaways**
	 * - **Sorting helps in efficiently counting consecutive numbers.**
	 * - **Iterate once through the sorted array and maintain a counter.**
	 * - **Duplicates are handled by skipping repeated values.**
	 * - **Final complexity:** `O(n log n)` due to sorting, followed by `O(n)`.
	 */


	//Optimal
	class Solution2 {
	    /**
	     * Finds the length of the longest consecutive sequence in an unsorted array.
	     * 
	     * @param nums The input array of integers
	     * @return Length of the longest consecutive sequence
	     */
	    public int longestConsecutive(int[] nums) {
	        int n = nums.length;

	        // Edge case: If the array is empty, return 0.
	        if (n == 0) return 0;

	        // Step 1: Use a HashSet to store all unique elements for O(1) lookups.
	        Set<Integer> s = new HashSet<>();

	        // Add all elements to the HashSet
	        for (int i = 0; i < n; i++) {
	            s.add(nums[i]);
	        }

	        int longest = 1; // Stores the maximum length of a consecutive sequence
	        int cnt = 0; // Stores the current sequence length

	        // Step 2: Iterate through the set and check for the start of sequences.
	        for (int num : s) {
	            // Check if the current number is the start of a sequence (i.e., num-1 is not in the set)
	            if (!s.contains(num - 1)) { 
	                cnt = 1; // Start a new sequence
	                int x = num; // Track current sequence element
	                
	                // Step 3: Count the length of the sequence by checking if the next consecutive number exists
	                while (s.contains(x + 1)) {
	                    x = x + 1;
	                    cnt++; // Increase sequence length
	                }

	                // Update the longest sequence found
	                longest = Math.max(longest, cnt);
	            }
	        }

	        return longest; // Return the longest sequence length found
	    }
	}

	/**
	 * 🔍 **Intuition & Why We Use a HashSet**
	 * ----------------------------------------
	 * - **Sorting approach (O(n log n)) works but isn't optimal**.
	 * - Instead of sorting, we use a **HashSet for O(1) lookups** to efficiently check for consecutive numbers.
	 * - The key **observation** is:
	 *     - A sequence always starts from a number **which doesn’t have a smaller number in the set**.
	 *     - If `num-1` is **not** in the set, `num` is a **starting point** of a sequence.
	 *     - We then count consecutive numbers by checking `num+1`, `num+2`, and so on.
	 *
	 * **🔍 Dry Run Example**
	 * ------------------------
	 * **Input:** `[100, 4, 200, 1, 3, 2]`
	 * 
	 * **Step 1: Store all elements in a HashSet**
	 * ```
	 * HashSet: {100, 4, 200, 1, 3, 2}
	 * ```
	 * 
	 * **Step 2: Identify sequence start points**
	 * - `100` → No `99`, so start a sequence but no `101` → Length = 1
	 * - `4` → No `3`, so **not a start**.
	 * - `200` → No `199`, so start a sequence but no `201` → Length = 1
	 * - `1` → No `0`, so start a sequence → Count: `2 → 3 → 4` → Length = **4** ✅
	 *
	 * **Final Result:** `4` (Longest consecutive sequence is `[1, 2, 3, 4]`)
	 *
	 * **🔹 Why HashSet?**
	 * - **O(1) lookups** instead of O(n) in an unsorted array.
	 * - **Avoids duplicates** automatically.
	 * - **Efficient iteration** (each number is checked at most once).
	 * 
	 * **Final Complexity:**  
	 * - **O(n)** average case (since each element is checked only once).
	 * - **O(n log n)** worst case (if HashSet operations degrade due to poor hash function distribution).
	 */


}
