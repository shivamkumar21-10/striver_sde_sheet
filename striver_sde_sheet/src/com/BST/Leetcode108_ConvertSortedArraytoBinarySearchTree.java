package com.BST;

public class Leetcode108_ConvertSortedArraytoBinarySearchTree {
	
	/**
	 * Definition for a binary tree node.
	 * Represents a node in the binary tree.
	 */
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;

	    // Default constructor
	    TreeNode() {}

	    // Constructor with value
	    TreeNode(int val) {
	        this.val = val;
	    }

	    // Constructor with value, left, and right child nodes
	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}

	class Solution {
	    /**
	     * Recursively constructs a height-balanced BST from a sorted array.
	     *
	     * Intuition:
	     * - A balanced BST ensures that the left and right subtrees have minimal height difference.
	     * - Since the input array is sorted, selecting the middle element as the root ensures balance.
	     * - Recursively applying this strategy to the left and right halves forms the complete BST.
	     *
	     * Dry Run Example:
	     * Given: nums = [-10, -3, 0, 5, 9]
	     * 
	     * 1. Pick middle element (0) as root.
	     * 2. Left half [-10, -3] → pick middle (-3) as left child of 0.
	     * 3. Right half [5, 9] → pick middle (9) as right child of 0.
	     * 4. Continue recursively until the tree is fully built.
	     *
	     * @param nums The sorted input array.
	     * @param l The left index of the current subarray.
	     * @param r The right index of the current subarray.
	     * @return The root of the constructed BST.
	     */
	    TreeNode construct(int[] nums, int l, int r) {
	        // Base case: If left index exceeds right, return null (no node).
	        if (l > r) return null;

	        // Choose the middle element as the root to maintain balance.
	        int mid = (l + r) / 2;
	        TreeNode root = new TreeNode(nums[mid]);

	        // Recursively build the left subtree using left half of array.
	        root.left = construct(nums, l, mid - 1);

	        // Recursively build the right subtree using right half of array.
	        root.right = construct(nums, mid + 1, r);

	        return root; // Return the constructed subtree root.
	    }

	    /**
	     * Converts a sorted array into a height-balanced BST.
	     *
	     * Steps:
	     * 1. Start with the middle element as root.
	     * 2. Recursively assign left and right halves as subtrees.
	     * 3. Return the root of the constructed BST.
	     *
	     * Example:
	     * Input: nums = [-10, -3, 0, 5, 9]
	     * Output: A height-balanced BST with 0 as root.
	     *
	     * @param nums The sorted input array.
	     * @return The root of the constructed BST.
	     */
	    public TreeNode sortedArrayToBST(int[] nums) {
	        return construct(nums, 0, nums.length - 1);
	    }
	}


}
