package com.BinarySearch;

public class KthelementoftwoArrays {
	
	//Approach - 1 TC=O(k)
	
	class Solution {
	    public int kthElement(int a[], int b[], int k) {
	        /*
	        ---------------------- INTUITION ----------------------
	        - Given two sorted arrays `a[]` and `b[]`, find the K-th smallest element in the merged sorted sequence.
	        - Instead of merging both arrays (O(n + m) space), we use **two pointers** (`i` for `a[]`, `j` for `b[]`).
	        - We iterate **only until the K-th element** is found.
	        - The smaller of `a[i]` and `b[j]` is picked first.
	        - If one array is exhausted, we take the remaining elements from the other.
	        - This runs in **O(K) time**, much better than merging both arrays.

	        ---------------------- VARIABLES ----------------------
	        */
	        int i = 0;  // Pointer for array `a`
	        int j = 0;  // Pointer for array `b`
	        int n = a.length;  // Length of array `a`
	        int m = b.length;  // Length of array `b`

	        /*
	        ---------------------- FIND K-th ELEMENT ----------------------
	        - Traverse both arrays, reducing `k` each time an element is selected.
	        */
	        while (i < n && j < m) {  
	            if (a[i] <= b[j]) {  // If element in `a[]` is smaller, select it
	                k--;
	                if (k == 0) return a[i];  // If this is the K-th element, return it
	                i++;  // Move to the next element in `a[]`
	            } else {  // If element in `b[]` is smaller, select it
	                k--;
	                if (k == 0) return b[j];  // If this is the K-th element, return it
	                j++;  // Move to the next element in `b[]`
	            }
	        }

	        /*
	        ---------------------- HANDLE REMAINING ELEMENTS ----------------------
	        - If one array is exhausted, continue picking elements from the other.
	        */
	        if (k > 0) {
	            while (i < n) {  // Process remaining elements in `a[]`
	                k--;
	                if (k == 0) return a[i];
	                i++;
	            }

	            while (j < m) {  // Process remaining elements in `b[]`
	                k--;
	                if (k == 0) return b[j];
	                j++;
	            }
	        }

	        return -1;  // This case won't occur if `k` is valid
	    }
	}

	/*
	----------------------------------------------------------------
	## **DRY RUN OF THE CODE (Step-by-Step Execution)**
	----------------------------------------------------------------
	### **Example 1**
	#### **Input:**
	    a[] = [2, 3, 6, 7, 9]
	    b[] = [1, 4, 8, 10]
	    k = 5

	#### **Step-by-Step Execution:**
	| Iteration | i | j | k | Selected Element | Remaining K |
	|-----------|---|---|---|-----------------|-------------|
	| 1         | 0 | 0 | 5 | 1 (from b[])    | 4           |
	| 2         | 0 | 1 | 4 | 2 (from a[])    | 3           |
	| 3         | 1 | 1 | 3 | 3 (from a[])    | 2           |
	| 4         | 2 | 1 | 2 | 4 (from b[])    | 1           |
	| 5         | 2 | 2 | 1 | **6 (from a[])**| **0** (Found!) |

	#### **Output:**
	   **6**

	----------------------------------------------------------------
	## **TIME COMPLEXITY ANALYSIS**
	----------------------------------------------------------------
	- We **only traverse until K elements** are selected.
	- **Best case:** `O(1)` (if K = 1).
	- **Worst case:** `O(K)` (if K = n + m).
	- **Space Complexity:** `O(1)` (no extra space used).
	----------------------------------------------------------------
	*/


}
