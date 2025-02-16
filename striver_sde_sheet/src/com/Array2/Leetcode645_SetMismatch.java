package com.Array2;

class Leetcode645_SetMismatch {
	class Solution {
		public int[] findErrorNums(int[] nums) {
			/*
			 ************************** INTUITION ************************** - Given an array `nums` of size `n`,
			 * containing numbers from 1 to `n` but with one missing and one duplicate. -
			 * Our task is to find: 1. The duplicated number `x` 2. The missing number `y` -
			 * Instead of using extra space (like HashMap or sorting), we can use
			 * mathematical equations.
			 ************************** 
			 * MATHEMATICAL DERIVATION ************************** - The sum of first `n`
			 * natural numbers: SUM_N = (n * (n + 1)) / 2 - The sum of squares of first `n`
			 * natural numbers: SUM_SQ_N = (n * (n + 1) * (2n + 1)) / 6
			 * 
			 * - The actual sum of elements in `nums`: SUM_ARR = sum of all elements in
			 * `nums` - The actual sum of squares in `nums`: SUM_SQ_ARR = sum of squares of
			 * all elements in `nums`
			 * 
			 * - If `x` is the duplicate and `y` is the missing number, then:
			 * 
			 * 1️⃣ **Sum Equation:** SUM_ARR - SUM_N = x - y ⟶ (Equation 1)
			 * 
			 * 2️⃣ **Sum of Squares Equation:** SUM_SQ_ARR - SUM_SQ_N = x² - y² Using `a² -
			 * b² = (a - b)(a + b)`: (x - y)(x + y) = SUM_SQ_ARR - SUM_SQ_N ⟶ (Equation 2)
			 * 
			 * - From Equation (1), we get `val1 = x - y` - From Equation (2), we solve
			 * `val2 = x + y` - Adding both: x = (val1 + val2) / 2 - Finding y: y = x - val1
			 */

			int n = nums.length;

			// Expected sum and sum of squares of first n natural numbers
			long sumExpected = (n * (n + 1L)) / 2; // SUM_N
			long sumSqExpected = (n * (n + 1L) * (2 * n + 1L)) / 6; // SUM_SQ_N

			// Actual sum and sum of squares from given array
			long sumArr = 0; // SUM_ARR
			long sumSqArr = 0; // SUM_SQ_ARR

			for (int num : nums) {
				sumArr += num;
				sumSqArr += (long) num * num; // Avoid integer overflow
			}

			// Compute values from equations:
			long val1 = sumArr - sumExpected; // (x - y) from Equation (1)
			long val2 = (sumSqArr - sumSqExpected) / val1; // (x + y) from Equation (2)

			int duplicate = (int) ((val1 + val2) / 2); // x = (val1 + val2) / 2
			int missing = (int) (duplicate - val1); // y = x - val1

			return new int[] { duplicate, missing };

			/*
			 ************************** DRY RUN EXAMPLE ************************** Example: nums = [1, 2, 2, 4]
			 * Expected Output: [2, 3] // 2 is duplicate, 3 is missing
			 * 
			 * Step | Computation
			 * -------------------------------------------------------------- 1. Compute
			 * Expected Sum SUM_N SUM_N = (4 * (4 + 1)) / 2 = 10
			 * 
			 * 2. Compute Expected Sum of Squares SUM_SQ_N SUM_SQ_N = (4 * 5 * 9) / 6 = 30
			 * 
			 * 3. Compute Actual Sum SUM_ARR from nums SUM_ARR = 1 + 2 + 2 + 4 = 9
			 * 
			 * 4. Compute Actual Sum of Squares SUM_SQ_ARR SUM_SQ_ARR = 1² + 2² + 2² + 4² =
			 * 1 + 4 + 4 + 16 = 25
			 * 
			 * 5. Compute val1 = SUM_ARR - SUM_N (x - y) val1 = 9 - 10 = -1
			 * 
			 * 6. Compute val2 = (SUM_SQ_ARR - SUM_SQ_N) / val1 (x + y) val2 = (25 - 30) /
			 * -1 = 5
			 * 
			 * 7. Compute duplicate x x = (val1 + val2) / 2 x = (-1 + 5) / 2 = 2
			 * 
			 * 8. Compute missing y y = x - val1 y = 2 - (-1) = 3
			 * 
			 * Final Output: [2, 3]
			 */
		}

//    ***********xor Based solution*************
		class Solution2 {
			public int[] findErrorNums(int[] nums) {
				/*
				 ************************** INTUITION ************************** - We use XOR to find the missing and
				 * duplicate numbers in O(n) time and O(1) space. - XOR cancels out duplicate
				 * elements (`x ^ x = 0`), leaving only the missing and duplicate numbers.
				 ************************** 
				 * MATHEMATICAL DERIVATION ************************** - Compute xorTotal of
				 * numbers 1 to n and elements in nums: xorTotal = (1 ⊕ 2 ⊕ ... ⊕ n) ⊕ (nums[0]
				 * ⊕ nums[1] ⊕ ... ⊕ nums[n-1]) This will give: `xorTotal = x ⊕ y` (duplicate ⊕
				 * missing)
				 * 
				 * - Find rightmost set bit in xorTotal: This helps divide numbers into two
				 * groups based on differing bits in x and y.
				 * 
				 * - XOR two separate groups to determine x (duplicate) and y (missing).
				 */

				int n = nums.length;
				int xorTotal = 0;

				// Step 1: Compute xorTotal = (1 ⊕ 2 ⊕ ... ⊕ n) ⊕ (nums[0] ⊕ nums[1] ⊕ ... ⊕
				// nums[n-1])
				for (int num : nums) {
					xorTotal ^= num;
				}
				for (int i = 1; i <= n; i++) {
					xorTotal ^= i;
				}

				// Step 2: Find rightmost set bit in xorTotal
				int rightmostBit = xorTotal & -xorTotal;

				int xor1 = 0, xor2 = 0; // Two separate groups

				// Step 3: Divide numbers into two groups and XOR separately
				for (int num : nums) {
					if ((num & rightmostBit) == 0) {
						xor1 ^= num;
					} else {
						xor2 ^= num;
					}
				}
				for (int i = 1; i <= n; i++) {
					if ((i & rightmostBit) == 0) {
						xor1 ^= i;
					} else {
						xor2 ^= i;
					}
				}

				// Step 4: Identify duplicate and missing values
				for (int num : nums) {
					if (num == xor1) {
						return new int[] { xor1, xor2 }; // xor1 is duplicate, xor2 is missing
					}
				}
				return new int[] { xor2, xor1 }; // xor2 is duplicate, xor1 is missing

				/*
				 ************************** DRY RUN EXAMPLE ************************** Example: nums = [1, 2, 2, 4]
				 * Expected Output: [2, 3] // 2 is duplicate, 3 is missing
				 * 
				 * Step | Computation
				 * -------------------------------------------------------------- 1. Compute
				 * xorTotal = (1 ⊕ 2 ⊕ 3 ⊕ 4) ⊕ (1 ⊕ 2 ⊕ 2 ⊕ 4) = (1 ⊕ 2 ⊕ 3 ⊕ 4 ⊕ 1 ⊕ 2 ⊕ 2 ⊕
				 * 4) = (3 ⊕ 3) ⊕ (2 ⊕ 2 ⊕ 2) ⊕ (4 ⊕ 4) ⊕ 1 = 0 ⊕ 2 ⊕ 1 = 3 (So, xorTotal = 2 ⊕
				 * 3)
				 * 
				 * 2. Find rightmost set bit in xorTotal (3) = 0011 → Rightmost set bit = 1
				 * 
				 * 3. Divide numbers into two groups based on bit '1': - Group 1 (bit '1' is
				 * set): 1, 3 - Group 2 (bit '1' is NOT set): 2, 2, 4, 4
				 * 
				 * 4. Compute XOR within each group: - XOR of Group 1 = 1 ⊕ 3 = 3 (missing
				 * number) - XOR of Group 2 = 2 ⊕ 2 ⊕ 4 ⊕ 4 = 2 (duplicate number)
				 * 
				 * 5. Output: [2, 3]
				 */
			}
		}
	}
}
