package com.stack;

import java.util.Stack;

public class Leetcode901_OnlineStockSpan {
	
	class StockSpanner {
	    /**
	     * Intuition:
	     * - We need to calculate the **stock span** for each price, which represents the number of consecutive days 
	     *   (including today) for which the stock price is **less than or equal** to the current day's price.
	     * - A **monotonic decreasing stack** (storing {price, index}) is used to efficiently track previous days
	     *   where the stock price was lower.
	     *
	     * Observations:
	     * - If there is no previous higher price, the span will be (index + 1).
	     * - If a previous higher price exists, the span is determined by the difference between the current index
	     *   and the index of the most recent higher price.
	     * - We use a **stack** to store prices along with their indices and remove (pop) smaller/equal prices
	     *   because they don't contribute to future spans.
	     */
	    
	    // Helper class to store price and its corresponding index
	    class Pair {
	        int first;  // Stores the stock price
	        int second; // Stores the index (position in the sequence)

	        public Pair(int f, int s) {
	            this.first = f;
	            this.second = s;
	        }
	    }

	    Stack<Pair> stk = new Stack<>(); // Monotonic decreasing stack to store (price, index) pairs
	    int idx = -1; // Tracks the current index (0-based)
	    
	    public StockSpanner() {
	        idx = -1; // Initialize index
	    }
	    
	    public int next(int price) {
	        idx = idx + 1; // Increment index as a new stock price arrives
	        int ans = 0; // Variable to store the computed stock span

	        // Pop elements from the stack while the top price is less than or equal to the current price
	        while (!stk.isEmpty() && stk.peek().first <= price) {
	            stk.pop();
	        }

	        /**
	         * If the stack is empty, it means there is no previous greater price, so the span is (idx + 1).
	         * Otherwise, span = difference between current index and the index of the most recent higher price.
	         */
	        ans = stk.isEmpty() ? (idx + 1) : (idx - stk.peek().second);
	        
	        // Push the current price and its index onto the stack
	        stk.add(new Pair(price, idx));
	        
	        return ans; // Return the calculated span for this stock price
	    }
	}

	/**
	 * Dry Run Example:
	 * Suppose the sequence of stock prices is: [100, 80, 60, 70, 60, 75, 85]
	 *
	 * Step-by-step execution:
	 * 1. next(100) -> Stack: [(100,0)]  -> Span = 1
	 * 2. next(80)  -> Stack: [(100,0), (80,1)] -> Span = 1
	 * 3. next(60)  -> Stack: [(100,0), (80,1), (60,2)] -> Span = 1
	 * 4. next(70)  -> Pop (60) -> Stack: [(100,0), (80,1), (70,3)] -> Span = 2
	 * 5. next(60)  -> Stack: [(100,0), (80,1), (70,3), (60,4)] -> Span = 1
	 * 6. next(75)  -> Pop (60), Pop (70) -> Stack: [(100,0), (80,1), (75,5)] -> Span = 4
	 * 7. next(85)  -> Pop (75), Pop (80) -> Stack: [(100,0), (85,6)] -> Span = 6
	 *
	 * Final output: [1, 1, 1, 2, 1, 4, 6]
	 *
	 * Complexity Analysis:
	 * - Each element is pushed and popped from the stack at most **once**, making it an **O(N) amortized time** algorithm.
	 */


}
