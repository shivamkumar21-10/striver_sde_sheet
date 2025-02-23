package com.Array4;
import java.util.*;
public class Leetcode15_3Sum {
	
	//Brute force we can use 3 loops and check for all possible value and for storing only unique we can make use of sorting the 
	//answer first then add it in set you will get your triplets TC - O(n^3)
	
	//Better (Hashing)
	// we can omit third loop to bring TC to O(n^2)
	// we can say num[k] = -(num[i] + num[j])
	// we can omit the finding of third element we can make use of HashSet to store the element 
	//and try to find -(nums[i+nums[j] if present in map we got out triplet
	
	//you cannot blindly put the elements in hashset because u cant pick one element twice
	//so u need to put element other than i and j for particular instance
	//what we do we put in array only elements in b/w i and j to the hashset
	

	class Solution {
	    /**
	     * Finds all unique triplets in the array that sum up to zero.
	     * 
	     * @param nums The input array of integers.
	     * @return A list of unique triplets where each triplet sums to zero.
	     */
	    public List<List<Integer>> threeSum(int[] nums) {
	        int n = nums.length;

	        // Using a set to store unique triplets (avoid duplicates automatically).
	        Set<List<Integer>> ansSet = new HashSet<>();

	        // 🔍 **Brute Force with HashSet Optimization**
	        // ----------------------------------------------------
	        // - Instead of checking all triplets (O(n³) brute force), we optimize using a HashSet.
	        // - For each pair (nums[i], nums[j]), we check if the third required number exists.
	        // - The third number needed to make the sum zero is `-(nums[i] + nums[j])`.
	        // - We use a HashSet to store numbers seen so far in this iteration.

	        for (int i = 0; i < n; i++) {
	            HashSet<Integer> st = new HashSet<>();

	            // Fix `nums[i]` and find `nums[j]` and `third` such that `nums[i] + nums[j] + third = 0`
	            for (int j = i + 1; j < n; j++) {
	                int third = -(nums[i] + nums[j]); // Compute the required third number

	                // If `third` exists in the HashSet, it means we found a valid triplet
	                if (st.contains(third)) {
	                    List<Integer> temp = new ArrayList<>();
	                    temp.add(nums[i]);
	                    temp.add(nums[j]);
	                    temp.add(third);
	                    Collections.sort(temp); // Sort to maintain uniqueness
	                    ansSet.add(temp); // Add to set to avoid duplicate triplets
	                }

	                // Add current `nums[j]` to HashSet to check for future pairs
	                st.add(nums[j]);
	            }
	        }

	        // Convert HashSet to List (final output format)
	        List<List<Integer>> ans = new ArrayList<>(ansSet);
	        return ans;
	    }
	}

	/**
	 * 🔍 **Intuition & Why We Use HashSet Instead of Brute Force**
	 * ------------------------------------------------------------
	 * - **Brute force (O(n³))** checks every combination of three elements, which is slow.
	 * - Instead, we use **HashSet to check for the third required number in O(1)**.
	 * - **Sorting each triplet ensures uniqueness**, and the Set removes duplicates.
	 *
	 * **🔍 Dry Run Example**
	 * ----------------------
	 * **Input:** `nums = [-1, 0, 1, 2, -1, -4]`
	 *
	 * **Step 1: Process `nums[i] = -1`**
	 * - `st = {}`
	 * - `j=1 (nums[j] = 0) → third = 1 (not in set)`
	 * - `j=2 (nums[j] = 1) → third = -1 (found in set!) → triplet: `[-1, 0, 1]`
	 *
	 * **Step 2: Process `nums[i] = -1` again** (next occurrence)
	 * - Avoids duplicate as `Set` stores unique triplets.
	 *
	 * **Final Unique Triplets:**
	 * ```
	 * [[-1, -1, 2], [-1, 0, 1]]
	 * ```
	 *
	 * **⏳ Complexity Analysis**
	 * -------------------------
	 * - **O(n²)** because for each `nums[i]`, we traverse the rest using `nums[j]` and HashSet.
	 * - **O(n log n)** due to sorting each triplet.
	 * - **Overall: O(n² log n), better than O(n³).**
	 */


	//Optimal we can think of get rid of set
	//it will be two pointer with fixed i and 2 pointer approach in j & k
	// with one optimisation for repeating character in array 
	// when we got triplet we will not check again if next element for i,j,k are same. same given in code
	//if we dont do this we end up havinf duplicate triplet in my answer list
	

	class Solution2 {
	    /**
	     * Finds all unique triplets in the array that sum up to zero.
	     * 
	     * @param nums The input array of integers.
	     * @return A list of unique triplets where each triplet sums to zero.
	     */
	    public List<List<Integer>> threeSum(int[] nums) {
	        int n = nums.length;

	        // ✅ **Step 1: Sort the Array**
	        // Sorting helps in efficiently finding unique triplets using two pointers.
	        Arrays.sort(nums);

	        // List to store the resulting triplets.
	        List<List<Integer>> ans = new ArrayList<>();

	        // ✅ **Step 2: Iterate through each number as the first element of the triplet**
	        for (int i = 0; i < n - 2; i++) {

	            // **Avoid duplicate triplets:** If `nums[i]` is the same as `nums[i-1]`, skip this iteration.
	            if (i > 0 && nums[i] == nums[i - 1]) continue;

	            int j = i + 1;  // Left pointer
	            int k = n - 1;  // Right pointer

	            // ❌ **Bug Fix:** The while loop here is unnecessary and will cause errors.
	            // Removed: `while (i < n - 1 && nums[i] == nums[i + 1]) i++;`
	            // Reason: The `if` condition above already prevents duplicates, so this is redundant.

	            // ✅ **Step 3: Use Two-Pointer Technique to Find the Triplet**
	            while (j < k) {
	                int sum = nums[i] + nums[j] + nums[k];

	                // 🟢 **Case 1: Found a valid triplet**
	                if (sum == 0) {
	                    // Create the triplet and add to the answer list
	                    List<Integer> temp = new ArrayList<>();
	                    temp.add(nums[i]);
	                    temp.add(nums[j]);
	                    temp.add(nums[k]);
	                    ans.add(temp);

	                    // Move both pointers to find new pairs
	                    j++;
	                    k--;

	                    // **Avoid duplicate `nums[j]` values**
	                    while (j < k && nums[j] == nums[j - 1]) j++;

	                    // **Avoid duplicate `nums[k]` values**
	                    while (k > j && nums[k] == nums[k + 1]) k--;
	                }
	                // 🔼 **Case 2: Sum is too large → Decrease `k` (move right pointer left)**
	                else if (sum > 0) {
	                    k--;
	                }
	                // 🔽 **Case 3: Sum is too small → Increase `j` (move left pointer right)**
	                else {
	                    j++;
	                }
	            }
	        }

	        return ans;
	    }
	}

	/**
	 * 🔍 **Intuition & Why We Use Sorting + Two Pointers**
	 * ----------------------------------------------------
	 * - **Sorting helps detect duplicates easily** and allows a **two-pointer** approach.
	 * - Instead of **brute force O(n³) checking all triplets**, we use sorting + two pointers.
	 * - **Two-pointer approach:** Fix `nums[i]`, then use two pointers (`j` and `k`) to find `nums[j] + nums[k] == -nums[i]`.
	 * - If the sum is too small, move `j` right; if too large, move `k` left.
	 *
	 * **🔍 Dry Run Example**
	 * ----------------------
	 * **Input:** `nums = [-1, 0, 1, 2, -1, -4]`
	 *
	 * **Step 1:** Sorting → `[-4, -1, -1, 0, 1, 2]`
	 *
	 * **Step 2:** Fix `nums[i] = -4`
	 * - `j = -1, k = 2`
	 * - Sum `(-4 + -1 + 2) = -3`, too small → move `j` right.
	 *
	 * **Step 3:** Fix `nums[i] = -1`
	 * - Found triplet `[-1, -1, 2]`
	 * - Found triplet `[-1, 0, 1]`
	 *
	 * **Final Unique Triplets:**
	 * ```
	 * [[-1, -1, 2], [-1, 0, 1]]
	 * ```
	 *
	 * **⏳ Complexity Analysis**
	 * -------------------------
	 * - **Sorting takes O(n log n)**
	 * - **Each element is processed once with two-pointer technique (O(n))**
	 * - **Overall: O(n²), much better than O(n³) brute force.**
	 */

}
