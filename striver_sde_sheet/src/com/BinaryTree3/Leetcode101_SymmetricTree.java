package com.BinaryTree3;

public class Leetcode101_SymmetricTree {
	
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
	     * 🔹 Intuition:
	     * - A tree is symmetric if the left subtree is a mirror image of the right subtree.
	     * - That means:
	     *   1. The left and right subtrees should have the **same root value**.
	     *   2. The **left child of the left subtree** should match the **right child of the right subtree**.
	     *   3. The **right child of the left subtree** should match the **left child of the right subtree**.
	     * - This follows **recursive symmetry checking** at every level.
	     */

	    boolean helper(TreeNode left, TreeNode right) {
	        // Base case: If either subtree is null, they should both be null to be symmetric
	        if (left == null || right == null) {
	            return left == right; // Returns true if both are null, false otherwise
	        }

	        // If the values of the left and right nodes are different, they are not symmetric
	        if (left.val != right.val) return false;

	        /**
	         * Recursively check:
	         * - left subtree's left with right subtree's right
	         * - left subtree's right with right subtree's left
	         */
	        return helper(left.left, right.right) && helper(left.right, right.left);
	    }

	    /**
	     * This function checks if the given tree is symmetric.
	     */
	    public boolean isSymmetric(TreeNode root) {
	        // A tree is symmetric if its left and right subtrees are mirror images.
	        return helper(root.left, root.right);
	    }
	}

	/**
	 * 🌟 Dry Run Example:
	 *
	 * Consider the following symmetric tree:
	 * 
	 *         1
	 *        / \
	 *       2   2
	 *      / \ / \
	 *     3  4 4  3
	 *
	 * Step-by-step recursive calls:
	 *
	 * 1. isSymmetric(root) → Calls helper(root.left, root.right)
	 *    - left = 2, right = 2 (equal) ✅
	 *    
	 * 2. helper(2.left, 2.right) and helper(2.right, 2.left)
	 *    - left.left = 3, right.right = 3 (equal) ✅
	 *    - left.right = 4, right.left = 4 (equal) ✅
	 *
	 * 3. Recursion reaches leaf nodes, returns true for all, confirming symmetry ✅
	 *
	 * 🔹 Final Output: **true**
	 *
	 * ---------------------------------------
	 *
	 * Consider an **asymmetric** tree:
	 * 
	 *         1
	 *        / \
	 *       2   2
	 *        \    \
	 *        3     3
	 *
	 * 1. isSymmetric(root) → Calls helper(root.left, root.right)
	 *    - left = 2, right = 2 (equal) ✅
	 *    
	 * 2. helper(2.left, 2.right) and helper(2.right, 2.left)
	 *    - left.left = null, right.right = 3 ❌ (mismatch)
	 *
	 * 🔹 Final Output: **false**
	 */


}
