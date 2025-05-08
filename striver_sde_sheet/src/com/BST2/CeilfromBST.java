package com.BST2;

public class CeilfromBST {
	
	public class Solution {

	    /**
	     * Helper function to recursively find the ceil value of X in a BST.
	     * The ceil of X in a BST is the smallest value in the BST that is >= X.
	     *
	     * Logic:
	     * - If the root is null, return (base case).
	     * - If root.data >= X, it is a candidate for the ceil. Move left to find a closer ceil.
	     * - If root.data < X, move right since ceil cannot exist in the left subtree.
	     *
	     * @param root The current node in the BST.
	     * @param x The target value for which we need to find the ceil.
	     * @param ans A single-element array to store the ceil value.
	     */
	    static void helper(TreeNode<Integer> root, int x, int[] ans) {
	        // Base case: If the node is null, return
	        if (root == null) return;

	        // Case 1: If root's value is greater than or equal to x,
	        // then this node is a candidate for the ceil value.
	        if (root.data >= x) {
	            ans[0] = root.data; // Update ceil value
	            helper(root.left, x, ans); // Move to the left to find a closer ceil
	        } else {
	            // Case 2: If root's value is less than x, ignore this node
	            // and move to the right subtree for larger values.
	            helper(root.right, x, ans);
	        }
	    }

	    /**
	     * Finds the ceil value of X in a given Binary Search Tree.
	     *
	     * Intuition:
	     * - A **Binary Search Tree (BST)** follows the rule:
	     *   Left child < Parent < Right child.
	     * - The **ceil of X** is the **smallest** value in BST that is **≥ X**.
	     * - If the **current node's value is ≥ X**, it's a **candidate for the ceil**, 
	     *   but there might be a **closer ceil in the left subtree**.
	     * - If the **current node's value is < X**, the ceil must be **in the right subtree**.
	     *
	     * Observations:
	     * - If `root.data == X`, the **ceil is X itself**.
	     * - If `root.data > X`, we **update the ceil** and move **left** for a better match.
	     * - If `root.data < X`, we move **right** because all left-side values are **smaller**.
	     *
	     * Dry Run:
	     * --------
	     * Example BST:
	     *          10
	     *         /  \
	     *        5    15
	     *       / \     \
	     *      2   7     18
	     *
	     * Test Case: `X = 6`
	     * 1. **Start at `10`** → `10 > 6`, update **ceil = 10**, move **left**.
	     * 2. **At `5`** → `5 < 6`, move **right**.
	     * 3. **At `7`** → `7 > 6`, update **ceil = 7**, move **left** (null node).
	     * 4. **End**, return **ceil = 7**.
	     *
	     * Test Case: `X = 12`
	     * 1. **Start at `10`** → `10 < 12`, move **right**.
	     * 2. **At `15`** → `15 > 12`, update **ceil = 15**, move **left** (null node).
	     * 3. **End**, return **ceil = 15**.
	     *
	     * @param node The root node of the BST.
	     * @param x The target value for which we need to find the ceil.
	     * @return The ceil value in the BST, or -1 if no ceil exists.
	     *
	     * Time Complexity:
	     * - **O(H)**, where `H` is the BST height.
	     *   - **Balanced BST:** `O(log N)`.
	     *   - **Skewed BST:** `O(N)`.
	     *
	     * Space Complexity:
	     * - **O(H)** (recursive call stack).
	     */
	    public static int findCeil(TreeNode<Integer> node, int x) {
	        int[] ans = new int[1]; // Array to store the ceil value
	        ans[0] = -1; // Initialize ceil as -1 (default when no ceil exists)
	        helper(node, x, ans); // Call helper function to find the ceil
	        return ans[0]; // Return the computed ceil value
	    }
	}


}
