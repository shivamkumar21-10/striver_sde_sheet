package com.Array1;

public class Leetcode121_BestTimetoBuyandSellStock {
	
	//Brute force - TLE
    public int maxProfit(int[] prices) {
        int ans = 0;

        for(int i=0; i<prices.length-1; i++){
            for(int j=i+1; j<prices.length; j++){
                ans = Math.max(ans,prices[j]-prices[i]);
            }
        }

        return ans;
    }
    
//    Based on the fact that we have to sell after we buy and we are trying to maximize profit, we can iterate through the prices and only need to consider two things:
//    	1.) Is this price cheaper than any other price I've seen before?
//    	2.) If I subtract current price by the cheapest price I've found, does this yield a greater profit than what I've seen so far?
    
    //optimal
    class Solution {
        public int maxProfit(int[] prices) {
            int ans = 0;  // Stores the maximum profit we can make
            int least = Integer.MAX_VALUE;  // Keeps track of the minimum price seen so far

            /*
            Intuition:
            - We need to buy at a minimum price and sell at a maximum price that comes later.
            - Instead of using a nested loop (O(n^2)), we can maintain the lowest price seen so far.
            - For each day, we check the profit if we sell on that day and update the maximum profit.

            Key Observations:
            - If we find a new minimum price, update `least`.
            - If selling at current price gives a better profit than before, update `ans`.
            - This ensures we are always considering the best buying and selling strategy efficiently.
            */

            for(int i = 0; i < prices.length; i++) {
                // Update least price seen so far
                least = Math.min(least, prices[i]);

                // Update maximum profit
                ans = Math.max(ans, prices[i] - least);
            }

            return ans;
        }
    }

    /*
    Dry Run Example:

    Input: prices = [7, 1, 5, 3, 6, 4]
    ---------------------------------------------------
    | Day | Price | Least So Far | Profit | Max Profit |
    ---------------------------------------------------
    |  0  |   7   |      7       |   0    |     0      |
    |  1  |   1   |      1       |   0    |     0      |  (Update least price)
    |  2  |   5   |      1       |   4    |     4      |  (5-1 = 4, Update max profit)
    |  3  |   3   |      1       |   2    |     4      |
    |  4  |   6   |      1       |   5    |     5      |  (6-1 = 5, Update max profit)
    |  5  |   4   |      1       |   3    |     5      |
    ---------------------------------------------------
    Output: 5 (Buy at price 1, Sell at price 6)

    Edge Cases:
    - If prices are in decreasing order (e.g., [5, 4, 3, 2, 1]), max profit remains 0.
    - If prices are constant (e.g., [3, 3, 3, 3]), max profit remains 0.
    - If prices contain only one day, max profit remains 0.

    Time Complexity: **O(n)**
    Space Complexity: **O(1)**
    */


}
