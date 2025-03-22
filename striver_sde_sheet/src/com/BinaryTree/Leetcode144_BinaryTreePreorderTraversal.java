package com.BinaryTree;
import java.util.*;
public class Leetcode144_BinaryTreePreorderTraversal {

	class Solution {

	    /**
	     * Helper method to recursively perform a preorder traversal of the binary tree.
	     * In a preorder traversal, nodes are visited in the order:
	     * - Root
	     * - Left Subtree
	     * - Right Subtree
	     * 
	     * @param root The current node being processed.
	     * @param ans  The list where the values are collected in preorder sequence.
	     * @return The list containing the preorder traversal of the binary tree.
	     */
	    public List<Integer> solve(TreeNode root, List<Integer> ans) {
	        // Base case: If the current node is null, return the list as it is.
	        if (root == null) {
	            return ans;
	        }

	        // Add the current node's value to the list.
	        // In preorder traversal, the root is visited before its left and right subtrees.
	        ans.add(root.val);

	        // Recursive call to process the left subtree.
	        solve(root.left, ans);

	        // Recursive call to process the right subtree.
	        solve(root.right, ans);

	        // Return the list after processing the current subtree.
	        return ans;
	    }

	    /**
	     * Entry point to perform a preorder traversal of the binary tree.
	     * 
	     * @param root The root of the binary tree.
	     * @return A list containing the preorder traversal of the binary tree.
	     */
	    public List<Integer> preorderTraversal(TreeNode root) {
	        // Initialize an empty list to store the traversal result.
	        List<Integer> ans = new ArrayList<>();

	        // Start the recursive preorder traversal from the root and return the result.
	        return solve(root, ans);
	    }
	}


}
