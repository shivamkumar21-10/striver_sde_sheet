package com.BST2;

public class FloorinBST {
	
	public class Solution {

	    /**
	     * Helper function to recursively find the floor value of X in a BST.
	     * The floor of X in a BST is the largest value in the BST that is <= X.
	     *
	     * Logic:
	     * - If the root is null, return (base case).
	     * - If root.data <= X, it is a candidate for the floor. Move right to find a closer floor.
	     * - If root.data > X, move left since floor cannot exist in the right subtree.
	     *
	     * @param root The current node in the BST.
	     * @param x The target value for which we need to find the floor.
	     * @param ans A single-element array to store the floor value.
	     */
	    static void helper(TreeNode<Integer> root, int x, int[] ans) {
	        // Base case: If the node is null, return
	        if (root == null) return;

	        // Case 1: If root's value is less than or equal to x,
	        // then this node is a candidate for the floor value.
	        if (root.data <= x) {
	            ans[0] = root.data; // Update floor value
	            helper(root.right, x, ans); // Move to the right to check for a closer floor
	        } else {
	            // Case 2: If root's value is greater than x, ignore this node
	            // and move to the left subtree for smaller values.
	            helper(root.left, x, ans);
	        }
	    }

	    /**
	     * Finds the floor value of X in a given Binary Search Tree.
	     *
	     * Intuition:
	     * - A **Binary Search Tree (BST)** follows the rule:
	     *   Left child < Parent < Right child.
	     * - The **floor of X** is the **largest** value in BST that is **≤ X**.
	     * - If the **current node's value is ≤ X**, it's a **candidate for the floor**, 
	     *   but there might be a **closer floor in the right subtree**.
	     * - If the **current node's value is > X**, the floor must be **in the left subtree**.
	     *
	     * Observations:
	     * - If `root.data == X`, the **floor is X itself**.
	     * - If `root.data < X`, we **update the floor** and move **right** for a better match.
	     * - If `root.data > X`, we move **left** because all right-side values are **greater**.
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
	     * 1. **Start at `10`** → `10 > 6`, move **left**.
	     * 2. **At `5`** → `5 ≤ 6`, update **floor = 5**, move **right**.
	     * 3. **At `7`** → `7 > 6`, move **left** (null node).
	     * 4. **End**, return **floor = 5**.
	     *
	     * Test Case: `X = 12`
	     * 1. **Start at `10`** → `10 ≤ 12`, update **floor = 10**, move **right**.
	     * 2. **At `15`** → `15 > 12`, move **left** (null node).
	     * 3. **End**, return **floor = 10**.
	     *
	     * @param root The root node of the BST.
	     * @param X The target value for which we need to find the floor.
	     * @return The floor value in the BST, or -1 if no floor exists.
	     *
	     * Time Complexity:
	     * - **O(H)**, where `H` is the BST height.
	     *   - **Balanced BST:** `O(log N)`.
	     *   - **Skewed BST:** `O(N)`.
	     *
	     * Space Complexity:
	     * - **O(H)** (recursive call stack).
	     */
	    public static int floorInBST(TreeNode<Integer> root, int X) {
	        int[] ans = new int[1]; // Array to store the floor value
	        ans[0] = -1; // Initialize floor as -1 (default when no floor exists)
	        helper(root, X, ans); // Call helper function to find the floor
	        return ans[0]; // Return the computed floor value
	    }
	}


}
