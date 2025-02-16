package com_BinarySearch;

public class FindNthRootOfM {
	
	public class Solution {
	    /*
	    * Intuition:
	    * -----------
	    * Given two numbers 'n' and 'm', we need to find the integer 'x' such that x^n = m.
	    * If no such integer exists, return -1.
	    *
	    * Observations:
	    * ------------
	    * 1. The function x^n is **monotonic** for positive values of x. This means:
	    *    - If x increases, x^n also increases.
	    *    - If x decreases, x^n also decreases.
	    *    - There is exactly **one valid answer (if it exists)**.
	    * 2. Because the function is monotonic, **binary search is a great choice** to find 'x' efficiently.
	    *
	    * Why Binary Search?
	    * -----------------
	    * - Instead of checking all values from 1 to m (O(m)), we use **binary search (O(log m))**.
	    * - We check a middle value 'mid', compute `mid^n`, and compare with 'm':
	    *   - If `mid^n == m`, we return 'mid' (found the answer).
	    *   - If `mid^n > m`, we search in the **left half** (values too high).
	    *   - If `mid^n < m`, we search in the **right half** (values too low).
	    */

	    public static int helper(int mid, int n, int m) {
	        /*
	        * This helper function computes mid^n and checks:
	        * 1. If mid^n == m → return 1 (found the answer)
	        * 2. If mid^n > m → return 2 (too large, search left)
	        * 3. If mid^n < m → return 0 (too small, search right)
	        *
	        * Instead of directly computing mid^n (which can overflow),
	        * we use **exponentiation by squaring**.
	        */
	        
	        long base = mid; // Store mid as base
	        long ans = 1;    // Initialize answer to 1
	        
	        while (n > 0) {
	            if (n % 2 == 1) { // If exponent is odd, multiply ans by base
	                ans *= base;
	                
	                if (ans > m) return 2; // Mid^n exceeded m, return 2
	                n--; // Reduce exponent
	            }
	            base *= base; // Square the base for next step
	            if (base > m) return 2; // Base exceeded m, return 2
	            n /= 2; // Halve the exponent
	        }

	        if (ans == m) return 1; // Found exact match
	        return 0; // ans < m, so we need a bigger mid
	    }

	    public static int NthRoot(int n, int m) {
	        /*
	        * Binary Search to find the nth root of m
	        * -------------------------------------------------------
	        * 1. The nth root must be between 1 and m → start = 1, end = m
	        * 2. We repeatedly check the middle element using `helper()`
	        * 3. If helper(mid) == 1 → return mid (nth root found)
	        * 4. If helper(mid) == 2 → mid^n is too large, search left (end = mid - 1)
	        * 5. If helper(mid) == 0 → mid^n is too small, search right (start = mid + 1)
	        */
	        
	        int start = 1;
	        int end = m;

	        while (start <= end) {
	            int mid = start + (end - start) / 2; // Avoid integer overflow
	            int check = helper(mid, n, m);

	            if (check == 1) return mid; // Found nth root
	            if (check == 2) end = mid - 1; // Search left
	            else start = mid + 1; // Search right
	        }

	        return -1; // No integer nth root found
	    }

	    /*
	    * Dry Run: Example - NthRoot(3, 27)
	    * -----------------------------------
	    * Initial range: start = 1, end = 27
	    *
	    * Step 1: mid = (1+27)/2 = 14
	    *         14^3 = 2744 → Too large → Move left (end = 13)
	    * 
	    * Step 2: mid = (1+13)/2 = 7
	    *         7^3 = 343 → Too large → Move left (end = 6)
	    * 
	    * Step 3: mid = (1+6)/2 = 3
	    *         3^3 = 27 → Found! Return 3
	    *
	    * Final Answer: 3 (because 3^3 = 27)
	    *
	    * Time Complexity: O(log m * log n)
	    * - log m for binary search
	    * - log n for exponentiation
	    */
	}


}
