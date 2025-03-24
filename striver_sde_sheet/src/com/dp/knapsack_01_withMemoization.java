package com.dp;

import java.util.Arrays;

public class knapsack_01_withMemoization {
    // Define a memoization table to store intermediate results.
    // t[n][W] will represent the maximum value that can be achieved 
    // using the first 'n' items with a knapsack capacity of 'W'.
    static int[][] t = new int[102][1002];

    /**
     * Function to solve the 0/1 Knapsack problem using memoization.
     * 
     * @param wt   Array representing weights of items.
     * @param val  Array representing values (profits) of items.
     * @param W    Maximum capacity of the knapsack.
     * @param n    Number of items remaining to consider.
     * @return     Maximum value that can be achieved with the given constraints.
     */
    static int knapsack(int[] wt, int[] val, int W, int n) {

        // Base condition:
        // If no items are left to consider (n == 0) or the capacity of the knapsack is 0 (W == 0),
        // the maximum value is 0 because no items can be selected.
        if (n == 0 || W == 0) {
            return 0;
        }

        // Check if the result for the current state (n, W) is already computed.
        // If it is not -1, return the stored value to avoid recomputation.
        if (t[n][W] != -1) {
            return t[n][W];
        }

        /**
         * Recursive logic:
         * If the weight of the current item is less than or equal to the remaining knapsack capacity,
         * we have two choices:
         * 1. Include the item in the knapsack:
         *    - Add the value of the item (val[n-1]).
         *    - Subtract the weight of the item (wt[n-1]) from the remaining capacity.
         *    - Reduce the number of items to consider (n-1).
         * 2. Exclude the item from the knapsack:
         *    - Solve the problem with the same capacity and remaining items (n-1).
         * The maximum of these two choices gives the optimal solution for this state.
         */
        if (wt[n - 1] <= W) {
            // Memoize the result for the current state (n, W).
            return t[n][W] = Math.max(
                val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1), // Include the item.
                knapsack(wt, val, W, n - 1)                          // Exclude the item.
            );
        }

        /**
         * If the weight of the current item exceeds the remaining knapsack capacity,
         * the item cannot be included in the solution. In this case:
         * - Skip the item and solve the problem for the remaining items (n-1).
         */
        else {
            return t[n][W] = knapsack(wt, val, W, n - 1);
        }
    }

    public static void main(String[] args) {
        // Define the values (profits) of the items.
        int profit[] = new int[] { 60, 100, 120 };
        // Define the weights of the items.
        int weight[] = new int[] { 10, 20, 30 };
        // Define the maximum capacity of the knapsack.
        int W = 50;
        // Get the number of items available.
        int n = profit.length;

        // Initialize the memoization table with -1 to indicate uncomputed states.
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        /**
         * Call the knapsack function with the defined inputs and print the result:
         * - This will output the maximum profit that can be achieved by optimally 
         *   selecting items to include in the knapsack.
         */
        System.out.println("Maximum profit: " + knapsack(weight, profit, W, n));
    }
}
