package com.BST2;

public class KthlargestelementinBST {
	
	class Solution {
	    /*
	     * Intuition:
	     * -----------
	     * - The largest elements in a BST are on the rightmost side.
	     * - We can use reverse in-order traversal (Right -> Root -> Left) to visit elements in descending order.
	     * - The Kth largest element is simply the Kth element encountered in this traversal.
	     *
	     * Observations:
	     * --------------
	     * - A normal in-order traversal (Left -> Root -> Right) gives elements in sorted order.
	     * - Reversing the in-order traversal gives elements in descending order.
	     * - This means the first element visited in this order is the largest, second is the second largest, etc.
	     *
	     * Logic:
	     * -------
	     * - We start from the rightmost node and move leftward.
	     * - We maintain a counter to track how many elements we have visited.
	     * - When the counter reaches `k`, we return that node's value.
	     *
	     * Dry Run:
	     * ---------
	     * Consider the following BST:
	     *
	     *         5
	     *        / \
	     *       3   8
	     *      / \   \
	     *     2   4   10
	     *
	     * k = 2 (We need the 2nd largest element)
	     *
	     * - Start at root (5), move to right child (8).
	     * - Move to right child of 8 (10), count = 1.
	     * - Backtrack to 8, count = 2 (We found the 2nd largest, return 8).
	     */

	    public int reverseInorder(Node root, int k, int[] count) {
	        if (root == null) return -1; // Base case: If root is null, return -1

	        // Traverse the right subtree first to access larger elements
	        int right = reverseInorder(root.right, k, count);
	        if (right != -1) return right; // If kth largest is found in right subtree, return it

	        // Increment count, since we are visiting a node
	        count[0]++;
	        if (count[0] == k) return root.data; // If count matches k, we found the kth largest element

	        // Traverse the left subtree if needed
	        return reverseInorder(root.left, k, count);
	    }

	    public int kthLargest(Node root, int k) {
	        // Array to maintain count as we traverse the tree
	        int[] count = new int[1];
	        count[0] = 0;
	        return reverseInorder(root, k, count); // Start reverse in-order traversal
	    }
	}


}
