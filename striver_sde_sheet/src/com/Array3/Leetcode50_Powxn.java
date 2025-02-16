package com.Array3;

public class Leetcode50_Powxn {
	
	//*********** Recursive *****************
//	**Intuition Behind the Approach
//	We need to compute x^n (x raised to the power n) efficiently.
//	A naive solution would be multiplying x n times, but that would take O(n) time, which is slow for large n.
//	Instead, we use Exponentiation by Squaring, reducing time complexity to O(log n).
//	
//	**Key Observations:
//	1. Base Case: Any number raised to power 0 is 1, i.e., x^0 = 1.
//	2. Handling Negative Powers:
//		x^(-n) = 1 / x^n (Convert the problem into a positive exponent).
//	3. Even Powers:
//		Instead of x^n = x * x * ... * x (n times), we can break it down recursively:
//			x^n = (x^2)^(n/2), reducing the exponent by half.
//	4. Odd Powers:
//		x^n = x * x^(n-1), bringing n closer to an even number.
	
	class Solution {
	    public double myPow(double x, int n) {
	        // Convert 'n' to a long to handle the case where n = Integer.MIN_VALUE.
	        // This prevents integer overflow when converting negative to positive.
	        return helper(x, Long.valueOf(n));
	    }

	    public double helper(double num, long pow) {
	        // Edge case: If the base is extremely large/small, we return 0.
	        if (num < -10000 || num > 10000) {
	            return 0;
	        }

	        // Base Case: Any number raised to power 0 is 1.
	        if (pow == 0) return 1;

	        // If power is negative, convert the problem into positive exponentiation.
	        if (pow < 0) {
	            pow *= -1;   // Convert negative power to positive.
	            num = 1 / num;  // Invert the base to handle negative power.
	        }

	        // If power is even, we use (x^2)^(n/2) approach.
	        // This reduces the problem size by half in each step.
	        if (pow % 2 == 0) {
	            return helper(num * num, pow / 2);
	        } 
	        // If power is odd, we break it down as: x^n = x * x^(n-1)
	        else {
	            return num * helper(num, pow - 1);
	        }
	    }
	}
	//Dry run
	// Input: x = 2, n = 10
	// Expected Output: 1024

	// Step 1: Convert problem to helper(2, 10)

	// Step 2: Since 10 is even, we compute helper(2*2, 10/2) => helper(4, 5)

	// Step 3: Since 5 is odd, we break it as helper(4, 4) * 4 => helper(16, 2) * 4

	// Step 4: Since 2 is even, helper(16*16, 1) => helper(256, 1)

	// Step 5: Since 1 is odd, return 256 * helper(256, 0) => 256 * 1 = 256

	// Final Answer: 1024
	
	
	
	//*********** LOOP approach *********
	
//	**Intuition Behind the Approach
//	The goal is to compute x^n (x raised to the power n) efficiently.
//	A brute force approach (multiplying x n times) would take O(n) time, which is slow for large n.
//	Instead, we use Exponentiation by Squaring, reducing time complexity to O(log n).
//	**Key Observations:
//	1. Base Case: Any number raised to the power 0 is 1, i.e., x^0 = 1.
//	2. Handling Negative Powers:
//		x^(-n) = 1 / x^n, so we invert x and convert n to positive.
//	3. Iterative Squaring:
//		Instead of x^n = x * x * ... * x (n times), we can efficiently reduce computations:
//			If n is even → x^n = (x^2)^(n/2), reducing n by half.
//			If n is odd → x^n = x * x^(n-1), making n even.


	class Solution1 {

	    public double myPow(double x, long n) {
	        double ans = 1.0;  // Initialize result to 1
	        long nn = n;  // Store original value of 'n' in long to handle edge cases

	        /**
	         * If the exponent is negative, convert it to positive.
	         * Instead of computing x^-n, we compute x^n and take reciprocal at the end.
	         */
	        if (n < 0) nn = -nn;  // Convert to positive

	        /**
	         * Iterate until exponent 'nn' becomes zero.
	         * - If 'nn' is odd: Multiply 'ans' by 'x' and decrease 'nn' by 1.
	         * - If 'nn' is even: Square 'x' and divide 'nn' by 2 (reducing problem size).
	         */
	        while (nn > 0) {
	            if (nn % 2 == 1) {  // If exponent is odd
	                ans = ans * x;  // Multiply the current value of 'x' with result
	                nn = nn - 1;  // Reduce 'nn' by 1 to make it even
	            } else {  // If exponent is even
	                x = x * x;  // Square the base
	                nn = nn / 2;  // Reduce exponent by half
	            }
	        }

	        /**
	         * If original 'n' was negative, take reciprocal of the result.
	         * This is because x^-n = 1 / x^n.
	         */
	        if (n < 0) ans = 1.0 / ans;
	        
	        return ans;  // Return final computed power
	    }
	}

	



}
