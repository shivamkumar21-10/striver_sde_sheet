package com.BinaryTree3;

public class Leetcode2236_RootEqualsSumofChildren {
	
	class Solution
	{
	    /**
	     * Function to check whether all nodes of a tree have the value 
	     * equal to the sum of their child nodes.
	     * 
	     * 🔹 Intuition:
	     * - A node satisfies the sum property if:
	     *   - It is a **leaf node** (trivially satisfies the property)
	     *   - `root.data == root.left.data + root.right.data`
	     * - We **recursively** check the sum property for every node.
	     * - If any node fails this condition, return `0` (false).
	     * - Otherwise, return `1` (true).
	     * 
	     * 🔹 Observations:
	     * - Leaf nodes are always valid.
	     * - If a node has **only one child**, treat the missing child as having value `0`.
	     * - The check must apply to **all** nodes in the tree.
	     * 
	     * 🔹 Dry Run:
	     * Given Tree:
	     *         10
	     *        /  \
	     *       3    7
	     *      / \   / \
	     *     1   2 3   4
	     * 
	     * Step-by-step execution:
	     * - `10 == 3 + 7` ✅ (valid)
	     * - `3 == 1 + 2` ✅ (valid)
	     * - `7 == 3 + 4` ✅ (valid)
	     * - `1, 2, 3, 4` are leaf nodes, so they are trivially valid ✅
	     * - All nodes satisfy the property → return `1` ✅
	     * 
	     * 🔹 Time Complexity: **O(N)** (Each node is visited once)
	     * 🔹 Space Complexity: **O(H)** (Recursive stack, H = height of tree)
	     */
	    public static boolean checkTree(TreeNode root)
	    {
	        // Base Case 1: If the tree is empty, it satisfies the property
	        if(root == null) {
	            return true;
	        }

	        // Base Case 2: If the node is a leaf node, it is valid (no children to sum)
	        if(root.left == null && root.right == null) {
	            return true;
	        }
	        
	        // Get the sum of child nodes safely (handle null cases)
	        int leftData = (root.left != null) ? root.left.val : 0;
	        int rightData = (root.right != null) ? root.right.val : 0;
	        
	        // Step 1: Check if current node satisfies sum property
	        if(leftData + rightData != root.val) {
	            return false;  // Property fails, return false (0)
	        }
	        
	        // Step 2: Recursively check for left and right subtrees
	        boolean left = checkTree(root.left);
	        boolean right = checkTree(root.right);
	        
	        // Step 3: If any subtree fails, return 0 (false)
	        return left && right;
	    }
	}


}
