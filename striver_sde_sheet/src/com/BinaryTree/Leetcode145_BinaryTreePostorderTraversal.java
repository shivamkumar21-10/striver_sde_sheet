package com.BinaryTree;
import java.util.*;

public class Leetcode145_BinaryTreePostorderTraversal {
	

	class Solution {

	    /**
	     * Helper method to perform a recursive postorder traversal of the binary tree.
	     * In postorder traversal, nodes are visited in the order:
	     * - Left Subtree
	     * - Right Subtree
	     * - Root
	     * 
	     * @param root The current node being processed.
	     * @param ans  The list where the values are collected in postorder sequence.
	     * @return The list containing the postorder traversal of the binary tree.
	     */
	    public List<Integer> solve(TreeNode root, List<Integer> ans) {
	        // Base case: If the current node is null, return the list as it is.
	        if (root == null) {
	            return ans;
	        }
	        
	        // Recursive call to process the left subtree.
	        solve(root.left, ans);
	        
	        // Recursive call to process the right subtree.
	        solve(root.right, ans);
	        
	        // Add the current node's value to the list after processing its children.
	        // In postorder traversal, the root is visited last.
	        ans.add(root.val);

	        // Return the list after processing the current subtree.
	        return ans;
	    }

	    /**
	     * Entry point to perform a postorder traversal of the binary tree.
	     * 
	     * @param root The root of the binary tree.
	     * @return A list containing the postorder traversal of the binary tree.
	     */
	    public List<Integer> postorderTraversal(TreeNode root) {
	        // Initialize an empty list to store the traversal result.
	        List<Integer> ans = new ArrayList<>();

	        // Start the recursive postorder traversal from the root and return the result.
	        return solve(root, ans);
	    }
	}


}
