package com.BinaryTree2;

public class Leetcode543_DiameterofBinaryTree {
	
//	*********************Brute Force O(N^2)***************************************
	
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
	     * Function to calculate the height of a binary tree.
	     * Height of a node is defined as the number of edges in the longest path from that node to a leaf.
	     */
	    public int height(TreeNode root) {
	        // Base case: If the node is null, height is 0
	        if(root == null){
	            return 0;
	        }

	        // Recursively calculate the height of the left and right subtrees
	        int left = height(root.left);
	        int right = height(root.right);

	        // Height of the current node is 1 + maximum of left and right subtree heights
	        return 1 + Math.max(left, right);
	    }

	    /**
	     * Function to calculate the diameter of a binary tree.
	     * The diameter is the longest path between any two nodes in the tree.
	     * The path may or may not pass through the root.
	     */
	    public int diameterOfBinaryTree(TreeNode root) {
	        // Base case: If the tree is empty, diameter is 0
	        if(root == null) return 0;

	        // Option 1: The diameter exists entirely in the left subtree
	        int op1 = diameterOfBinaryTree(root.left);

	        // Option 2: The diameter exists entirely in the right subtree
	        int op2 = diameterOfBinaryTree(root.right);

	        // Option 3: The diameter passes through the root (left height + right height)
	        int op3 = height(root.left) + height(root.right);

	        // The maximum of these three options is the actual diameter
	        return Math.max(op1, Math.max(op2, op3));
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Understanding Diameter of a Binary Tree:**
	   - The **diameter** is the longest path between any two nodes in the tree.
	   - The path may **or may not** pass through the root.

	2️⃣ **How the Recursive Approach Works:**
	   - The **height()** function calculates the height of a subtree.
	   - The **diameterOfBinaryTree()** function explores three possible cases:
	     🔹 The longest path is in the **left subtree**.
	     🔹 The longest path is in the **right subtree**.
	     🔹 The longest path **passes through the root** (sum of left and right subtree heights).

	====================================================================
	🔹 Dry Run Example:
	====================================================================
	Consider the binary tree:

	          1
	         / \
	        2   3
	       / \    
	      4   5   

	Step-by-step execution:

	🔹 **Recursive Calls Breakdown:**
	1️⃣ `height(4) = 0`, `height(5) = 0`  
	   ➝ `height(2) = 1 + max(0,0) = 1`

	2️⃣ `height(3) = 0`  
	   ➝ `height(1) = 1 + max(1,0) = 2`

	3️⃣ Calculating diameter:
	   - `op1 = diameterOfBinaryTree(2)`
	   - `op2 = diameterOfBinaryTree(3)`
	   - `op3 = height(2) + height(3) = 1 + 0 = 1`
	   - Result: `diameter = max(op1, op2, op3) = 2`

	Final result: **Diameter = 3** (Path: 4 → 2 → 1 → 3)

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity:** `O(N^2)`
	  - Each node calls `height()`, which takes `O(N)`, resulting in `O(N^2)`.
	  - This can be optimized to `O(N)` using **memoization** (by storing heights in the same recursion).

	- **Space Complexity:** `O(H)`
	  - The recursive call stack depth is the height of the tree, `O(H)`.
	  - In a balanced tree, `H = log(N)`, making space complexity `O(logN)`.
	  - In a skewed tree, `H = N`, making space complexity `O(N)`.

	====================================================================
	🔹 Optimization Suggestion:
	====================================================================
	- The height is calculated **multiple times**, leading to redundant calculations.
	- We can optimize this by modifying the `height()` function to **return both height and diameter** in a single traversal.
	- This reduces the complexity from **O(N²) to O(N)**.

	====================================================================
	*/

	
	
	
//	*********************Optimal O(N)*************************************************
	
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
	     * This function calculates the height of the tree while simultaneously updating the diameter.
	     * Instead of computing height separately for each recursive call, we optimize it by maintaining
	     * a single traversal where we compute both height and diameter together.
	     */
	    public int height(TreeNode root, int[] diameter) {
	        // Base case: If the node is null, height is 0
	        if(root == null){
	            return 0;
	        }

	        // Recursively compute the height of the left subtree
	        int left = height(root.left, diameter);

	        // Recursively compute the height of the right subtree
	        int right = height(root.right, diameter);

	        // Update the maximum diameter found so far
	        // The diameter at this node is given by left height + right height
	        diameter[0] = Math.max(diameter[0], left + right);

	        // Return height of the current node
	        return 1 + Math.max(left, right);
	    }

	    /**
	     * Function to compute the diameter of the binary tree.
	     * Uses a single traversal to compute both height and diameter.
	     */
	    public int diameterOfBinaryTree(TreeNode root) {
	        // Array to store the diameter, as Java does not support pass-by-reference for primitive types
	        int[] diameter = new int[1];

	        // Compute height while updating diameter
	        height(root, diameter);

	        // Return the final computed diameter
	        return diameter[0];
	    }
	}

	/*
	====================================================================
	🔹 Observations and Intuition:
	====================================================================
	1️⃣ **Understanding Diameter of a Binary Tree:**
	   - The **diameter** is the longest path between any two nodes in the tree.
	   - This path may **or may not** pass through the root.
	   - The length of this path is measured by counting **the number of edges**.

	2️⃣ **Why is the Previous Approach Inefficient?**
	   - The previous implementation called the `height()` function **multiple times**.
	   - Specifically, in `diameterOfBinaryTree()`, for each node, we:
	     - Compute `diameterOfBinaryTree(root.left)`
	     - Compute `diameterOfBinaryTree(root.right)`
	     - Compute `height(root.left) + height(root.right)`
	   - **Each height computation takes O(N), leading to O(N²) complexity.**

	3️⃣ **Optimized Approach:**
	   - Instead of calling `height()` multiple times, we compute it **once per node**.
	   - We **pass an array (`int[] diameter`)** to store the maximum diameter found so far.
	   - In a **single traversal**, we update both **height** and **diameter** dynamically.

	====================================================================
	🔹 Dry Run Example:
	====================================================================
	Consider the binary tree:

	          1
	         / \
	        2   3
	       / \    
	      4   5   

	🔹 **Recursive Execution Breakdown:**
	1️⃣ `height(4) = 1, height(5) = 1`
	   - `diameter[0] = max(0, 1 + 1) = 2`
	   
	2️⃣ `height(2) = 1 + max(1, 1) = 2`
	   - `diameter[0] = max(2, 2 + 0) = 2`
	   
	3️⃣ `height(3) = 1`
	   - `diameter[0] = max(2, 2 + 1) = 3`
	   
	4️⃣ `height(1) = 1 + max(2, 1) = 3`
	   - `diameter[0] = max(3, 2 + 1) = 3`
	   
	Final result: **Diameter = 3** (Path: 4 → 2 → 1 → 3)

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity: O(N)**  
	  - Each node is visited **only once**, as we calculate height and update diameter in the same recursion.
	  - Since we traverse each node once, the time complexity is **O(N)**.

	- **Space Complexity: O(H)**  
	  - The function uses recursion, which takes **O(H)** space in the call stack.
	  - In a balanced tree, **H = log(N)** → **O(log N) space**.
	  - In a skewed tree, **H = N** → **O(N) space**.

	====================================================================
	🔹 Summary of Optimization:
	====================================================================
	✅ **Previous Approach:**  
	   - Time Complexity: **O(N²)** (Height computation repeated)  
	   - Space Complexity: **O(H)**  

	✅ **Optimized Approach (Current Implementation):**  
	   - Time Complexity: **O(N)** (Single traversal for both height and diameter)  
	   - Space Complexity: **O(H)** (Recursion depth)  

	💡 **Key Takeaway:** We reduced redundant `height()` calls by computing both **height** and **diameter** in a single pass using a helper function. 🚀
	*/


}
