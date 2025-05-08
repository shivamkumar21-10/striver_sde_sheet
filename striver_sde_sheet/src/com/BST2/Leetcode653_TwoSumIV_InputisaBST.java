package com.BST2;

public class Leetcode653_TwoSumIV_InputisaBST {
	
	/**
	 * Definition for a binary tree node.
	 * This class represents a node in a Binary Search Tree (BST).
	 */
	class TreeNode {
	    int val; // Value of the node
	    TreeNode left, right; // Left and right child references

	    // Constructors
	    TreeNode() {}
	    TreeNode(int val) { this.val = val; }
	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}

	/**
	 * Solution class that contains the `findTarget` method.
	 * This method determines if there exist two nodes in the BST
	 * whose values sum up to a given target `k`.
	 */
	class Solution {
	    
	    // A HashSet to store unique values of nodes we have seen so far
	    Set<Integer> res = new HashSet<>();

	    /**
	     * The function `findTarget` checks if two elements exist in the BST
	     * whose sum equals `k`. It utilizes a HashSet to track visited values.
	     * 
	     * Intuition:
	     * - If we traverse the BST and store visited values in a HashSet,
	     *   we can check if `k - root.val` exists in the HashSet.
	     * - If yes, we have found two numbers that sum to `k`.
	     * - If not, we continue searching in left and right subtrees.
	     * 
	     * Observations:
	     * - Since the problem asks only whether such a pair exists,
	     *   we do not need to store all values, just check in real time.
	     * - HashSet operations (contains, add) take **O(1) average time**.
	     * - The overall traversal takes **O(N) time** in the worst case.
	     * 
	     * Logic:
	     * - If the `root` is null, return false (base case for recursion).
	     * - Check if `k - root.val` exists in `res`. If it does, return true.
	     * - Otherwise, add `root.val` to `res` and recursively check left and right subtrees.
	     * - If any subtree returns true, propagate that answer.
	     * 
	     * @param root The root node of the BST.
	     * @param k The target sum.
	     * @return true if two distinct nodes in BST sum to `k`, false otherwise.
	     */
	    public boolean findTarget(TreeNode root, int k) {
	        // Base case: If the tree is empty, return false
	        if (root == null)
	            return false;

	        // Check if there exists a number in the set that sums up with `root.val` to give `k`
	        if (res.contains(k - root.val))
	            return true; // Found the required pair
	        
	        // Store the current node value in the HashSet
	        res.add(root.val);

	        // Recursively search in left and right subtrees
	        return findTarget(root.left, k) || findTarget(root.right, k);
	    }
	}

	/**
	 * Dry Run:
	 * 
	 * Consider the following BST:
	 * 
	 *        5
	 *       / \
	 *      3   6
	 *     / \   \
	 *    2   4   7
	 * 
	 * Example 1:
	 * Input: k = 9
	 * Expected Output: true (because 2 + 7 = 9)
	 * 
	 * Execution Flow:
	 * - Start with root (5), add to `res`: {5}
	 * - Move to left (3), add to `res`: {5, 3}
	 * - Move to left (2), add to `res`: {5, 3, 2}
	 * - Check 9 - 2 = 7 in `res` → Not found, move back
	 * - Move to right (4), add to `res`: {5, 3, 2, 4}
	 * - Check 9 - 4 = 5 in `res` → Found! (5 + 4 = 9)
	 * - Return true
	 * 
	 * Example 2:
	 * Input: k = 28
	 * Expected Output: false (No such pair exists)
	 * 
	 * Execution Flow:
	 * - Traverse all nodes, checking for complement `(k - node.val)`.
	 * - No pair found, return false.
	 * 
	 * Time Complexity: **O(N)** (each node visited once)
	 * Space Complexity: **O(N)** (worst case storage in HashSet)
	 */


}
