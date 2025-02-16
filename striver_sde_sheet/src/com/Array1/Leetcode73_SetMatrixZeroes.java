package com.Array1;

import java.util.*;

public class Leetcode73_SetMatrixZeroes {

//	Instead of modifying the matrix while iterating (which can cause incorrect results),
//	we first store all zero positions and then process them separately.
//	This ensures that we do not modify the matrix while scanning for zeroes.

	// My solution
	class Solution {
		public void setZeroes(int[][] matrix) {
			int m = matrix.length; // Number of rows
			int n = matrix[0].length; // Number of columns

			// Set to store positions of zeroes
			Set<List<Integer>> zeroPositions = new HashSet<>();

			// Step 1: Find all positions where matrix[i][j] is 0
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == 0) {
						zeroPositions.add(Arrays.asList(i, j)); // Store (row, col) in the set
					}
				}
			}

			// Step 2: Set entire row and column to zero for each stored position
			for (List<Integer> zero : zeroPositions) {
				int i = zero.get(0); // Row index of zero
				int j = zero.get(1); // Column index of zero

				// Set entire column `j` to zero
				for (int row = 0; row < m; row++) {
					matrix[row][j] = 0;
				}

				// Set entire row `i` to zero
				for (int col = 0; col < n; col++) {
					matrix[i][col] = 0;
				}
			}
		}
	}

//	Approach	        Time Complexity	   Space Complexity
//	Using Extra Set	      O(m × n)	        O(min(m, n))

	// Better

	class Solution2 {
	    public void setZeroes(int[][] matrix) {
	        int m = matrix.length;    // Number of rows
	        int n = matrix[0].length; // Number of columns
	        
	        int[] row = new int[m];   // Array to mark rows to be zeroed
	        int[] col = new int[n];   // Array to mark columns to be zeroed

	        // Step 1: Mark rows and columns that need to be set to zero
	        for(int i = 0; i < m; i++) {
	            for(int j = 0; j < n; j++) {
	                if(matrix[i][j] == 0) {
	                    row[i] = 1;  // Mark row `i`
	                    col[j] = 1;  // Mark column `j`
	                }
	            }
	        }

	        // Step 2: Set matrix cells to zero using marked rows and columns
	        for(int i = 0; i < m; i++) {
	            for(int j = 0; j < n; j++) {
	                if(row[i] == 1 || col[j] == 1) {
	                    matrix[i][j] = 0;
	                }
	            }
	        }
	    }
	}
	
	//optimal - without extra space
//	Marking rows and columns using the first row & first column:
//
//		Instead of separate row[] and col[] arrays, we store row-zero flags in matrix[0][..] and column-zero flags in matrix[..][0].
//		matrix[i][0] == 0 → Entire row i should be zero.
//		matrix[0][j] == 0 → Entire column j should be zero.
//		Handling the first column separately (col0 variable):
//
//		Since matrix[0][0] is shared for both row 0 and column 0, we cannot use it to track column 0.
//		Instead, we use an extra variable col0 to track if column 0 should be set to zero.
	
	class Solution3{
	    public void setZeroes(int[][] matrix) {
	        /*
	        =======================
	        🌟 Intuition & Observations 🌟
	        =======================

	        Given an `m x n` matrix, we must set entire rows and columns to zero if any cell is `0`.

	        ✅ We want to do this **in-place** with `O(1)` extra space.

	        ❌ A naive approach (using an `O(m + n)` space array) would require separate `row[]` and `col[]` arrays
	           to track which rows and columns should be set to zero.

	        ✅ Instead, we can use:
	           - The **first row** to track which columns should be zeroed.
	           - The **first column** to track which rows should be zeroed.
	           - An extra variable `col0` to **track whether column 0 should be set to zero** separately.

	        =======================
	        📝 Dry Run Example 📝
	        =======================

	        **Input:**
	        matrix =
	        [ [1, 2, 3],
	          [4, 0, 6],
	          [7, 8, 9] ]

	        **Step 1: Marking zeros using first row & column**
	        - The zero is at `matrix[1][1]`
	        - We mark: `matrix[1][0] = 0` (mark row 1)
	                   `matrix[0][1] = 0` (mark column 1)

	        Matrix after marking:
	        [ [1,  0, 3],
	          [0,  0, 6],
	          [7,  8, 9] ]

	        **Step 2: Set cells to zero using markers**
	        - If `matrix[i][0] == 0` or `matrix[0][j] == 0`, set `matrix[i][j] = 0`
	        
	        Matrix after updating:
	        [ [1,  0, 3],
	          [0,  0, 0],
	          [7,  8, 9] ]

	        **Step 3 & 4: Handle first row & first column separately**
	        - Since `matrix[0][1] == 0`, set column `1` to zero.
	        
	        Final Output:
	        [ [1,  0, 3],
	          [0,  0, 0],
	          [7,  0, 9] ]

	        */

	        int m = matrix.length;    // Number of rows
	        int n = matrix[0].length; // Number of columns
	        
	        int col0 = 1;  // Variable to track if column 0 should be zeroed

	        /*
	        =========================
	        🔹 Step 1: Mark rows & cols using first row/column
	        =========================
	        - Iterate over the matrix
	        - If `matrix[i][j] == 0`:
	          - Mark `matrix[i][0] = 0` (Indicate row `i` should be zero)
	          - Mark `matrix[0][j] = 0` (Indicate column `j` should be zero)
	          - If `j == 0`, we can't use `matrix[0][0]`, so we track column 0 separately using `col0`
	        */

	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                if (matrix[i][j] == 0) {
	                    matrix[i][0] = 0;  // Mark row `i` to be zeroed
	                    
	                    if (j != 0) {
	                        matrix[0][j] = 0;  // Mark column `j` to be zeroed
	                    } else {
	                        col0 = 0;  // Special case: If `j == 0`, track column 0 separately
	                    }
	                }
	            }
	        }

	        /*
	        =========================
	        🔹 Step 2: Modify matrix using markers
	        =========================
	        - Iterate again (starting from row `1` and column `1`) to **set elements to zero**.
	        - If `matrix[0][j] == 0` OR `matrix[i][0] == 0`, it means either:
	          - The **column `j`** was marked zero (so entire column should be zero).
	          - The **row `i`** was marked zero (so entire row should be zero).
	        */

	        for (int i = 1; i < m; i++) {
	            for (int j = 1; j < n; j++) {
	                if (matrix[i][j] != 0) {
	                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
	                        matrix[i][j] = 0;
	                    }
	                }
	            }
	        }

	        /*
	        =========================
	        🔹 Step 3: Handle first row separately
	        =========================
	        - If `matrix[0][0] == 0`, we set the **entire first row** to zero.
	        - This ensures that **column markers don’t affect the first row**.
	        */

	        if (matrix[0][0] == 0) {
	            for (int j = 0; j < n; j++) {
	                matrix[0][j] = 0;
	            }
	        }

	        /*
	        =========================
	        🔹 Step 4: Handle first column separately
	        =========================
	        - If `col0 == 0`, we set the **entire first column** to zero.
	        - This prevents `matrix[0][0]` from interfering with column tracking.
	        */

	        if (col0 == 0) {
	            for (int i = 0; i < m; i++) {
	                matrix[i][0] = 0;
	            }
	        }
	    }
	}





}
