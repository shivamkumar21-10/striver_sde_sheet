package com.BST;

public class Leetcode98_ValidateBinarySearchTree {
	
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
	     * Function to validate whether a given binary tree is a Binary Search Tree (BST).
	     * 
	     * Intuition:
	     * -----------
	     * A Binary Search Tree (BST) must follow these properties:
	     * 1. The left subtree of a node contains only nodes with values **less than** the node's value.
	     * 2. The right subtree of a node contains only nodes with values **greater than** the node's value.
	     * 3. Both the left and right subtrees must also be valid BSTs.
	     * 
	     * To ensure the BST property holds for all nodes, we use a **recursive approach** that checks:
	     * - If the current node's value is **within the valid range** (`min < root.val < max`).
	     * - Recursively check the left subtree with an **updated max constraint** (root.val).
	     * - Recursively check the right subtree with an **updated min constraint** (root.val).
	     * 
	     * Edge Cases Considered:
	     * ----------------------
	     * - An **empty tree** (`root == null`) is considered a valid BST.
	     * - A **single node** tree is always valid.
	     * - Trees with duplicate values are **not valid BSTs** (we use `root.val > min && root.val < max`).
	     * 
	     * Dry Run:
	     * --------
	     * Example 1: Valid BST
	     *         5
	     *        / \
	     *       3   7
	     *      / \  / \
	     *     2  4 6  8
	     * 
	     * - `validate(root=5, min=-∞, max=∞)` ✅ (5 is valid)
	     * - `validate(root=3, min=-∞, max=5)` ✅ (3 is valid)
	     * - `validate(root=7, min=5, max=∞)` ✅ (7 is valid)
	     * - `validate(root=2, min=-∞, max=3)` ✅ (2 is valid)
	     * - `validate(root=4, min=3, max=5)` ✅ (4 is valid)
	     * - `validate(root=6, min=5, max=7)` ✅ (6 is valid)
	     * - `validate(root=8, min=7, max=∞)` ✅ (8 is valid)
	     * - ✅ Final Result: **Valid BST**
	     * 
	     * Example 2: Invalid BST
	     *         5
	     *        / \
	     *       3   7
	     *      / \  / \
	     *     2  6 6  8  (6 on the left subtree of 5 violates BST property)
	     * 
	     * - `validate(root=5, min=-∞, max=∞)` ✅ (5 is valid)
	     * - `validate(root=3, min=-∞, max=5)` ✅ (3 is valid)
	     * - `validate(root=7, min=5, max=∞)` ✅ (7 is valid)
	     * - `validate(root=2, min=-∞, max=3)` ✅ (2 is valid)
	     * - `validate(root=6, min=3, max=5)` ❌ **(6 violates BST rule)**
	     * - ❌ Final Result: **Not a BST**
	     * 
	     * Complexity Analysis:
	     * -------------------
	     * - **Time Complexity: O(N)** - We visit each node exactly once.
	     * - **Space Complexity: O(N)** - In the worst case (skewed tree), recursion depth is N.
	     */

	    private boolean validate(TreeNode root, long min, long max) {
	        // Base case: An empty tree is always a valid BST
	        if (root == null) return true; 
	        
	        // If the current node's value violates the BST property, return false
	        if (root.val <= min || root.val >= max) return false;
	        
	        // Recursively validate the left subtree (all values must be < root.val)
	        // Recursively validate the right subtree (all values must be > root.val)
	        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
	    }

	    /**
	     * Public function to check if a given binary tree is a valid BST.
	     * @param root The root node of the tree.
	     * @return True if the tree is a BST, otherwise false.
	     */
	    public boolean isValidBST(TreeNode root) {
	        // Start validation with the full integer range
	        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
	    }
	}
	
//	Observations
//	1.Recursive Approach Ensures Global BST Property:
//
//	Instead of just comparing immediate left/right children, we enforce constraints across the entire tree.
//	
//	2.Using Long.MIN_VALUE and Long.MAX_VALUE:
//
//	Ensures values don’t go out of Integer bounds when checking edge cases.


}
