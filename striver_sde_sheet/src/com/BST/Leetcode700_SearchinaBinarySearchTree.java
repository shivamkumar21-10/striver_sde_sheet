package com.BST;

public class Leetcode700_SearchinaBinarySearchTree {
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
	     * Intuition:
	     * - A Binary Search Tree (BST) maintains a property: 
	     *   - The left subtree contains values smaller than the root.
	     *   - The right subtree contains values larger than the root.
	     * - This allows us to search efficiently:
	     *   - If the target value is smaller than the root, it must be in the left subtree.
	     *   - If the target value is larger, it must be in the right subtree.
	     *   - If the root value matches the target, we return the node.
	     * - The search continues recursively until we either find the value or reach a null node.
	     */
	    public TreeNode searchBST(TreeNode root, int val) {
	        // Base case: If root is null, or if root's value is equal to the target value
	        if(root == null) return null;
	        
	        if(root.val == val){
	            return root; // Found the target node, return it
	        }
	        else if(root.val > val){
	            return searchBST(root.left, val); // Search in the left subtree
	        }
	        else{
	            return searchBST(root.right, val); // Search in the right subtree
	        }
	    }
	}
	
//	****LITTLE SHORT*****
	
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: If root is null, or if root's value is equal to the target value
        if(root == null || root.val == val) return root;

        if(root.val > val){
            return searchBST(root.left, val); // Search in the left subtree
        }
        return searchBST(root.right, val); // Search in the right subtree

    }

	/**
	 * Dry Run:
	 * Let's consider the following BST:
	 * 
	 *         5
	 *        / \
	 *       3   7
	 *      / \   \
	 *     2   4   8
	 *
	 * Example 1: searchBST(root, 4)
	 * 1. root = 5, val = 4 → Go left (3)
	 * 2. root = 3, val = 4 → Go right (4)
	 * 3. root = 4, val = 4 → Found! Return 4.
	 *
	 * Example 2: searchBST(root, 6)
	 * 1. root = 5, val = 6 → Go right (7)
	 * 2. root = 7, val = 6 → Go left (null)
	 * 3. root = null → Not found, return null.
	 *
	 * Observations:
	 * - The search is efficient because we avoid unnecessary branches.
	 * - The recursion depth is at most the height of the tree (O(H)).
	 * - If the tree is balanced, the search takes O(log N) time.
	 * - If the tree is skewed (like a linked list), the worst case is O(N).
	 */

}
