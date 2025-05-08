package com.BST;

public class PredecessorandSuccessor {
	
	class Solution {
	    public static void findPreSuc(Node root, Node[] pre, Node[] suc, int key) {
	        /*
	         * ==============================
	         * INTUITION:
	         * ==============================
	         * The goal is to find:
	         * 1. **Predecessor** (Pre): The largest value smaller than the key.
	         * 2. **Successor** (Suc): The smallest value greater than the key.
	         *
	         * In a **Binary Search Tree (BST)**:
	         * - Left subtree nodes are smaller than the root.
	         * - Right subtree nodes are larger than the root.
	         *
	         * This allows us to efficiently find predecessor and successor using BST properties.
	         */

	        // ============================
	        // BASE CASE: If root is null, return (No node to process)
	        // ============================
	        if (root == null) return;

	        /*
	         * ==============================
	         * CASE 1: If the key is found in the tree
	         * ==============================
	         * - The **predecessor** is the rightmost node in the **left subtree**.
	         * - The **successor** is the leftmost node in the **right subtree**.
	         */
	        if (root.data == key) {
	            // FINDING PREDECESSOR (Largest value in left subtree)
	            if (root.left != null) {
	                Node temp = root.left;
	                while (temp.right != null) {  // Move to the rightmost node in left subtree
	                    temp = temp.right;
	                }
	                pre[0] = temp;  // Store predecessor
	            }

	            // FINDING SUCCESSOR (Smallest value in right subtree)
	            if (root.right != null) {
	                Node temp = root.right;
	                while (temp.left != null) {  // Move to the leftmost node in right subtree
	                    temp = temp.left;
	                }
	                suc[0] = temp;  // Store successor
	            }

	            // Once both predecessor and successor are found, we return
	            return;
	        }

	        /*
	         * ==============================
	         * CASE 2: If key is smaller than root value
	         * ==============================
	         * - The **key must exist in the left subtree**.
	         * - The current root could be the **successor**, as it is the smallest node greater than key.
	         * - Continue searching in the left subtree.
	         */
	        if (root.data > key) {
	            suc[0] = root; // Mark root as possible successor
	            findPreSuc(root.left, pre, suc, key); // Recursively search in left subtree
	        }
	        
	        /*
	         * ==============================
	         * CASE 3: If key is greater than root value
	         * ==============================
	         * - The **key must exist in the right subtree**.
	         * - The current root could be the **predecessor**, as it is the largest node smaller than key.
	         * - Continue searching in the right subtree.
	         */
	        else {
	            pre[0] = root; // Mark root as possible predecessor
	            findPreSuc(root.right, pre, suc, key); // Recursively search in right subtree
	        }
	    }
	}

	/*
	 * ==============================
	 * DRY RUN EXAMPLE:
	 * ==============================
	 * BST Structure:
	 *
	 *         50
	 *       /    \
	 *      30     70
	 *     /  \   /  \
	 *    20  40 60  80
	 *
	 * Test Case 1: Key = 50
	 * - Predecessor: Rightmost node in left subtree → 40
	 * - Successor: Leftmost node in right subtree → 60
	 *
	 * Test Case 2: Key = 30
	 * - Predecessor: Rightmost node in left subtree → 20
	 * - Successor: Leftmost node in right subtree → 40
	 *
	 * Test Case 3: Key = 35 (Not in BST)
	 * - Traverse left from 50 → Right from 30 → Key not found.
	 * - Predecessor = 30, Successor = 40
	 */

	/*
	 * ==============================
	 * TIME COMPLEXITY ANALYSIS:
	 * ==============================
	 * - Each recursive call moves **left or right** → **O(h)**, where **h** is the height of the tree.
	 * - **O(log N)** for **balanced BST**.
	 * - **O(N)** for **skewed BST** (worst case).
	 */

	/*
	 * ==============================
	 * SPACE COMPLEXITY ANALYSIS:
	 * ==============================
	 * - **O(h)** recursive stack space in **worst case** (O(log N) for balanced BST).
	 * - **O(1)** extra space (no additional data structures used).
	 */


}
