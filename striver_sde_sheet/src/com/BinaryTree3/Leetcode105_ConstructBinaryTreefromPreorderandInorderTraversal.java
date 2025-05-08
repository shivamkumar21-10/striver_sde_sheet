package com.BinaryTree3;

import java.util.HashMap;
import java.util.Map;

public class Leetcode105_ConstructBinaryTreefromPreorderandInorderTraversal {
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
	     * This function recursively constructs the Binary Tree from given preorder and inorder traversals.
	     * 
	     * @param preorder - The preorder traversal array of the tree
	     * @param preStart - The starting index of the preorder array
	     * @param preEnd - The ending index of the preorder array
	     * @param inorder - The inorder traversal array of the tree
	     * @param inStart - The starting index of the inorder array
	     * @param inEnd - The ending index of the inorder array
	     * @param inMap - A HashMap that maps node values to their corresponding index in inorder array
	     * 
	     * @return The root of the constructed tree
	     * 
	     * Intuition:
	     * - In **Preorder traversal**, the first element is always the **root**.
	     * - In **Inorder traversal**, elements **left of root** are in the left subtree, 
	     *   and elements **right of root** are in the right subtree.
	     * - We use **recursion** to divide the array into smaller parts and construct the tree.
	     */
	    TreeNode construct(int[] preorder, int preStart, int preEnd, 
	                       int[] inorder, int inStart, int inEnd, 
	                       Map<Integer, Integer> inMap) {
	        
	        // Base condition: If the start index is greater than the end index, return null (no tree).
	        if (preStart > preEnd || inStart > inEnd) return null;

	        // Step 1: Get the root node from the current segment of preorder traversal
	        TreeNode root = new TreeNode(preorder[preStart]);

	        // Step 2: Find the root's index in inorder traversal
	        int inRoot = inMap.get(root.val);

	        // Step 3: Calculate the number of nodes in the left subtree
	        int numsLeft = inRoot - inStart;

	        // Recursive call to construct the left subtree
	        // Left subtree elements in preorder: (preStart+1 to preStart+numsLeft)
	        // Left subtree elements in inorder: (inStart to inRoot-1)
	        root.left = construct(preorder, preStart + 1, preStart + numsLeft, 
	                              inorder, inStart, inRoot - 1, inMap);

	        // Recursive call to construct the right subtree
	        // Right subtree elements in preorder: (preStart+numsLeft+1 to preEnd)
	        // Right subtree elements in inorder: (inRoot+1 to inEnd)
	        root.right = construct(preorder, preStart + numsLeft + 1, preEnd, 
	                               inorder, inRoot + 1, inEnd, inMap);

	        // Returning the constructed root node
	        return root;
	    }

	    /**
	     * This function is the main function that constructs the Binary Tree from Preorder and Inorder traversals.
	     * 
	     * @param preorder - The preorder traversal array
	     * @param inorder - The inorder traversal array
	     * 
	     * @return The root of the constructed Binary Tree
	     * 
	     * Logic:
	     * - First, we store the indices of inorder elements in a HashMap for quick lookup.
	     * - Then, we call the helper function 'construct()' to build the tree.
	     */
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	        // Step 1: Create a HashMap to store value-to-index mappings for inorder traversal.
	        Map<Integer, Integer> map = new HashMap<>();

	        // Store each node's value and its index in inorder traversal
	        for (int i = 0; i < inorder.length; i++) {
	            map.put(inorder[i], i);
	        }

	        // Step 2: Start constructing the tree using the recursive function.
	        TreeNode root = construct(preorder, 0, preorder.length - 1,
	                                  inorder, 0, inorder.length - 1, map);

	        // Return the final constructed tree
	        return root;
	    }
	}
	
	 /*
     * 🔍 Dry Run Example:
     * ===================
     * Given Input:
     * Preorder = [3, 9, 20, 15, 7]
     * Inorder  = [9, 3, 15, 20, 7]
     * 
     * Step-by-step Execution:
     * -----------------------
     * 1. First Call (preorder[0] = 3)
     *    - Root = 3 (from preorder)
     *    - Found at index 1 in inorder.
     *    - Left subtree size = 1 (inorder[0] = 9)
     *    - Recursively build left subtree from Preorder[1] (9) and right subtree from Preorder[2] (20).
     *
     * 2. Left Subtree (Preorder = [9]):
     *    - Root = 9 (preorder[1])
     *    - Found at index 0 in inorder.
     *    - No left or right subtree.
     *    - Return 9 as left child of 3.
     *
     * 3. Right Subtree (Preorder = [20, 15, 7]):
     *    - Root = 20 (preorder[2])
     *    - Found at index 3 in inorder.
     *    - Left subtree size = 1 (inorder[2] = 15)
     *    - Recursively build left subtree from Preorder[3] (15) and right subtree from Preorder[4] (7).
     *
     * 4. Left Subtree of 20 (Preorder = [15]):
     *    - Root = 15 (preorder[3])
     *    - Found at index 2 in inorder.
     *    - No left or right subtree.
     *    - Return 15 as left child of 20.
     *
     * 5. Right Subtree of 20 (Preorder = [7]):
     *    - Root = 7 (preorder[4])
     *    - Found at index 4 in inorder.
     *    - No left or right subtree.
     *    - Return 7 as right child of 20.
     *
     * Final Tree Structure:
     * ---------------------
     *         3
     *        / \
     *       9   20
     *          /  \
     *         15   7
     *
     * Time Complexity: O(n) → Each node is visited once.
     * Space Complexity: O(n) → Storing values in HashMap.
     */

}
