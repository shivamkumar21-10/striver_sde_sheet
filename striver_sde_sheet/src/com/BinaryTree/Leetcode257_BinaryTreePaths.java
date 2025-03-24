package com.BinaryTree;
import java.util.*;
public class Leetcode257_BinaryTreePaths {
	
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
	     * Helper function to traverse the tree and find all root-to-leaf paths.
	     * The function stores each path as a **list of node values**.
	     *
	     * Observations:
	     * 1. Every **root-to-leaf** path must be stored.
	     * 2. We use **recursion** to traverse each branch of the tree.
	     * 3. Since paths change dynamically, **backtracking** ensures correctness.
	     * 4. Once we reach a **leaf node**, we add a copy of the path to results.
	     *
	     * Intuition:
	     * - We perform a **DFS traversal** (preorder) from the root.
	     * - Maintain a `path` list storing nodes along the current path.
	     * - On reaching a leaf node, add the **current path copy** to `res`.
	     * - **Backtrack** to explore other paths before returning to parent node.
	     *
	     * @param root The current node being processed.
	     * @param res  The result list storing all root-to-leaf paths.
	     * @param path The current path from root to this node.
	     */
	    public static void helper(TreeNode root, List<List<Integer>> res, List<Integer> path) {
	        // Base case: If the current node is null, return (invalid path).
	        if (root == null) {
	            return;
	        }

	        // Step 1: Add the current node value to the path list.
	        path.add(root.val);

	        // Step 2: If it's a leaf node, add the path to result and backtrack.
	        if (root.left == null && root.right == null) {
	            res.add(new ArrayList<>(path)); // Store a new copy of path
	        } else {
	            // Step 3: Recursive case - Traverse left subtree.
	            helper(root.left, res, path);
	            
	            // Step 4: Recursive case - Traverse right subtree.
	            helper(root.right, res, path);
	        }

	        // Step 5: Backtracking - Remove the last added element before returning.
	        path.remove(path.size() - 1);
	    }

	    /**
	     * Function to return all root-to-leaf paths in a binary tree as string representations.
	     * - Uses a helper function to generate list-based paths, then converts them to strings.
	     *
	     * @param root The root of the binary tree.
	     * @return A list of strings representing all root-to-leaf paths.
	     */
	    public List<String> binaryTreePaths(TreeNode root) {
	        // List to store all root-to-leaf paths as lists.
	        List<List<Integer>> res = new ArrayList<>();

	        // List to store the current path during traversal.
	        List<Integer> path = new ArrayList<>();

	        // Call the recursive function to generate paths.
	        helper(root, res, path);

	        // List to store the final output paths as string format.
	        List<String> ans = new ArrayList<>();

	        // Convert each list of integers into a string representation "a->b->c".
	        for (List<Integer> next : res) {
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < next.size(); i++) {
	                sb.append(next.get(i));
	                if (i < next.size() - 1) {
	                    sb.append("->");
	                }
	            }
	            ans.add(sb.toString()); // Add the formatted string to the final answer list.
	        }

	        return ans; // Return the list of formatted path strings.
	    }
	}


}
