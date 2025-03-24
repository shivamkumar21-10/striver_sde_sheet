package com.BinaryTree2;


public class Leetcode104_MaximumDepthofBinaryTree {
	
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
	     * Function to calculate the maximum depth (height) of a binary tree.
	     * Depth of a node is the number of edges from the root to that node.
	     * Height of a tree is the depth of the deepest node.
	     */
	    public int maxDepth(TreeNode root) {
	        // Base Case: If the tree is empty, depth is 0.
	        if(root == null){
	            return 0;
	        }

	        // Recursively calculate the depth of the left subtree.
	        int left = maxDepth(root.left);

	        // Recursively calculate the depth of the right subtree.
	        int right = maxDepth(root.right);

	        // The depth of the current node is 1 + maximum depth of left and right subtrees.
	        return 1 + Math.max(left, right);
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Concept of Depth and Height:**
	   - The **depth** of a node is the number of edges from the root to that node.
	   - The **height** of a tree is the depth of its deepest node.

	2️⃣ **How the Recursive Approach Works:**
	   - If the tree is empty (`root == null`), return `0` (base case).
	   - Recursively calculate the depth of the **left** and **right** subtrees.
	   - The maximum depth at any node is `1 + max(left subtree depth, right subtree depth)`.

	====================================================================
	🔹 Dry Run Example:
	====================================================================
	Consider the binary tree:

	          1
	         / \
	        2   3
	       / \    
	      4   5   

	Step-by-step execution:

	🔹 **Recursive Calls Breakdown:**
	   - `maxDepth(1)` → Calls `maxDepth(2)` and `maxDepth(3)`
	   - `maxDepth(2)` → Calls `maxDepth(4)` and `maxDepth(5)`
	   - `maxDepth(4)` → Calls `maxDepth(null)`, returns `0`
	   - `maxDepth(5)` → Calls `maxDepth(null)`, returns `0`
	   - `maxDepth(2)` → Returns `1 + max(0,0) = 1`
	   - `maxDepth(3)` → Calls `maxDepth(null)`, returns `0`
	   - `maxDepth(1)` → Returns `1 + max(1,0) = 2`

	Final result: **Height = 3**

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity:** `O(N)`
	  - Each node is visited **once**, so the time complexity is **O(N)** where `N` is the number of nodes.
	- **Space Complexity:** `O(H)`
	  - The recursive call stack uses `O(H)` space, where `H` is the height of the tree.
	  - In a balanced tree, `H = log(N)`, making space complexity `O(logN)`.
	  - In a skewed tree, `H = N`, making space complexity `O(N)`.

	====================================================================
	*/


}
