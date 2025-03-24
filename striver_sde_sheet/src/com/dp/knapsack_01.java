package com.dp;
import java.util.ArrayList;
public class knapsack_01 {
	
//********************************************Recursive Approach*************************************************


	public class Solution {
	    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
	        /*
	         * Observations & Intuition:
	         * 1. This is a classic **0/1 Knapsack Problem**, where:
	         *    - We are given `n` items, each with a weight and a value.
	         *    - We have a maximum weight capacity `w`.
	         *    - We need to **maximize the total value** while keeping the total weight ≤ `w`.
	         * 2. **Recursive Approach:**
	         *    - We process items one by one.
	         *    - For each item, we have **two choices**:
	         *      a) **Include the item** (if it fits in remaining weight `w`).
	         *      b) **Exclude the item** and move to the next.
	         *    - We take the maximum value obtained from these two choices.
	         * 3. **Base Case:** 
	         *    - If `n == 0` → No items left to consider, return `0`.
	         *    - If `w == 0` → No remaining capacity, return `0`.
	         */

	        // Base case: If no items left (`n == 0`) or capacity is full (`w == 0`), no profit possible.
	        if (n == 0 || w == 0) return 0;

	        /*
	         * Case 1: The current item's weight is **greater than the remaining weight `w`**.
	         * - We **cannot include** this item, so we simply move to the next item (`n-1`).
	         */
	        if (weights.get(n - 1) > w) {
	            return maxProfit(values, weights, n - 1, w);
	        } 
	        
	        /*
	         * Case 2: The current item's weight is **less than or equal to `w`**.
	         * - We have **two choices**:
	         *   a) Include the current item → Add its value and reduce capacity (`w - weight`).
	         *   b) Exclude the item → Simply move to the next (`n-1`).
	         * - We return the **maximum value** obtained from these choices.
	         */
	        else {
	            return Math.max(
	                values.get(n - 1) + maxProfit(values, weights, n - 1, w - weights.get(n - 1)), // Include current item
	                maxProfit(values, weights, n - 1, w) // Exclude current item
	            );
	        }
	    }
	}

	/*
	Dry Run Example:
	values  = [60, 100, 120]
	weights = [10, 20, 30]
	n = 3, w = 50 (capacity)

	Call: maxProfit(values, weights, 3, 50)

	1. Item 3 (120, weight=30) can be included:
	   - Include: 120 + maxProfit(values, weights, 2, 20)
	   - Exclude: maxProfit(values, weights, 2, 50)

	2. maxProfit(values, weights, 2, 20):
	   - Item 2 (100, weight=20) can be included:
	     - Include: 100 + maxProfit(values, weights, 1, 0) → 100
	     - Exclude: maxProfit(values, weights, 1, 20) → 60
	   - Max = 100

	3. maxProfit(values, weights, 2, 50):
	   - Item 2 (100, weight=20) can be included:
	     - Include: 100 + maxProfit(values, weights, 1, 30) → 100 + 60 = 160
	     - Exclude: maxProfit(values, weights, 1, 50) → 60
	   - Max = 160

	Final Result: max(120 + 100, 160) = 220
	*/

	
	
//*********************************************Top Down**********************************************
	
	import java.util.ArrayList;

	public class Solution {
	    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
	        /*
	         * Observations:
	         * - This is the **0/1 Knapsack Problem**, solved using **Bottom-Up Dynamic Programming (Tabulation).**
	         * - We create a **DP table `t[n+1][w+1]`**, where:
	         *   - `t[i][j]` represents the **maximum profit possible** using first `i` items with capacity `j`.
	         * - If an item's weight is **greater than the remaining capacity `j`**, we **cannot include** it.
	         * - Otherwise, we take the **maximum of**:
	         *   1) **Including** the item (`values[i-1] + t[i-1][j - weights[i-1]]`).
	         *   2) **Excluding** the item (`t[i-1][j]`).
	         * 
	         * Time Complexity: **O(n * w)** - Filling up the DP table requires iterating over `n` items and `w` capacities.
	         * Space Complexity: **O(n * w)** - We use a 2D DP table of size `(n+1) * (w+1)`.
	         */

	        // DP table: `t[i][j]` stores the max profit for `i` items and `j` weight capacity.
	        int[][] t = new int[n + 1][w + 1];

	        // Step 1: Initialize Base Case - When `n == 0` or `w == 0`, profit is `0`
	        for (int i = 0; i <= n; i++) {  // Fix: `i <= n` to include all items
	            for (int j = 0; j <= w; j++) {  // Fix: `j <= w` to include all weights
	                if (i == 0 || j == 0) {  // If no items or zero weight capacity, profit is 0
	                    t[i][j] = 0;
	                }
	            }
	        }

	        // Step 2: Build DP Table using Bottom-Up Approach
	        for (int i = 1; i <= n; i++) { // Iterating over all items
	            for (int j = 1; j <= w; j++) { // Iterating over all capacities from 1 to w
	                if (weights.get(i - 1) > j) {  
	                    // If the weight of the item exceeds capacity `j`, we **cannot include it**.
	                    t[i][j] = t[i - 1][j];  // Take value from the row above
	                } else {
	                    // Otherwise, we **choose the best of two options**:
	                    // 1) Including the current item: values[i-1] + profit from remaining weight (j - weights[i-1])
	                    // 2) Excluding the current item: taking profit from previous row (i-1)
	                    t[i][j] = Math.max(
	                        values.get(i - 1) + t[i - 1][j - weights.get(i - 1)],  // Include the item
	                        t[i - 1][j]  // Exclude the item
	                    );
	                }
	            }
	        }

	        // Step 3: The last cell `t[n][w]` contains the maximum profit possible
	        return t[n][w];
	    }
	}

	/*
	Dry Run Example:
	--------------------
	Input:
	values = [60, 100, 120]
	weights = [10, 20, 30]
	n = 3, w = 50

	Step-by-Step DP Table Filling:

	1. **Initialization:**
	   - `t[0][j] = 0` for all `j` (when there are no items, profit is 0).
	   - `t[i][0] = 0` for all `i` (when the capacity is 0, profit is 0).

	2. **Processing Each Item:**
	   - **Item 1 (Weight = 10, Value = 60)**:
	     - Can be included if `j >= 10`, so `t[1][10] = 60`, `t[1][20] = 60`, ..., `t[1][50] = 60`.
	   - **Item 2 (Weight = 20, Value = 100)**:
	     - If `j < 20`, copy previous row (`t[2][j] = t[1][j]`).
	     - If `j >= 20`, take max(`t[1][j]`, `values[1] + t[1][j-20]`).
	     - So, `t[2][20] = 100`, `t[2][30] = max(60, 100) = 100`, ..., `t[2][50] = max(60, 160) = 160`.
	   - **Item 3 (Weight = 30, Value = 120)**:
	     - If `j < 30`, copy previous row (`t[3][j] = t[2][j]`).
	     - If `j >= 30`, take max(`t[2][j]`, `values[2] + t[2][j-30]`).
	     - `t[3][30] = max(100, 120) = 120`, `t[3][40] = max(160, 120) = 160`, `t[3][50] = max(160, 220) = 220`.

	Final DP Table:
	--------------------
	     0   1   2  ...  10  11  12 ...  20  21  22 ...  30  31 ...  50
	-------------------------------------------------------------------
	0 |  0   0   0  ...   0   0   0 ...   0   0   0 ...   0   0 ...   0
	1 |  0   0   0  ...  60  60  60 ...  60  60  60 ...  60  60 ...  60
	2 |  0   0   0  ...  60  60  60 ... 100 100 100 ... 100 160 ... 160
	3 |  0   0   0  ...  60  60  60 ... 100 100 100 ... 120 160 ... 220

	Final Result: maxProfit(values, weights, 3, 50) → 220
	*/

	
	

}
