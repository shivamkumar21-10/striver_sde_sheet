package com.BinaryTree;

import java.util.*;

public class Leetcode662_MaximumWidthofBinaryTree {
	
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
	    // Helper class to store a node along with its assigned index
	    class Pair {
	        TreeNode node; // The current tree node
	        int num; // The assigned index to this node

	        public Pair(TreeNode node, int num) {
	            this.node = node;
	            this.num = num;
	        }
	    }

	    public int widthOfBinaryTree(TreeNode root) {
	        // Edge case: If the tree is empty, return width as 0
	        if (root == null) return 0; 

	        int ans = 0; // Variable to store the maximum width of the tree

	        // Queue for level-order traversal (BFS)
	        Queue<Pair> q = new LinkedList<>();
	        
	        // Add root node with index 0 (starting point)
	        q.offer(new Pair(root, 0));

	        // Perform BFS (level-order traversal)
	        while (!q.isEmpty()) {
	            int size = q.size(); // Number of nodes at the current level
	            int mmin = q.peek().num; // Minimum index at this level to normalize indices
	            int first = 0, last = 0; // First and last node indices at the current level

	            // Traverse all nodes at the current level
	            for (int i = 0; i < size; i++) {
	                // Normalize the current node's index to prevent overflow
	                int curr_id = q.peek().num - mmin; 
	                TreeNode node = q.peek().node; // Get the current node
	                q.poll(); // Remove from queue

	                // Capture the first and last node's index at this level
	                if (i == 0) first = curr_id;  // First node in the level
	                if (i == size - 1) last = curr_id; // Last node in the level

	                // Add left child with updated index (2 * parent + 1)
	                if (node.left != null) {
	                    q.add(new Pair(node.left, curr_id * 2 + 1));
	                }

	                // Add right child with updated index (2 * parent + 2)
	                if (node.right != null) {
	                    q.add(new Pair(node.right, curr_id * 2 + 2));
	                }
	            }
	            // Update the maximum width
	            ans = Math.max(ans, last - first + 1);
	        }
	        return ans; // Return the maximum width found
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================

	1️⃣ **Concept of Indexing:**
	   - Assign **unique indices** to each node similar to a **complete binary tree**:
	     - Root node → Index **0**.
	     - Left child → `2 * parent_index + 1`
	     - Right child → `2 * parent_index + 2`

	2️⃣ **Why Use Indexing?**
	   - Helps in **identifying gaps** in the tree structure.
	   - Prevents issues with **skewed trees** where normal level order traversal fails.

	3️⃣ **Why Normalize the Index (Subtract `mmin`)?**
	   - Prevents **integer overflow** in deep trees.
	   - Ensures that indices remain small and manageable.

	====================================================================
	🔹 Dry Run Example:
	====================================================================

	Consider the binary tree:

	          1
	         / \
	        2   3
	       / \   \
	      4   5   7

	Step-by-step execution:

	🟢 **Level 0:** (Width = 1)
	   - Nodes: `[1]`
	   - Indices: `[0]`
	   - First = 0, Last = 0 → Width = `0 - 0 + 1 = 1`

	🟢 **Level 1:** (Width = 2)
	   - Nodes: `[2, 3]`
	   - Indices: `[1, 2]`
	   - First = 1, Last = 2 → Width = `2 - 1 + 1 = 2`

	🟢 **Level 2:** (Width = 3)
	   - Nodes: `[4, 5, 7]`
	   - Indices: `[3, 4, 6]`
	   - First = 3, Last = 6 → Width = `6 - 3 + 1 = 4`

	**Final Answer:** `4`
	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity:** `O(N)`
	  - Each node is processed **once** in BFS.
	- **Space Complexity:** `O(N)`
	  - The queue stores at most **one level** of the tree.

	====================================================================
	*/


}
