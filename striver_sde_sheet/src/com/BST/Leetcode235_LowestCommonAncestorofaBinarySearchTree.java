package com.BST;

public class Leetcode235_LowestCommonAncestorofaBinarySearchTree {
	
	/**
	 * Definition for a binary tree node.
	 * Represents a node in the binary tree.
	 */
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;

	    // Constructor to initialize node value
	    TreeNode(int x) {
	        val = x;
	    }
	}

	class Solution {
	    /**
	     * Finds the Lowest Common Ancestor (LCA) of two given nodes in a Binary Search Tree (BST).
	     *
	     * Intuition:
	     * - In a BST, all left child nodes have values smaller than their parent,
	     *   and all right child nodes have values greater than their parent.
	     * - The LCA is the lowest node in the tree that has both p and q as descendants.
	     * - If both p and q are smaller than root, LCA lies in the left subtree.
	     * - If both p and q are greater than root, LCA lies in the right subtree.
	     * - Otherwise, the root itself is the LCA.
	     *
	     * Dry Run Example:
	     * Given BST:
	     *          6
	     *        /   \
	     *       2     8
	     *      / \   / \
	     *     0   4 7   9
	     *        / \
	     *       3   5
	     *
	     * p = 2, q = 8
	     * - Root is 6, which is between 2 and 8 → LCA is 6.
	     *
	     * p = 2, q = 4
	     * - Both 2 and 4 are in the left subtree of 6, move left.
	     * - Root is now 2, which is one of the nodes → LCA is 2.
	     *
	     * @param root The root of the BST.
	     * @param p First node for which LCA is to be found.
	     * @param q Second node for which LCA is to be found.
	     * @return The Lowest Common Ancestor node.
	     *
	     * Time Complexity: O(log N) in a balanced BST, as we eliminate half of the tree at each step.
	     *                 O(N) in the worst case for a skewed BST (like a linked list).
	     * Space Complexity: O(1) for the iterative approach, O(H) for the recursive approach (due to function call stack),
	     *                   where H is the height of the tree.
	     */
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        // Base case: if root is null, return null (tree is empty)
	        if (root == null) {
	            return root;
	        }

	        // If both p and q are greater than root, LCA must be in the right subtree
	        if (root.val < p.val && root.val < q.val) {
	            return lowestCommonAncestor(root.right, p, q);
	        }

	        // If both p and q are smaller than root, LCA must be in the left subtree
	        if (root.val > p.val && root.val > q.val) {
	            return lowestCommonAncestor(root.left, p, q);
	        }

	        // If one node is on the left and the other on the right (or one is equal to root), root is the LCA
	        return root;
	    }
	}


}
