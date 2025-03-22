package com.BinaryTree;
import java.util.*;

public class Left_RightViewOfBinaryTree {
	class Solution {
	    // Function to return list containing elements of left view of binary tree.
	    
	    /**
	     * Recursive function to calculate the left view of the binary tree.
	     * The left view contains the first node at each level when traversed from left to right.
	     * 
	     * Logic:
	     * - We use **preorder traversal** (Root -> Left -> Right), ensuring that we visit the left child first.
	     * - We track the **level** (depth) of each node during recursion.
	     * - If the size of `result` list is equal to the **current level**, it means this is the **first node** at this level.
	     *   So, we add it to `result`.
	     * - We **first visit the left subtree** before the right subtree to ensure leftmost nodes are stored first.
	     * 
	     * **Intuition Behind Storing in Result:**
	     * - The `result` list maintains only one node per level, specifically the first node encountered at that level.
	     * - Since we use **preorder traversal** (root → left → right), the first node we reach at each level is always the leftmost node.
	     * - If a node is the first one encountered at a level, it gets stored in `result`.
	     * - This ensures that when we traverse deeper into the tree, the leftmost nodes are already stored before the right nodes are processed.
	     * - Once a level is filled in `result`, the recursion will not overwrite it because we only add when `level == result.size()`.
	     */
	    void calculateLeft(TreeNode root, int level, List<Integer> result) {
	        // Base condition: If node is null, return
	        if (root == null) {
	            return;
	        }
	        
	        // If the current level is equal to the size of result list,
	        // it means we have encountered the first node of this level.
	        if (level == result.size()) {
	            result.add(root.val);  // Store the first node at this level (leftmost node).
	        }
	        
	        // Recur for left subtree first (ensures leftmost nodes are stored first)
	        calculateLeft(root.left, level + 1, result);
	        
	        // Then recur for right subtree
	        calculateLeft(root.right, level + 1, result);
	    }
	    
	    /**
	     * Wrapper function to compute the left view.
	     * @param root Root of the binary tree.
	     * @return List of elements representing the left view.
	     */
	    ArrayList<Integer> leftView(TreeNode root) {
	        ArrayList<Integer> result = new ArrayList<>(); // Stores the left view nodes
	        calculateLeft(root, 0, result); // Start recursion from level 0
	        return result;
	    }
	    
	    /**
	     * Recursive function to calculate the right view of the binary tree.
	     * The right view contains the first node at each level when traversed from right to left.
	     * 
	     * Logic:
	     * - We use **preorder traversal** (Root -> Right -> Left), ensuring that we visit the right child first.
	     * - If the size of `result` list is equal to the **current level**, it means this is the **first node** at this level.
	     *   So, we add it to `result`.
	     * - We **first visit the right subtree** before the left subtree to ensure rightmost nodes are stored first.
	     */
	    void calculateRight(TreeNode root, int level, List<Integer> result) {
	        if (root == null) {
	            return;
	        }
	        
	        if (level == result.size()) {
	            result.add(root.val);  // Store the first node at this level (rightmost node).
	        }
	        
	        calculateRight(root.right, level + 1, result);
	        calculateRight(root.left, level + 1, result);
	    }
	    
	    /**
	     * Wrapper function to compute the right view.
	     * @param root Root of the binary tree.
	     * @return List of elements representing the right view.
	     */
	    ArrayList<Integer> rightView(TreeNode root) {
	        ArrayList<Integer> result = new ArrayList<>(); // Stores the right view nodes
	        calculateRight(root, 0, result); // Start recursion from level 0
	        return result;
	    }
	}

	/**
	 * Example & Dry Run:
	 * Consider the following binary tree:
	 * 
	 *          1
	 *         / \
	 *        2   3
	 *       / \   \
	 *      4   5   6
	 *     /       \
	 *    7         8
	 * 
	 * Left View should be: [1, 2, 4, 7]
	 * Right View should be: [1, 3, 6, 8]
	 * 
	 * Dry Run (Right View):
	 * - Level 0: First node encountered is `1` → Add to result → result = [1]
	 * - Level 1: First node encountered is `3` → Add to result → result = [1, 3]
	 * - Level 2: First node encountered is `6` → Add to result → result = [1, 3, 6]
	 * - Level 3: First node encountered is `8` → Add to result → result = [1, 3, 6, 8]
	 * 
	 * At each level, the first node encountered in preorder traversal (right-first) is stored.
	 */
}
}
