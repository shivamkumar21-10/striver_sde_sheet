package com.BinaryTree2;

public class Leetcode100_SameTree {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode() {}
	 *     TreeNode(int val) { this.val = val; }
	 *     TreeNode(int val, TreeNode left, TreeNode right) {
	 *         this.val = val;
	 *         this.left = left;
	 *         this.right = right;
	 *     }
	 * }
	 */

	class Solution {
	    /**
	     * Function to check if two binary trees are identical.
	     * 
	     * Two trees are considered identical if:
	     * 1️⃣ They have the **same structure**.
	     * 2️⃣ They have **the same values** at corresponding nodes.
	     *
	     * @param p Root of the first tree.
	     * @param q Root of the second tree.
	     * @return True if both trees are the same, otherwise False.
	     */
	    public boolean isSameTree(TreeNode p, TreeNode q) {
	        // Base Case 1: If both nodes are null, they are identical.
	        if (p == null && q == null) return true;

	        // Base Case 2: If only one of them is null, trees are different.
	        if (p == null || q == null) return false;

	        // Base Case 3: If values at the current nodes are different, trees are different.
	        if (p.val != q.val) return false;

	        // Recursively check the left and right subtrees.
	        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Understanding the Problem:**
	   - We need to check if both trees are structurally the same and have the same values.
	   - This can be efficiently done using a **recursive depth-first traversal**.

	2️⃣ **Base Cases:**
	   - If both nodes are `null`, they are the same at this level ✅.
	   - If one node is `null` but the other isn't, trees are different ❌.
	   - If values at corresponding nodes don't match, trees are different ❌.

	3️⃣ **Recursive Traversal:**
	   - Traverse both **left subtrees** recursively.
	   - Traverse both **right subtrees** recursively.
	   - If both return `true`, the trees are identical.

	====================================================================
	🔹 Dry Run Example:
	====================================================================

	Example 1 (Same Trees):

	       Tree 1          Tree 2
	          1               1
	         / \             / \
	        2   3           2   3

	🔹 **Recursive Execution:**
	1️⃣ `isSameTree(1,1)` → ✅ → Recurse on left and right.
	2️⃣ `isSameTree(2,2)` → ✅ → Recurse further.
	3️⃣ `isSameTree(null,null)` → ✅
	4️⃣ `isSameTree(null,null)` → ✅
	5️⃣ `isSameTree(3,3)` → ✅ → Recurse further.
	6️⃣ `isSameTree(null,null)` → ✅
	7️⃣ `isSameTree(null,null)` → ✅
	✅ Trees are **same**.

	Example 2 (Different Trees):

	       Tree 1          Tree 2
	          1               1
	         / \             / \
	        2   3           2   4

	🔹 **Recursive Execution:**
	1️⃣ `isSameTree(1,1)` → ✅
	2️⃣ `isSameTree(2,2)` → ✅
	3️⃣ `isSameTree(3,4)` → ❌  
	❌ Trees are **not the same**.

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity: O(N)**
	  - Each node is visited **exactly once**.
	  - Since we traverse both trees **simultaneously**, it's **O(N)** where **N** is the number of nodes.

	- **Space Complexity: O(H)**
	  - The function uses **recursion**, which takes **O(H)** space in the call stack.
	  - **H = log(N)** in a balanced tree → **O(log N)** space.
	  - **H = N** in a skewed tree → **O(N)** space.

	====================================================================
	🔹 Summary:
	====================================================================
	✅ **Recursive traversal ensures both structure and values match.**  
	✅ **Base cases handle all possible scenarios.**  
	✅ **Optimal solution with O(N) time complexity.**  
	*/


}
