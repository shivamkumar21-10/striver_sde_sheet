package com.BST2;

public class Leetcode1373_MaximumSumBSTinBinaryTree {
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

	// Helper class to hold three values for each subtree:
	// maxValue -> the maximum node value in the current subtree (used to validate BST condition)
	// minValue -> the minimum node value in the current subtree (used to validate BST condition)
	// maxsum   -> the total sum of values of current subtree if it's a valid BST
	class NodeValue {
	    int maxValue;
	    int minValue;
	    int maxsum;

	    // Constructor to initialize the fields
	    NodeValue(int max, int min, int maxsum){
	        this.maxValue = max;
	        this.minValue = min;
	        this.maxsum = maxsum;
	    }
	}

	class Solution {

	    // Global variable to keep track of maximum sum among all BST subtrees
	    int max = 0;

	    // Main helper function to recursively check subtrees
	    public NodeValue maxSumBSTHelper(TreeNode root){

	        // 🔍 Base case: if node is null, it's a valid BST with sum 0
	        // Also, to not affect parent BST validation, return extreme min/max values
	        if(root == null){
	            return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
	        }

	        // ✅ Post-order traversal: process left and right first
	        NodeValue left = maxSumBSTHelper(root.left);
	        NodeValue right = maxSumBSTHelper(root.right);

	        // 🧠 Logic:
	        // If the current root satisfies BST condition:
	        // i.e., all left subtree nodes < root.val < all right subtree nodes
	        if(left.maxValue < root.val && root.val < right.minValue){

	            // 🧮 Current subtree is a valid BST, calculate total sum
	            int currentSum = root.val + left.maxsum + right.maxsum;

	            // ✅ Update global max sum if currentSum is greater
	            max = Math.max(max, currentSum);

	            // 🧾 Return updated values: maxValue, minValue and sum for parent processing
	            return new NodeValue(
	                Math.max(root.val, right.maxValue),  // update max value seen so far
	                Math.min(root.val, left.minValue),   // update min value seen so far
	                currentSum                           // sum of current BST
	            );
	        }

	        // ❌ If BST condition fails, we cannot consider this subtree as a BST
	        // We return extreme values to ensure parent cannot include this subtree in BST
	        // But we still return the max sum from left or right to continue tracking global max
	        return new NodeValue(
	            Integer.MAX_VALUE, Integer.MIN_VALUE,  // Invert values to fail BST check upward
	            Math.max(left.maxsum, right.maxsum)    // Pick best possible valid BST sum below
	        );
	    }

	    // Function to be called by user to get maximum BST sum
	    public int maxSumBST(TreeNode root) {
	        // 🚀 Start postorder traversal from root
	        maxSumBSTHelper(root);

	        // 📦 Return final result (max sum of all valid BSTs in tree)
	        return max;
	    }

	    /*
	    🔍 Dry Run:
	    Tree:
	           5
	          / \
	         3   8
	        / \   \
	       2   4   9

	    - Subtree rooted at 3 → valid BST, sum = 3+2+4 = 9
	    - Subtree rooted at 8 → valid BST, sum = 8+9 = 17
	    - Entire tree is BST → sum = 5+3+8+2+4+9 = 31 → max = 31

	    ⏱ Time Complexity: O(N)
	    - Each node is visited exactly once.

	    🧠 Space Complexity: O(H)
	    - H = height of tree = logN (balanced) or N (skewed)
	    - Used by recursion stack.
	    */
	}


}
