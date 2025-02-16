package com.Array1;

public class Leetcode48_RotateMatrix {
	
//	If we observe how elements shift, we see that rows become columns after rotation.
//	This hints at the need for transposition.
//	After transposing, the order is still incorrect, meaning we need to reverse each row to achieve the final result.
	
//	1. Why Transpose?
//			A 90-degree rotation swaps rows into columns.
//			If we observe element movements, transposing helps place elements in correct columns.
//	2.Why Reverse Each Row?
//			After transposing, the elements shift left to right instead of rotating.
//			Reversing fixes this by pushing them into the correct positions.
//	
	
	
	class Solution {

	    public void rotate(int[][] matrix) {
	        int m = matrix.length;  // Number of rows (N)
	        int n = matrix[0].length; // Number of columns (Always same as m since it's an NxN matrix)

	        /* 
	        Step 1: Transpose the matrix
	        - Transposition means converting rows into columns
	        - We swap matrix[i][j] with matrix[j][i] for elements above the diagonal.
	        
	        Example:
	        Original Matrix:
	        1  2  3
	        4  5  6
	        7  8  9

	        After Transposing:
	        1  4  7
	        2  5  8
	        3  6  9
	        */
	        for(int i = 0; i < m; i++) {
	            for(int j = i + 1; j < n; j++) { // Only swap upper diagonal elements
	                int temp = matrix[i][j];
	                matrix[i][j] = matrix[j][i];  // Swap matrix[i][j] and matrix[j][i]
	                matrix[j][i] = temp;
	            }
	        }

	        /* 
	        Step 2: Reverse each row to achieve 90-degree rotation
	        - Now that we have transposed, we simply reverse each row to complete the rotation.
	        
	        Example:
	        Transposed Matrix:
	        1  4  7
	        2  5  8
	        3  6  9

	        After Reversing Each Row:
	        7  4  1
	        8  5  2
	        9  6  3  (Final Rotated Matrix)
	        */
	        for (int i = 0; i < matrix.length; i++) {
	            int start = 0;
	            int end = matrix[i].length - 1;

	            while (start < end) {
	                // Swap leftmost and rightmost elements
	                int temp = matrix[i][start];
	                matrix[i][start] = matrix[i][end];
	                matrix[i][end] = temp;

	                start++;
	                end--;
	            }
	        }
	    }
	}



}
