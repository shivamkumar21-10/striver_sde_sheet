package com.BST2;
import java.util.*;
public class Leetcode173_BinarySearchTreeIterator {
	
	/**
	 * Definition for a binary tree node.
	 * A standard TreeNode class representing a node in a Binary Search Tree (BST).
	 */
	class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;

	    // Constructors to initialize the TreeNode
	    TreeNode() {}
	    TreeNode(int val) { this.val = val; }
	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}

	/**
	 * BSTIterator class implements an iterator for a BST.
	 * This iterator provides an efficient way to traverse the BST 
	 * in **inorder traversal (Left -> Root -> Right)** using **O(h) space**, where `h` is the height of the tree.
	 */
	class BSTIterator {
	    private Stack<TreeNode> stk = new Stack<>(); // Stack to store left nodes

	    /**
	     * Constructor: Initializes the iterator by pushing the leftmost path of the tree into the stack.
	     * This ensures that the smallest element (leftmost node) is always on top.
	     * 
	     * Intuition:
	     * - The inorder traversal of a BST gives sorted order of elements.
	     * - Instead of storing the whole inorder traversal, we use a stack to **store only the left path**.
	     * - This allows us to retrieve the next smallest element in O(1) amortized time.
	     * 
	     * Logic:
	     * - We push **all left children** of the given root into the stack.
	     * - The **top of the stack always contains the next smallest element** in the BST.
	     */
	    public BSTIterator(TreeNode root) {
	        pushAll(root);
	    }

	    /**
	     * Returns the next smallest number in the BST.
	     * 
	     * - The smallest element is at the **top of the stack** (leftmost node).
	     * - We pop it and process its **right subtree** (if any).
	     * - We then push **all left children** of the right subtree into the stack.
	     * - This ensures that the stack always contains the next smallest element.
	     */
	    public int next() {
	        TreeNode tempNode = stk.pop(); // Pop the smallest element from the stack
	        pushAll(tempNode.right); // Process right child (if any), push its left subtree
	        return tempNode.val; // Return the smallest element
	    }

	    /**
	     * Returns true if there are still elements left in the iterator.
	     * If the stack is empty, it means we've traversed all elements.
	     */
	    public boolean hasNext() {
	        return !stk.isEmpty();
	    }

	    /**
	     * Helper method to push all left children of a given node into the stack.
	     * 
	     * - The **leftmost node is the smallest** in a BST.
	     * - We push all left children so that we can retrieve them in sorted order.
	     */
	    private void pushAll(TreeNode root) {
	        while (root != null) {
	            stk.add(root); // Push current node onto the stack
	            root = root.left; // Move left (smallest values first)
	        }
	    }
	}

	/**
	 * Dry Run:
	 * Consider the following BST:
	 * 
	 *        7
	 *       / \
	 *      3   15
	 *         /  \
	 *        9   20
	 * 
	 * Step 1: Initialization (`BSTIterator(root)`)
	 * - Stack after `pushAll(root)`: [7, 3] (Push leftmost path)
	 * 
	 * Step 2: `next()` → Pops 3 (smallest)
	 * - Stack now: [7]
	 * - Output: 3
	 * 
	 * Step 3: `next()` → Pops 7
	 * - Stack now: [15, 9] (Push left subtree of 15)
	 * - Output: 7
	 * 
	 * Step 4: `next()` → Pops 9
	 * - Stack now: [15]
	 * - Output: 9
	 * 
	 * Step 5: `next()` → Pops 15
	 * - Stack now: [20] (Push left subtree of 20)
	 * - Output: 15
	 * 
	 * Step 6: `next()` → Pops 20
	 * - Stack now: []
	 * - Output: 20
	 * 
	 * Final Step: `hasNext()` returns false (stack is empty).
	 * 
	 * Time Complexity:
	 * - `next()`: O(1) amortized
	 * - `hasNext()`: O(1)
	 * - Overall for `n` nodes: O(n)
	 * 
	 * Space Complexity:
	 * - O(h), where `h` is the tree height (log(n) for balanced BST, n for skewed BST).
	 * 
	 * Usage Example:
	 * BSTIterator obj = new BSTIterator(root);
	 * int param_1 = obj.next();  // Gets next smallest element
	 * boolean param_2 = obj.hasNext(); // Checks if more elements exist
	 */


}
