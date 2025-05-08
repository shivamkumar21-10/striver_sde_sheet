package com.BinaryTree3;

public class Leetcode114_FlattenBinaryTreetoLinkedList {
	
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
	    TreeNode prev = null; // 'prev' will keep track of the previously visited node in reverse preorder

	    public void flatten(TreeNode root) {
	        // Base case: if the root is null, there's nothing to flatten
	        if (root == null) return;

	        // Step 1: Recurse on the right subtree first (important for reverse preorder traversal: root -> right -> left)
	        flatten(root.right);

	        // Step 2: Recurse on the left subtree
	        flatten(root.left);

	        // Step 3: At this point, both left and right subtrees of 'root' have been flattened.
	        // Now we modify the current node:
	        
	        root.right = prev;  // Set the right child to previously visited node
	        root.left = null;   // Set left child to null to match "linked list" format
	        prev = root;        // Move the 'prev' pointer to current root (for the next node in reverse preorder)

	        /**
	         * Dry Run Example:
	         * Consider Tree:
	         *       1
	         *      / \
	         *     2   5
	         *    / \   \
	         *   3   4   6
	         *
	         * Reverse preorder traversal: 6 → 5 → 4 → 3 → 2 → 1
	         *
	         * Step by step, each node's right is set to the previously visited node (prev)
	         * and left is set to null.
	         *
	         * Final flattened list: 1 → 2 → 3 → 4 → 5 → 6 (right-skewed)
	         */
	    }
	}


}
