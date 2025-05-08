package com.stack;

public class TheCelebrityProblem {
	
	//***************************Brute******************************
	class Solution {
	    int findCelebrity(int n, int[][] MATRIX) {
	        int[] whoKnowsMe = new int[n];  // Tracks how many know this person
	        int[] iKnowWhom = new int[n];   // Tracks how many this person knows

	        // Step 1: Fill tracking arrays
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (MATRIX[i][j] == 1) { 
	                    // i knows j
	                    iKnowWhom[i]++;  
	                    whoKnowsMe[j]++;
	                }
	            }
	        }

	        // Step 2: Check for a celebrity
	        for (int i = 0; i < n; i++) {
	            if (whoKnowsMe[i] == n - 1 && iKnowWhom[i] == 0) {
	                return i;  // Found the celebrity
	            }
	        }

	        return -1;  // No celebrity found
	    }
	}
//	Complexity Analysis:
//		TC - O(N*N) + O(N)
//		SC - O(2N)
	
	//**********************OPTIMAL*********************
	class Solution2 {
	    /**
	     * Function to find the celebrity using the two-pointer approach.
	     * 
	     * Intuition:
	     * - A celebrity is someone who is known by everyone but knows no one.
	     * - Instead of checking all pairs (which is O(N^2)), we can use two pointers to find a potential celebrity.
	     * - We start with two pointers, one at the top (0) and one at the bottom (N-1), and eliminate non-celebrities.
	     * 
	     * Observations:
	     * - If A knows B, then A **cannot** be a celebrity (since a celebrity knows no one).
	     * - If A **does not** know B, then B **cannot** be a celebrity (since a celebrity must be known by everyone).
	     * - Using this, we narrow down to **one candidate** in O(N) time.
	     * - Finally, we verify if the candidate is truly a celebrity in another O(N) check.
	     * 
	     * Logic:
	     * 1. Use two pointers (`top` and `down`) to scan from **top (0)** and **bottom (N-1)**.
	     * 2. If `top` knows `down`, move `top` **downward** (since `top` cannot be a celebrity).
	     * 3. Else, move `down` **upward** (since `down` cannot be a celebrity).
	     * 4. We will be left with **one potential candidate**.
	     * 5. Validate the candidate by checking:
	     *    - The candidate must **not know** anyone.
	     *    - The candidate must be **known by everyone else**.
	     * 
	     * Dry Run:
	     * MATRIX = 
	     *  0  1  2  3
	     * 0[0, 1, 1, 1]   // Person 0 knows 1, 2, 3
	     * 1[0, 0, 0, 1]   // Person 1 knows 3
	     * 2[0, 1, 0, 1]   // Person 2 knows 1, 3
	     * 3[0, 0, 0, 0]   // Person 3 knows no one (Potential celebrity)
	     * 
	     * Step 1: top = 0, down = 3
	     * - top (0) knows down (3) → move top to 1
	     * - top (1) knows down (3) → move top to 2
	     * - top (2) knows down (3) → move top to 3
	     * - Now `top == down`, candidate is 3.
	     * 
	     * Step 2: Verify candidate 3:
	     * - Row check: 3 knows no one ✅
	     * - Column check: Everyone knows 3 ✅
	     * - Return 3 as the celebrity.
	     */
	    
	    public int findCelebrity(int n, int[][] MATRIX) {
	        // Step 1: Use two pointers to find the potential celebrity
	        int top = 0;         // Start from the top (0)
	        int down = n - 1;    // Start from the bottom (n-1)

	        while (top < down) {
	            if (MATRIX[top][down] == 1) { 
	                // top knows down → top cannot be a celebrity
	                top++;  
	            } else if (MATRIX[down][top] == 1) { 
	                // down knows top → down cannot be a celebrity
	                down--;  
	            } else { 
	            	//both knows each other
	            	top++;
	            	down--;
	            }
	        }

	        // Step 2: Verify the potential celebrity
	        int candidate = top; // Candidate left after pointer approach

	        // Check if candidate knows no one (row check)
	        for (int j = 0; j < n; j++) {
	            if (MATRIX[candidate][j] == 1) return -1; // If candidate knows someone, return -1
	        }

	        // Check if everyone knows the candidate (column check)
	        for (int i = 0; i < n; i++) {
	            if (i != candidate && MATRIX[i][candidate] == 0) return -1; // If someone doesn't know candidate, return -1
	        }

	        return candidate; // Candidate is the celebrity
	    }
	    
//	    Time & Space Complexity:
//	    	Time Complexity: O(N)
//	    	Finding the candidate takes O(N) using two pointers.
//	    	Verification (row + column check) takes O(N).
//	    	Total: O(N + N) = O(N) (Optimal).
//	    	Space Complexity: O(1)
//	    	No extra space used apart from variables.
	}


}
