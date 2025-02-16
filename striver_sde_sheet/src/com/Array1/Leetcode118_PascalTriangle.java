package com.Array1;
import java.util.*;

public class Leetcode118_PascalTriangle {
	
	class Solution {

	    /**
	     * This method generates the nth row of Pascal's Triangle.
	     * 
	     * Intuition:
	     * - Pascal's Triangle is built using combinatorial mathematics.
	     * - Each row follows the binomial coefficient formula: C(n, r) = n! / (r! * (n-r)!)
	     * - We can generate each row dynamically using a mathematical pattern:
	     *   - The first element of each row is always `1`.
	     *   - Each subsequent element is computed as:
	     *     - ans = ans * (n - c) / c
	     *     - This avoids expensive factorial calculations.
	     * 
	     * Example:
	     * - n = 5 (5th row of Pascal's Triangle)
	     *   Row: [1, 4, 6, 4, 1]
	     *   Calculation:
	     *   - 1st element: 1
	     *   - 2nd element: (1 * (5-1) / 1) = 4
	     *   - 3rd element: (4 * (5-2) / 2) = 6
	     *   - 4th element: (6 * (5-3) / 3) = 4
	     *   - 5th element: (4 * (5-4) / 4) = 1
	     *
	     * @param n The row index in Pascal's Triangle (1-based index)
	     * @return A list representing the nth row
	     */
	    List<Integer> generatedRow(int n) {
	        int ans = 1;  // First element is always 1
	        List<Integer> row = new ArrayList<>();
	        row.add(ans);

	        for (int c = 1; c < n; c++) {
	            ans = ans * (n - c);  // Multiply by (n-c)
	            ans = ans / c;        // Divide by c (to get the next coefficient)
	            row.add(ans);
	        }
	        return row;
	    }

	    /**
	     * This method generates Pascal's Triangle up to `numRows`.
	     * 
	     * Intuition:
	     * - Pascal's Triangle is built row by row.
	     * - Each row follows the binomial coefficient pattern.
	     * - We use the `generatedRow(n)` method to compute each row and add it to the result.
	     * 
	     * Example:
	     * - numRows = 5
	     * Pascal’s Triangle:
	     *   1
	     *   1 1
	     *   1 2 1
	     *   1 3 3 1
	     *   1 4 6 4 1
	     * 
	     * Dry Run:
	     * - i = 1 → [1] → Add to result
	     * - i = 2 → [1, 1] → Add to result
	     * - i = 3 → [1, 2, 1] → Add to result
	     * - i = 4 → [1, 3, 3, 1] → Add to result
	     * - i = 5 → [1, 4, 6, 4, 1] → Add to result
	     * 
	     * @param numRows The number of rows to generate
	     * @return A list of lists representing Pascal's Triangle
	     */
	    public List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> res = new ArrayList<>();
	        for (int i = 1; i <= numRows; i++) {
	            res.add(generatedRow(i));  // Compute the i-th row and add it to result
	        }
	        return res;
	    }
	}


}
