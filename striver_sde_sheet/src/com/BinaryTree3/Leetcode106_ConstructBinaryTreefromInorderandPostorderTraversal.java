package com.BinaryTree3;

import java.util.*;

public class Leetcode106_ConstructBinaryTreefromInorderandPostorderTraversal {
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
	     * This function recursively constructs a binary tree using inorder and postorder traversal arrays.
	     *
	     * Intuition:
	     * - We know that in **postorder traversal**, the last element represents the **root** of the tree.
	     * - Using this root, we can find its position in the **inorder array**.
	     * - Elements **before** this position in inorder belong to the **left subtree**.
	     * - Elements **after** this position in inorder belong to the **right subtree**.
	     * - We recursively apply this logic to construct left and right subtrees.
	     * - Using a HashMap (`inMap`) to store inorder indices allows **O(1) lookup**, improving efficiency.
	     */
	    public TreeNode construct(int[] postorder, int postStart, int postEnd, 
	                              int[] inorder, int inStart, int inEnd, 
	                              Map<Integer, Integer> inMap) {

	        // Base Case: If postorder start index > end index OR inorder start index > end index
	        // This means we have exhausted the range, so return null (no subtree can be formed).
	        if (postStart > postEnd || inStart > inEnd) return null;

	        // Step 1: Get the root node from the last element in postorder array
	        TreeNode root = new TreeNode(postorder[postEnd]);

	        // Step 2: Find the index of this root in the inorder array (from precomputed HashMap)
	        int inRoot = inMap.get(root.val);

	        // Step 3: Calculate the number of nodes in the left subtree
	        int numsLeft = inRoot - inStart; 

	        /**
	         * Step 4: Recursively build the left and right subtrees
	         * 
	         * Left Subtree:
	         * - Postorder range: postStart to (postStart + numsLeft - 1)
	         * - Inorder range: inStart to (inRoot - 1)
	         */
	        root.left = construct(postorder, postStart, postStart + numsLeft - 1, 
	                              inorder, inStart, inRoot - 1, inMap);

	        /**
	         * Right Subtree:
	         * - Postorder range: (postStart + numsLeft) to (postEnd - 1)
	         * - Inorder range: (inRoot + 1) to inEnd
	         */
	        root.right = construct(postorder, postStart + numsLeft, postEnd - 1, 
	                               inorder, inRoot + 1, inEnd, inMap);

	        // Finally, return the constructed root node
	        return root;
	    }

	    /**
	     * Builds a binary tree from inorder and postorder traversal arrays.
	     */
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        // Edge Case: If the input arrays are empty or null, return null
	        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
	            return null;
	        }

	        // Step 1: Create a HashMap to store inorder values with their indices for quick lookup
	        Map<Integer, Integer> map = new HashMap<>();

	        // Populate HashMap with inorder values and their respective indices
	        for (int i = 0; i < inorder.length; i++) {
	            map.put(inorder[i], i);
	        }

	        // Step 2: Call the helper function to construct the tree and return the root node
	        return construct(postorder, 0, postorder.length - 1, 
	                         inorder, 0, inorder.length - 1, map);
	    }
	}

	/**
	 * 🌟 Dry Run Example:
	 * Given:
	 * inorder =   [9, 3, 15, 20, 7]
	 * postorder = [9, 15, 7, 20, 3]
	 *
	 * Step-by-step execution:
	 * 
	 * 1. Root is the last element in postorder -> 3
	 *    - Find 3 in inorder -> index 1
	 *    - Left subtree = [9]
	 *    - Right subtree = [15, 20, 7]
	 *
	 * 2. Construct left subtree:
	 *    - Root = 9 (last element in left postorder subarray)
	 *    - No left/right subtree (leaf node)
	 *
	 * 3. Construct right subtree:
	 *    - Root = 20 (last element in right postorder subarray)
	 *    - Find 20 in inorder -> index 3
	 *    - Left subtree = [15]
	 *    - Right subtree = [7]
	 *
	 * 4. Construct left of 20:
	 *    - Root = 15 (leaf node)
	 *
	 * 5. Construct right of 20:
	 *    - Root = 7 (leaf node)
	 *
	 * 🔹 Final Tree:
	 *          3
	 *         / \
	 *        9  20
	 *          /  \
	 *         15   7
	 *
	 */

}
