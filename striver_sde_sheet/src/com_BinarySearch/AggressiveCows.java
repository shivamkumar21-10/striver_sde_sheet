package com_BinarySearch;
import java.util.Arrays;
public class AggressiveCows {
	
	

	class Solution {
	    /*
	    * Intuition:
	    * -----------
	    * - We need to place 'k' cows in 'n' stalls such that the minimum distance between
	    *   any two cows is maximized.
	    * - This is a classic **binary search on answer** problem.
	    * - If we fix a minimum distance 'd', we can check if it is possible to place all cows.
	    * - The function is **monotonic**:
	    *   - If we can place cows at distance 'd', we can also place them at any smaller 'd'.
	    *   - If we can't place them at 'd', we can't place them at any larger 'd' either.
	    * - This allows us to use **binary search** on the distance.
	    */

	    boolean isPossible(int[] nums, int k, int mid) {
	        /*
	        * This function checks if we can place 'k' cows with at least 'mid' distance.
	        *
	        * Algorithm:
	        * ----------
	        * 1. Place the first cow in the first stall.
	        * 2. Iterate through the stalls:
	        *    - If the gap between the current stall and the last placed cow >= 'mid',
	        *      place a cow in this stall.
	        *    - Keep track of the number of cows placed.
	        * 3. If we placed at least 'k' cows, return true (it's possible).
	        */
	        
	        int nc = 1; // Number of cows placed (first cow placed in the first stall)
	        int last = nums[0]; // Position of last placed cow

	        for (int i = 1; i < nums.length; i++) {
	            if (nums[i] - last >= mid) { // If the gap is at least 'mid'
	                nc++; // Place a cow
	                last = nums[i]; // Update last placed cow position
	            }
	        }
	        
	        return nc >= k; // If we placed all k cows, return true
	    }

	    public int aggressiveCows(int[] nums, int k) {
	        /*
	        * Binary Search to find the maximum minimum distance.
	        * ---------------------------------------------------
	        * 1. Sort the stall positions → Essential for binary search.
	        * 2. Define the search space:
	        *    - Minimum possible distance = 1 (smallest possible separation).
	        *    - Maximum possible distance = (max - min) → placing cows at extreme ends.
	        * 3. Perform binary search on 'mid' (the minimum distance between cows).
	        * 4. If 'mid' is possible → try a larger distance (start = mid + 1).
	        * 5. If 'mid' is not possible → try a smaller distance (end = mid - 1).
	        * 6. Return 'end' which stores the largest possible minimum distance.
	        */
	        
	        int n = nums.length;
	        Arrays.sort(nums); // Sort stalls in increasing order
	        
	        int start = 1; // Smallest possible minimum distance
	        int end = nums[n - 1] - nums[0]; // Largest possible minimum distance
	        int ans = -1; // Store the best possible answer

	        while (start <= end) {
	            int mid = (start + end) / 2; // Middle value (potential minimum distance)

	            if (isPossible(nums, k, mid)) {
	                ans = mid; // Store the valid distance
	                start = mid + 1; // Try for a larger minimum distance
	            } else {
	                end = mid - 1; // Try for a smaller minimum distance
	            }
	        }
	        
	        return ans; // Return the largest valid minimum distance
	    }

	    /*
	    * Dry Run: Example - aggressiveCows([1, 2, 4, 8, 9], 3)
	    * -----------------------------------------------------
	    * Stalls: [1, 2, 4, 8, 9], Cows: 3
	    * 
	    * Step 1: Binary Search Range → start = 1, end = (9 - 1) = 8
	    * 
	    * Step 2: mid = (1 + 8) / 2 = 4
	    *   - Can we place cows with at least 4 distance?
	    *   - Place 1st cow at 1
	    *   - Place 2nd cow at 4 (gap = 3, less than 4) → No
	    *   - Place 2nd cow at 8 (gap = 7) → Yes
	    *   - Place 3rd cow at 9 (gap = 1, less than 4) → No
	    *   - Can't place all cows → end = mid - 1 = 3
	    * 
	    * Step 3: mid = (1 + 3) / 2 = 2
	    *   - Can we place cows with at least 2 distance?
	    *   - Place 1st cow at 1
	    *   - Place 2nd cow at 4 (gap = 3) → Yes
	    *   - Place 3rd cow at 8 (gap = 4) → Yes
	    *   - All cows placed → start = mid + 1 = 3, ans = 2
	    * 
	    * Step 4: mid = (3 + 3) / 2 = 3
	    *   - Can we place cows with at least 3 distance?
	    *   - Place 1st cow at 1
	    *   - Place 2nd cow at 4 (gap = 3) → Yes
	    *   - Place 3rd cow at 8 (gap = 4) → Yes
	    *   - All cows placed → start = mid + 1 = 4, ans = 3
	    *
	    * Final Answer: 3 (Largest valid minimum distance)
	    *
	    * Time Complexity: O(n log d)
	    * - O(n) for checking placement (isPossible)
	    * - O(log d) for binary search (d = max - min stall)
	    */
	}


}
