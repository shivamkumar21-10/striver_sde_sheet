package com.BST2;

public class Leetcode230_KthSmallestElementinaBST {
	
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
	    
	    // Helper function to perform in-order traversal and find the kth smallest element
	    private int inorder(TreeNode root, int k, int[] count) {
	        // Base case: if root is null, return -1 (indicating element not found)
	        if (root == null) return -1;

	        // Recur to the left subtree first (smallest elements are on the left)
	        int left = inorder(root.left, k, count);
	        if (left != -1) return left; // If kth element is found in left subtree, return it

	        // Increment count since we are visiting a node
	        count[0]++;
	        
	        // If count matches k, we have found the kth smallest element
	        if (count[0] == k) return root.val;

	        // Recur to the right subtree
	        return inorder(root.right, k, count);
	    }

	    public int kthSmallest(TreeNode root, int k) {
	        // Array to keep track of the count of visited nodes
	        int[] count = new int[1]; // count[0] = 0 initially
	        return inorder(root, k, count);
	    }
	}

	/**
	 * Intuition:
	 * - The smallest elements in a BST are found in in-order traversal (Left -> Root -> Right).
	 * - If we traverse the tree in in-order, the kth visited node is the kth smallest element.
	 * - We use recursion to perform in-order traversal and maintain a counter to track visited nodes.
	 *
	 * Observations:
	 * - We do not need to store all elements; we just track the count and stop early.
	 * - The leftmost node is the smallest element.
	 * - If k is 1, return the smallest element.
	 * - If k is equal to total nodes, return the largest element.
	 *
	 * Dry Run Example:
	 *
	 * Given BST:
	 *        5
	 *       / \
	 *      3   7
	 *     / \   \
	 *    2   4   8
	 *
	 * k = 3 (Find the 3rd smallest element)
	 * In-order traversal: [2, 3, 4, 5, 7, 8]
	 * Answer = 4
	 *
	 * Step-by-Step Execution:
	 * --------------------------------
	 * Call: inorder(5, 3, count)  --> count = 0
	 * |-> Call: inorder(3, 3, count)  --> count = 0
	 * |   |-> Call: inorder(2, 3, count)  --> count = 0
	 * |   |   |-> Call: inorder(null, 3, count) --> return -1
	 * |   |   |-> Visit node 2, count = 1
	 * |   |   |-> Call: inorder(null, 3, count) --> return -1
	 * |   |-> Visit node 3, count = 2
	 * |   |-> Call: inorder(4, 3, count) --> count = 2
	 * |   |   |-> Call: inorder(null, 3, count) --> return -1
	 * |   |   |-> Visit node 4, count = 3 (Found the answer!)
	 * |   |   |-> Return 4 up the recursion stack
	 * |   |-> Return 4 to inorder(3)
	 * |-> Return 4 to inorder(5)
	 * --------------------------------
	 *
	 * Output: 4
	 *
	 * Complexity Analysis:
	 * - Time Complexity: O(k), since we only traverse the first k elements.
	 * - Space Complexity: O(H), where H is the height of the BST (due to recursion stack).
	 */


}
