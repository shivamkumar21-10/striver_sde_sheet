package com.BinaryTree;

import java.util.*;

public class Leetcode94_BinaryTreeInorderTraversal {
	

	class Solution {
	    public List<List<Integer>> levelOrderBottom(TreeNode root) {
	        // Result list to store the levels in reverse order
	        List<List<Integer>> ans = new ArrayList<>();

	        // Stack to reverse the order of levels (bottom to top)
	        Stack<List<Integer>> stk = new Stack<>();

	        // Queue to perform level-order traversal (breadth-first search)
	        Queue<TreeNode> q = new LinkedList<>();

	        // If the root is null, return an empty list (no tree to traverse)
	        if (root == null) return ans;

	        // Add the root node to the queue as the starting point
	        q.add(root);

	        // Add a null marker to indicate the end of the first level
	        q.add(null);

	        // Temporary list to store values of the current level
	        List<Integer> tempAns = new ArrayList<>();

	        // Perform level-order traversal
	        while (!q.isEmpty()) {
	            // Get the front node from the queue
	            TreeNode temp = q.peek();
	            q.poll();

	            // If the node is null, we've reached the end of the current level
	            if (temp == null) {
	                // Push the current level's values onto the stack
	                stk.push(new ArrayList<>(tempAns));

	                // Clear the temporary list for the next level
	                tempAns.clear();

	                // If the queue is not empty, add another null marker for the next level
	                if (!q.isEmpty()) {
	                    q.add(null);
	                }
	            } else {
	                // Add the current node's value to the temporary list
	                tempAns.add(temp.val);

	                // Add the left child of the current node to the queue (if it exists)
	                if (temp.left != null) {
	                    q.add(temp.left);
	                }

	                // Add the right child of the current node to the queue (if it exists)
	                if (temp.right != null) {
	                    q.add(temp.right);
	                }
	            }
	        }

	        // Pop each level from the stack and add it to the result list
	        while (!stk.isEmpty()) {
	            ans.add(stk.pop());
	        }

	        // Return the reversed level-order traversal
	        return ans;
	    }
	}


}
