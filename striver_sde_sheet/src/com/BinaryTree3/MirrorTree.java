package com.BinaryTree3;

public class MirrorTree {
	
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
	     * - A **mirror tree** is a tree where the **left and right subtrees** of every node are swapped.
	     * - We perform **Depth-First Traversal (DFS)** and swap left and right subtrees **recursively**.
	     * - This ensures that **all nodes** in the tree are mirrored correctly.
	     * - The function modifies the tree **in-place**, meaning no extra space is needed.
	     *
	     * 🔹 Approach:
	     * 1. If the root is null, return null (base case).
	     * 2. Swap the left and right child nodes of the current root.
	     * 3. Recursively call the function on the left and right subtrees.
	     * 4. Return the modified root node.
	     *
	     * 🔹 Dry Run Example:
	     * Given Input Tree:
	     *         1
	     *       /   \
	     *      2     3
	     *     / \     \
	     *    4   5     6
	     *
	     * Step-by-step execution:
	     * - `mirrorTree(1)`: Swap `left=2`, `right=3`
	     * - `mirrorTree(3)`: Swap `left=null`, `right=6`
	     * - `mirrorTree(6)`: No children, return.
	     * - `mirrorTree(2)`: Swap `left=4`, `right=5`
	     * - `mirrorTree(4)`, `mirrorTree(5)`: No children, return.
	     *
	     * Output Mirrored Tree:
	     *         1
	     *       /   \
	     *      3     2
	     *     /     / \
	     *    6     5   4
	     *
	     * 🔹 Time Complexity: **O(N)** (Each node is visited once)
	     * 🔹 Space Complexity: **O(H)** (Recursive stack, H = tree height)
	     */
	    public TreeNode mirrorTree(TreeNode root) {
	        // Base Case: If root is null, return null (Nothing to mirror)
	        if (root == null) return null;

	        // Swap left and right children
	        TreeNode temp = root.left;
	        root.left = root.right;
	        root.right = temp;

	        // Recursively mirror left and right subtrees
	        mirrorTree(root.left);
	        mirrorTree(root.right);

	        return root; // Return the root of the mirrored tree
	    }
	}


}
