package com.BinaryTree2;

public class Leetcode236_LowestCommonAncestorofaBinaryTree {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */

	class Solution {
	    /**
	     * Function to find the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
	     * 
	     * - The LCA of two nodes p and q is the lowest node in the tree that has both p and q as descendants.
	     * - If either p or q is the root, then the root is the LCA.
	     * - We recursively search in the left and right subtrees.
	     * - If both left and right return non-null, the current node is the LCA.
	     * - If only one subtree has p or q, return that subtree's result.
	     * 
	     * Time Complexity: O(N), where N is the number of nodes in the tree.
	     * Space Complexity: O(H), where H is the height of the tree (recursive call stack).
	     */
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        // Base case: If root is null, or if we found one of the target nodes, return root
	        if (root == null || root == p || root == q) {
	            return root;
	        }

	        // Recursive search in the left subtree
	        TreeNode left = lowestCommonAncestor(root.left, p, q);

	        // Recursive search in the right subtree
	        TreeNode right = lowestCommonAncestor(root.right, p, q);

	        // Case 1: If both left and right are non-null, it means p is found in one subtree and q in the other.
	        // Thus, root is their Lowest Common Ancestor.
	        if (left != null && right != null) {
	            return root;
	        }

	        // Case 2: If left is null, return right (either q or p is found in the right subtree)
	        if (left == null) {
	            return right;
	        }

	        // Case 3: If right is null, return left (either q or p is found in the left subtree)
	        return left;
	    }
	}

	/**
	 * Dry Run Example:
	 * 
	 * Given Tree:
	 *         3
	 *        / \
	 *       5   1
	 *      / \  / \
	 *     6   2 0  8
	 *        / \
	 *       7   4
	 * 
	 * Input: p = 5, q = 1
	 * 
	 * Step-by-step execution:
	 * 1. Start at root (3). It is not p or q, so search in left and right subtrees.
	 * 2. Left subtree (5) contains p.
	 * 3. Right subtree (1) contains q.
	 * 4. Since we found p in left and q in right, return root (3) as LCA.
	 * 
	 * Output: 3
	 * 
	 * Another Example:
	 * Input: p = 5, q = 4
	 * 
	 * Step-by-step execution:
	 * 1. Start at root (3). Not p or q, search left and right.
	 * 2. Left subtree (5) is p. Search in its left and right.
	 * 3. Right child (2) contains q (4).
	 * 4. Since p is found at (5) and q is in its subtree, return (5) as LCA.
	 * 
	 * Output: 5
	 */


}
