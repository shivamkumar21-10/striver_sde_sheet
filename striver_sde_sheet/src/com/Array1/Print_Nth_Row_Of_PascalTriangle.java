package com.Array1;

public class Print_Nth_Row_Of_PascalTriangle {
	
	//Brute force -- TC O(n * r)
	static void printNthRow(int n){
		
		for(int c=1; c<=n; c++) {
			System.out.println(FindNCR.ncr(n-1, c-1));
		}
	}
	
	//Optimsl
	
	static void printNthRowOptimal(int n) {
	    /*
	    ===========================================
	    🌟 Intuition & Explanation 🌟
	    ===========================================

	    ➤ This function prints the **n-th row** of Pascal’s Triangle.

	    ➤ Pascal’s Triangle is a triangular array where each row represents 
	      the coefficients of the binomial expansion `(x + y)^(row-1)`.
	    
	    ➤ Example of Pascal’s Triangle:
	        Row 1:       1
	        Row 2:      1 1
	        Row 3:     1 2 1
	        Row 4:    1 3 3 1
	        Row 5:   1 4 6 4 1
	        Row 6:  1 5 10 10 5 1

	    ➤ The `n-th row` contains `n` elements, and the values are computed using:
	        C(n-1, 0), C(n-1, 1), C(n-1, 2), ..., C(n-1, n-1)
	      where `C(n-1, r) = (n-1)! / (r! * (n-1-r)!)` (Binomial Coefficient)

	    ➤ Instead of using factorial, we use an **optimized formula**:
	        C(n, r) = C(n, r-1) * (n-r) / r

	    ➤ This avoids **factorial computation**, reducing **time complexity to O(n)**.

	    ===========================================
	    📝 Dry Run Example: n = 5
	    ===========================================

	    We are computing **5th row** of Pascal’s Triangle:
	    
	    - Start with `ans = 1`
	    - Compute subsequent values using: `ans = ans * (n - c) / c`

	    Step-by-step:

	    | Step | c | ans Formula          | ans Value |
	    |------|---|----------------------|-----------|
	    | 1    | 0 | 1                    | 1         |
	    | 2    | 1 | 1 * (5-1) / 1 = 4     | 4         |
	    | 3    | 2 | 4 * (5-2) / 2 = 6     | 6         |
	    | 4    | 3 | 6 * (5-3) / 3 = 4     | 4         |
	    | 5    | 4 | 4 * (5-4) / 4 = 1     | 1         |

	    **Final Output:** `1 4 6 4 1`

	    ===========================================
	    🚀 Key Observations:
	    ===========================================

	    ✅ Each value in Pascal’s Triangle can be computed from the previous value using:
	       `C(n, r) = C(n, r-1) * (n-r) / r`
	    
	    ✅ This avoids the overhead of computing factorials explicitly.

	    ✅ Time Complexity: `O(n)` (Iterates `n` times)
	       Space Complexity: `O(1)` (No extra storage used)

	    ✅ This is an **optimal** approach to compute Pascal's Triangle row **without extra space**.

	    ===========================================
	    🔥 Code Implementation 🔥
	    ===========================================
	    */

	    int ans = 1;  // The first element is always `1`
	    System.out.println(ans); // Print the first element

	    for (int c = 1; c < n; c++) {
	        ans = ans * (n - c);  // Multiply by (n-c)
	        ans = ans / c;        // Divide by c
	        System.out.println(ans); // Print the computed value
	    }
	}

	
	public static void main(String[] args) {
//		printNthRow(6);
		printNthRowOptimal(6);
	}
}
