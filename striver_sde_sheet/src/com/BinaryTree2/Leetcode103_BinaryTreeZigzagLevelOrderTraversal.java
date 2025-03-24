package com.BinaryTree2;

import java.util.*;
public class Leetcode103_BinaryTreeZigzagLevelOrderTraversal {
	
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
	    /**
	     * Function to perform zigzag level order traversal of a binary tree.
	     * 
	     * @param root Root node of the binary tree.
	     * @return List of lists where each list contains nodes at that level in zigzag order.
	     */
	    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        // List to store the final zigzag level order traversal
	        List<List<Integer>> result = new ArrayList<>();
	        
	        // Edge case: If the tree is empty, return an empty list
	        if (root == null) return result;

	        // Queue for performing level order traversal (BFS)
	        Queue<TreeNode> queue = new LinkedList<>();
	        queue.offer(root); // Start BFS from the root

	        // Boolean flag to track direction of traversal (left-to-right or right-to-left)
	        boolean leftToRight = true;

	        // BFS Traversal: Process nodes level by level
	        while (!queue.isEmpty()) {
	            int levelSize = queue.size(); // Get the number of nodes at the current level
	            List<Integer> level = new ArrayList<>(levelSize); // List to store nodes at current level
	            
	            // Process all nodes at the current level
	            for (int i = 0; i < levelSize; i++) {
	                TreeNode node = queue.poll(); // Remove front node from queue
	                
	                // Add nodes in the appropriate order based on traversal direction
	                if (leftToRight) {
	                    level.add(node.val); // If left to right, append to the end
	                } else {
	                    level.add(0, node.val); // If right to left, insert at the beginning
	                }
	                
	                // Add child nodes to queue for next level processing
	                if (node.left != null) queue.offer(node.left);
	                if (node.right != null) queue.offer(node.right);
	            }
	            
	            // After processing the current level, flip the direction for the next level
	            leftToRight = !leftToRight;
	            
	            // Store the processed level in the final result
	            result.add(level);
	        }
	        
	        return result;
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Understanding the Problem:**
	   - We need to traverse the tree level by level.
	   - Nodes at **even levels** should be stored **left to right**.
	   - Nodes at **odd levels** should be stored **right to left**.
	   - This means we need a **Zigzag pattern** instead of a normal level order traversal.

	2️⃣ **Key Trick for Zigzag Order:**
	   - Use a **boolean flag (`leftToRight`)** to decide traversal order.
	   - If `leftToRight = true`, add nodes **normally to the list**.
	   - If `leftToRight = false`, insert nodes **at the beginning of the list** (reverse order).

	====================================================================
	🔹 Explanation of the `leftToRight` Boolean Flag:
	====================================================================
	✅ `leftToRight = true`: **Process left to right (Normal order)**
	   - Nodes are simply **added to the end** of the list.

	✅ `leftToRight = false`: **Process right to left (Reverse order)**
	   - Nodes are **inserted at index 0**, effectively reversing the order.

	🔹 **Why use a Boolean Flag instead of a Stack?**
	   - A stack could be used, but reversing the list after each level is **extra processing**.
	   - Using `leftToRight` with **ArrayList insertion at index 0** is **efficient and cleaner**.

	====================================================================
	🔹 Dry Run Example:
	====================================================================

	Example Tree:
	        1
       / \
      2   3
     / \   \
    4   5   6

🔹 Level-wise traversal with `leftToRight` toggling:
1️⃣ **Level 0** (left to right) → `[1]`
   Queue after processing: `[2, 3]`
2️⃣ **Level 1** (right to left) → `[3, 2]`
   Queue after processing: `[4, 5, 6]`
3️⃣ **Level 2** (left to right) → `[4, 5, 6]`
   Queue is empty, traversal ends.

✅ **Final Output:**
[
    [1],
    [3, 2],
    [4, 5, 6]
]

====================================================================
🔹 Complexity Analysis:
====================================================================
- **Time Complexity: O(N)**
  - Every node is visited **once**, making it **O(N)**.
  - Adding at index `0` takes `O(k)` per level, but at most `N/2` insertions occur.
  - Overall, it remains **O(N)**.

- **Space Complexity: O(N)**
  - The queue stores nodes level by level (**O(N) in worst case**).
  - The result list also holds **O(N) elements**.

====================================================================
*/


}
