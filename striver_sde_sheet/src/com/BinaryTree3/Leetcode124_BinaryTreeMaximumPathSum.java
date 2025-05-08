package com.BinaryTree3;

public class Leetcode124_BinaryTreeMaximumPathSum {
	
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
	     * Helper function to compute the maximum path sum from any node downward.
	     * 
	     * @param root The current node being processed.
	     * @param maxi An array with a single element, used to keep track of the maximum path sum found so far.
	     * @return The maximum path sum from this node to any of its descendant nodes.
	     */
	    public int maxPathDown(TreeNode root, int[] maxi) {
	        // Base case: If the node is null, return 0 (a null node contributes nothing to the sum)
	        if (root == null) return 0;

	        // Recursively calculate the maximum path sum of the left and right subtrees.
	        // If the sum is negative, we ignore that path (hence, Math.max(0, ...))
	        int left = Math.max(0, maxPathDown(root.left, maxi));  // Compute max path sum from left child
	        int right = Math.max(0, maxPathDown(root.right, maxi)); // Compute max path sum from right child

	        // Update the global maximum path sum by considering the sum passing through the current node.
	        // This includes the current node's value plus the maximum sum from both left and right subtrees.
	        maxi[0] = Math.max(maxi[0], left + right + root.val);

	        // Return the maximum sum obtained from either the left or right subtree, adding the current node's value.
	        // This is because a path in a binary tree is a sequence of nodes where we can either:
	        //   1. Continue the path from the parent to the child (left or right).
	        //   2. End the path at a certain node (not going further).
	        // We return only one of the paths (either left or right) since we cannot include both paths in a valid path sum.
	        return Math.max(left, right) + root.val;
	    }

	    /**
	     * Function to compute the maximum path sum of a binary tree.
	     * 
	     * A path is any sequence of nodes from some starting node to any node in the tree
	     * following parent-child connections. The path must contain at least one node.
	     * 
	     * @param root The root node of the binary tree.
	     * @return The maximum path sum found in the tree.
	     */
	    public int maxPathSum(TreeNode root) {
	        // Initialize an array with one element to store the maximum path sum.
	        // We use an array instead of an integer so that we can update it inside the recursive function.
	        int[] maxi = new int[1];

	        // We initialize maxi[0] with root's value to ensure at least one node is considered.
	        maxi[0] = root.val;

	        // Call the helper function to start computing the maximum path sum.
	        maxPathDown(root, maxi);

	        // Return the maximum path sum found.
	        return maxi[0];
	    }
	}

	/**
	 * 🔍 DRY RUN EXAMPLE:
	 * 
	 * Given Tree:
	 *        -10
	 *        /  \
	 *       9   20
	 *          /  \
	 *         15   7
	 * 
	 * Step-by-step execution:
	 * 1. Start at root (-10), recursively compute max path sum for left and right subtrees.
	 * 2. Left subtree (9): 
	 *    - Left and right children are null, so it returns 9.
	 * 3. Right subtree (20):
	 *    - Left child (15): Both left and right are null, so it returns 15.
	 *    - Right child (7): Both left and right are null, so it returns 7.
	 *    - Max path sum including node 20: 15 + 7 + 20 = 42. This updates `maxi[0]`.
	 *    - Node 20 returns max(15,7) + 20 = 35.
	 * 4. Root (-10) computes: max(9,35) + (-10) = 25.
	 * 5. Answer is `maxi[0] = 42`.
	 * 
	 * Output: 42
	 */


}
