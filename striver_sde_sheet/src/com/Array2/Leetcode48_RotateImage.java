package com.Array2;

public class Leetcode48_RotateImage {
	
//	To rotate a matrix by 90 degrees clockwise, we break it down into two steps:
//
//		1. Transpose the Matrix (Convert rows to columns)
//
//		The element at position (i, j) swaps with (j, i).
//		This effectively flips the matrix along its diagonal.
//		Why do we do this? Because it makes it easier to perform the final rotation.
	
//		2. Reverse Each Row (Swap leftmost and rightmost elements)
//
//		Once transposed, simply reversing each row completes the 90-degree rotation.
	
	class Solution {

	    public void rotate(int[][] matrix) {
	        int m = matrix.length;  // Number of rows (same as number of columns since it's a square matrix)
	        int n = matrix[0].length;  // Number of columns

	        /**
	         * Step 1: Transpose the matrix (convert rows into columns).
	         * - The transpose of a matrix is obtained by swapping matrix[i][j] with matrix[j][i].
	         * - We only traverse the upper triangle (i.e., when j > i) to avoid double swapping.
	         * 
	         * Example:
	         * Original Matrix:
	         *  1  2  3
	         *  4  5  6
	         *  7  8  9
	         * 
	         * After Transpose:
	         *  1  4  7
	         *  2  5  8
	         *  3  6  9
	         */
	        for (int i = 0; i < m; i++) {
	            for (int j = i + 1; j < n; j++) {
	                // Swap matrix[i][j] with matrix[j][i]
	                int temp = matrix[i][j];
	                matrix[i][j] = matrix[j][i];
	                matrix[j][i] = temp;
	            }
	        }

	        /**
	         * Step 2: Reverse each row to achieve 90-degree rotation.
	         * - After transposing, flipping each row gives the final rotated matrix.
	         * 
	         * Example (After Transpose):
	         *  1  4  7
	         *  2  5  8
	         *  3  6  9
	         * 
	         * Reverse each row:
	         *  7  4  1
	         *  8  5  2
	         *  9  6  3
	         * 
	         * This is our final rotated matrix.
	         */
	        for (int i = 0; i < matrix.length; i++) {
	            int start = 0;
	            int end = matrix[i].length - 1;

	            while (start < end) {
	                // Swap the first and last elements in each row
	                int temp = matrix[i][start];
	                matrix[i][start] = matrix[i][end];
	                matrix[i][end] = temp;

	                start++;
	                end--;
	            }
	        }
	    }
	}
	
//	Why Do We Transpose the Matrix First?
//			Directly rotating each element is complex as it requires a multi-step swapping process.
//			Transpose converts columns into rows, which simplifies the next step of reversing rows.
//			It reduces complexity and makes implementation easier.
	
	//Dry Run
	
	// Input: matrix = 
//  [ [1, 2, 3],
//    [4, 5, 6],
//    [7, 8, 9] ]

// Step 1: Transpose
// Swap matrix[i][j] with matrix[j][i] for j > i
// Transposed matrix:
//  [ [1, 4, 7],
//    [2, 5, 8],
//    [3, 6, 9] ]

// Step 2: Reverse each row
// Reversing rows gives:
//  [ [7, 4, 1],
//    [8, 5, 2],
//    [9, 6, 3] ]

// Output: Rotated Matrix
//  [ [7, 4, 1],
//    [8, 5, 2],
//    [9, 6, 3] ]

	
//	Time Complexity: O(N²)
//	Transpose step: O(N²) (traversing upper half)
//	Reverse step: O(N²) (reversing each row)
//	Overall: O(N²)
//	Space Complexity: O(1)
//	The rotation is done in-place, so no extra space is used.


}
