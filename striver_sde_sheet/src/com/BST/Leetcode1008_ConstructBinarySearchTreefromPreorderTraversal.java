package com.BST;
import java.util.*;
public class Leetcode1008_ConstructBinarySearchTreefromPreorderTraversal {
	
//*********IMP*********
//	most imp thing is inorder of BST is always sorted.
//	preorder we can sort to get inorder
//	and from preorder and inorder we already know how to build unique tree 
//	done problem

	/**
	 * Definition for a binary tree node.
	 * Represents a node in the binary tree.
	 */
	class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;

	    // Constructors for different scenarios
	    TreeNode() {}

	    TreeNode(int val) {
	        this.val = val;
	    }

	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}

	class Solution {
	    /**
	     * Recursive function to construct the BST from preorder and inorder traversal.
	     * 
	     * Intuition:
	     * - Preorder traversal gives the root node first.
	     * - Inorder traversal helps determine the left and right subtrees.
	     * - By identifying the root index in the inorder array, we can partition
	     *   the array into left and right subtrees and recursively build the tree.
	     *
	     * Dry Run Example:
	     * Given Preorder: [8, 5, 1, 7, 10, 12]
	     * Sorted Inorder: [1, 5, 7, 8, 10, 12]
	     *
	     * 1. First element in preorder (8) is root.
	     * 2. Find 8 in inorder -> it splits into left subtree [1, 5, 7] and right subtree [10, 12].
	     * 3. Recursively, take next element from preorder for left subtree root (5), then split.
	     * 4. Continue recursively until tree is formed.
	     */
	    TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
	        // Base case: If the start index exceeds the end index, return null.
	        if (preStart > preEnd || inStart > inEnd) return null;

	        // The first element of the current preorder segment is the root node.
	        TreeNode root = new TreeNode(preorder[preStart]);

	        // Find the root's index in the inorder array.
	        int inRoot = inMap.get(root.val);

	        // Count of elements in the left subtree.
	        int numsLeft = inRoot - inStart;

	        // Recursively build the left subtree.
	        root.left = construct(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);

	        // Recursively build the right subtree.
	        root.right = construct(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

	        return root;
	    }

	    /**
	     * Builds a BST from a given preorder traversal.
	     * 
	     * Steps:
	     * 1. Clone the preorder array to create an inorder version.
	     * 2. Sort the inorder array (BST inorder traversal is always sorted).
	     * 3. Store element positions in a HashMap for quick lookup.
	     * 4. Recursively build the BST using preorder and inorder arrays.
	     *
	     * Dry Run Example:
	     * Input: preorder = [8, 5, 1, 7, 10, 12]
	     * Sorted inorder = [1, 5, 7, 8, 10, 12]
	     * 
	     * 1. Root is 8.
	     * 2. Left subtree is {5, 1, 7}, right subtree is {10, 12}.
	     * 3. Build left subtree with root 5, left 1, right 7.
	     * 4. Build right subtree with root 10, right 12.
	     */
	    public TreeNode bstFromPreorder(int[] preorder) {
	        Map<Integer, Integer> map = new HashMap<>();

	        // Clone and sort the preorder array to obtain inorder traversal.
	        int[] inorder = preorder.clone();
	        Arrays.sort(inorder);

	        // Map each value to its index in the inorder array for quick lookup.
	        for (int i = 0; i < inorder.length; i++) {
	            map.put(inorder[i], i);
	        }

	        // Construct the BST using the helper function.
	        TreeNode root = construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);

	        return root;
	    }
	}


}
