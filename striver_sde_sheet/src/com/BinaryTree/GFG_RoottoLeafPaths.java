package com.BinaryTree;
import java.util.*;
public class GFG_RoottoLeafPaths {
	
	class Solution {
	    /**
	     * Recursive helper function to find all root-to-leaf paths in the given binary tree.
	     * This function explores each path from the root to every leaf node and stores the paths.
	     *
	     * Observations:
	     * 1. We need to **store all paths** from root to leaf.
	     * 2. We use **recursion** to traverse the tree depth-first.
	     * 3. Since paths are stored dynamically, we use **backtracking** to remove added elements after recursion.
	     * 4. When reaching a leaf node, we **save a new copy** of the path to avoid unintended modifications.
	     *
	     * Intuition:
	     * - The idea is to explore every path from root to leaf.
	     * - We keep adding node values to the path as we move down.
	     * - Once we hit a **leaf node**, we add that path to the result list.
	     * - Before returning to the previous call, we **remove** the last added element (backtracking).
	     *
	     * Dry Run Example:
	     * Given the tree:
	     *          1
	     *         / \
	     *        2   3
	     *       / \
	     *      4   5
	     * Expected Output: [[1, 2, 4], [1, 2, 5], [1, 3]]
	     * Steps:
	     * - Start from 1 → Add [1]
	     * - Go left to 2 → Add [1, 2]
	     * - Go left to 4 → Add [1, 2, 4] (Leaf node, store and backtrack)
	     * - Backtrack to 2 → Remove 4 → Try right to 5
	     * - Go right to 5 → Add [1, 2, 5] (Leaf node, store and backtrack)
	     * - Backtrack to 1 → Remove 2 → Try right to 3
	     * - Go right to 3 → Add [1, 3] (Leaf node, store and backtrack)
	     * - Done
	     *
	     * @param root The current node in the binary tree.
	     * @param res  The list that stores all root-to-leaf paths.
	     * @param path The current path from the root to this node.
	     */
	    public static void helper(Node root, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path) {
	        // Base Case: If the current node is null, return immediately (invalid path).
	        if (root == null) {
	            return;
	        }

	        // Step 1: Add the current node's value to the path list.
	        // This represents the current path as we traverse the tree.
	        path.add(root.data);

	        // Step 2: Check if we have reached a leaf node (both left and right children are null).
	        // If it's a leaf node, we add the current path to the result list.
	        if (root.left == null && root.right == null) {
	            // Create a new list to store this path (important to avoid reference issues).
	            res.add(new ArrayList<>(path));
	        } else {
	            // Step 3: Recursive case - Traverse the left subtree.
	            helper(root.left, res, path);
	            
	            // Step 4: Recursive case - Traverse the right subtree.
	            helper(root.right, res, path);
	        }

	        // Step 5: Backtracking - Remove the last added element before returning to the previous function call.
	        // This ensures that the path remains correct for other recursive calls.
	        path.remove(path.size() - 1);
	    }

	    /**
	     * Function to find all root-to-leaf paths in a binary tree.
	     * This function initializes an empty list and calls the recursive helper function.
	     *
	     * @param root The root node of the binary tree.
	     * @return A list containing all root-to-leaf paths.
	     */
	    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
	        // List to store all root-to-leaf paths.
	        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
	        
	        // List to store the current path during traversal.
	        ArrayList<Integer> path = new ArrayList<>();

	        // Call the recursive function to find paths.
	        helper(root, res, path);

	        // Return the list of all paths.
	        return res;
	    }
	}


}
