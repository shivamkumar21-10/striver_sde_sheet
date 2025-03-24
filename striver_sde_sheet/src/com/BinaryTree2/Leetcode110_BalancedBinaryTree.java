package com.BinaryTree2;

public class Leetcode110_BalancedBinaryTree {
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
	     * This function calculates the height of the tree while simultaneously checking if the tree is balanced.
	     * Instead of checking balance separately for each node, we optimize by computing both height and balance 
	     * status in a single traversal.
	     * 
	     * @param root - The current node in the tree
	     * @param flag - A boolean array of size 1 (used to store whether the tree is balanced)
	     * @return The height of the current subtree
	     */
	    int height(TreeNode root, boolean[] flag) {
	        // Base case: If the node is null, height is 0
	        if (root == null) {
	            return 0;
	        }

	        // Recursively compute the height of the left subtree
	        int left = height(root.left, flag);

	        // Recursively compute the height of the right subtree
	        int right = height(root.right, flag);

	        // Check if the current subtree is balanced
	        // A tree is balanced if for every node, the difference between left and right height is at most 1
	        flag[0] = flag[0] & (Math.abs(left - right) <= 1);

	        // Return height of the current node
	        return 1 + Math.max(left, right);
	    }

	    /**
	     * Function to check if a binary tree is height-balanced.
	     * A binary tree is balanced if for every node, the height of the left and right subtrees differ by at most 1.
	     * 
	     * @param root - The root node of the binary tree
	     * @return True if the tree is balanced, otherwise False
	     */
	    public boolean isBalanced(TreeNode root) {
	        // Flag array to store the balance status (Java doesn't support pass-by-reference for primitive types)
	        boolean[] flag = new boolean[1];

	        // Initially assume the tree is balanced
	        flag[0] = true;

	        // Compute height while updating the balance status
	        height(root, flag);

	        // Return whether the tree is balanced or not
	        return flag[0];
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Understanding Balanced Binary Tree:**
	   - A binary tree is **balanced** if for every node:
	     - The height of the left and right subtrees **differs by at most 1**.
	   - This ensures that the tree does not become **too skewed**, leading to efficient operations.

	2️⃣ **Why is the Previous Naïve Approach Inefficient?**
	   - The basic approach to check balance involves:
	     - Computing height separately for every node.
	     - Calling `height()` for each subtree at every node.
	   - This leads to **O(N²) time complexity**, as height calculation is repeated.

	3️⃣ **Optimized Approach:**
	   - Instead of **checking balance separately**, we:
	     - **Calculate height and check balance simultaneously**.
	     - Use a **boolean array** (`flag[0]`) to store whether the tree remains balanced.
	   - This ensures **O(N) complexity** by performing a **single traversal**.

	====================================================================
	🔹 Dry Run Example:
	====================================================================
	Consider the binary tree:

	          1
	         / \
	        2   3
	       / \    
	      4   5   

	🔹 **Recursive Execution Breakdown:**
	1️⃣ `height(4) = 1, height(5) = 1`
	   - `flag[0] = true & abs(1 - 1) <= 1` ✅ Balanced

	2️⃣ `height(2) = 1 + max(1, 1) = 2`
	   - `flag[0] = true & abs(2 - 0) <= 1` ✅ Balanced

	3️⃣ `height(3) = 1`
	   - `flag[0] = true & abs(2 - 1) <= 1` ✅ Balanced

	4️⃣ `height(1) = 1 + max(2, 1) = 3`
	   - `flag[0] = true & abs(2 - 1) <= 1` ✅ Balanced

	Final result: **Tree is balanced ✅**

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity: O(N)**  
	  - Each node is **visited once**.
	  - We compute height and balance **in a single traversal**.

	- **Space Complexity: O(H)**  
	  - The function uses **recursion**, which takes **O(H)** space in the call stack.
	  - In a **balanced tree**, **H = log(N)** → **O(log N) space**.
	  - In a **skewed tree**, **H = N** → **O(N) space**.

	====================================================================
	🔹 Summary of Optimization:
	====================================================================
	✅ **Previous Approach:**  
	   - Time Complexity: **O(N²)** (Multiple height calculations)  
	   - Space Complexity: **O(H)**  

	✅ **Optimized Approach (Current Implementation):**  
	   - Time Complexity: **O(N)** (Single traversal for both height and balance check)  
	   - Space Complexity: **O(H)** (Recursion depth)  

	💡 **Key Takeaway:** We reduced redundant height calculations by computing both **height** and **balance status** in a single pass using a helper function. 🚀
	*/

}
