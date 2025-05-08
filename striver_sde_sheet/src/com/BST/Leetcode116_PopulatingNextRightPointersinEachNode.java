package com.BST;

public class Leetcode116_PopulatingNextRightPointersinEachNode {
	
	/*
	// Definition for a Node.
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	};
	*/

	class Solution {
	    public Node connect(Node root) {
	        // Base condition: If the root is null, simply return null as there's nothing to connect.
	        if(root == null) return null;

	        // 'curr' will act as a pointer to traverse level by level.
	        Node curr = root;
	        
	        // Outer loop: Moves down the tree level by level, as long as the current node has a left child.
	        // This ensures we are working only on perfect binary trees, where each parent has two children.
	        while(curr.left != null){
	            
	            // Store the current node reference as 'temp' to keep track of the level start.
	            Node temp = curr;

	            // Inner loop: Connects nodes at the current level.
	            while(curr != null){
	                // Connect the left child to the right child of the same parent.
	                curr.left.next = curr.right;

	                // If 'curr.next' exists, connect the right child of 'curr' to the left child of 'curr.next'.
	                // This bridges the gap between adjacent parents.
	                curr.right.next = curr.next == null ? null : curr.next.left;

	                // Move 'curr' to the next node in the current level.
	                curr = curr.next;
	            }

	            // Move down to the next level.
	            curr = temp.left;
	        }
	        return root;
	    }
	}

	/*
	🔹 Intuition:
	   - This problem involves connecting the next pointer of nodes in a **perfect binary tree**.
	   - The goal is to ensure that all nodes at the same level are connected using the 'next' pointer.
	   - Since it's a perfect binary tree, each parent has exactly **two children**.
	   - We will traverse level by level and establish connections iteratively.

	🔹 Observations:
	   - Each level of the tree forms a **linked list** due to the 'next' pointers.
	   - If we traverse level-wise, we can easily connect:
	     - **Left child → Right child (within the same parent)**
	     - **Right child → Left child of the next parent** (bridging between adjacent parents)

	🔹 Dry Run:
	   Consider the following perfect binary tree:
	   
	       1
	      / \
	     2   3
	    / \  / \
	   4   5 6  7

	   1st iteration (root level): 
	   - No connections needed, as there's only one node.

	   2nd iteration (level 2):
	   - `2.next = 3`

	   3rd iteration (level 3):
	   - `4.next = 5`
	   - `5.next = 6`
	   - `6.next = 7`

	   The modified tree with next pointers:
	   
	       1 -> null
	      / \
	     2 -> 3 -> null
	    / \  / \
	   4→ 5→6 → 7 -> null

	   All nodes at the same level are now connected!

	🔹 Complexity Analysis:
	   - **Time Complexity:** O(N), since we visit each node once.
	   - **Space Complexity:** O(1), as we use constant extra space (no extra DS like queues).
	*/

	
//******************************RECURSIVE********************************************************
	/*
	// Definition for a Node.
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	};
	*/

	class Solution2 {
	    public Node connect(Node root) {
	        // Base case: If the root is null, return null as there is nothing to connect.
	        if(root == null) return null;

	        // If the left child exists, connect it to the right child.
	        if(root.left != null) root.left.next = root.right;

	        // If the right child exists and the current node has a next pointer,
	        // connect the right child to the left child of the adjacent node.
	        if(root.right != null && root.next != null) 
	            root.right.next = root.next.left;

	        // Recursively connect the left and right subtrees.
	        connect(root.left);
	        connect(root.right);

	        // Return the root node after establishing all connections.
	        return root;
	    }
	}

	/*
	🔹 **Intuition:**
	   - We need to connect the next pointers in a **perfect binary tree**.
	   - Since it is a perfect tree, each parent has exactly **two children**.
	   - Using recursion, we can establish the `next` pointers in a **top-down** manner.

	🔹 **Observations:**
	   - Every node at a given level should be connected to its next **sibling node**.
	   - If a node has a left child, it should point to the right child of the same parent.
	   - If a node has a right child, it should point to the **left child of the adjacent parent**.

	🔹 **How the recursive function works:**
	   - **Step 1:** If the root is `null`, return.
	   - **Step 2:** If `root.left` exists, set `root.left.next = root.right`.
	   - **Step 3:** If `root.right` exists and the current node (`root`) has a `next` pointer, 
	     connect `root.right` to `root.next.left`.
	   - **Step 4:** Recursively process the left and right children.

	🔹 **Dry Run Example:**
	   Consider the following **perfect binary tree**:

	       1
	      / \
	     2   3
	    / \  / \
	   4   5 6  7

	1. **First recursive call (root = 1)**:
	   - `2.next = 3`
	   - `connect(2)`, `connect(3)`

	2. **Second recursive call (root = 2)**:
	   - `4.next = 5`
	   - `5.next = 6` (because `2.next = 3`, so `2.right.next = 3.left`)
	   - `connect(4)`, `connect(5)`

	3. **Second recursive call (root = 3)**:
	   - `6.next = 7`
	   - `connect(6)`, `connect(7)`

	The final tree with **next pointers**:

	       1 -> null
	      / \
	     2 -> 3 -> null
	    / \  / \
	   4→ 5→6 → 7 -> null

	🔹 **Complexity Analysis:**
	   - **Time Complexity:** O(N), since we visit each node once.
	   - **Space Complexity:** O(H) = O(log N) (due to recursive stack calls).
	     - Here, `H` is the height of the tree, which is `log N` in a perfect binary tree.

	✅ **Key Takeaways:**
	   - This solution follows a **preorder traversal** (Root → Left → Right).
	   - It efficiently establishes the `next` pointers in **one pass** without using extra space.
	   - Recursion ensures we process the tree in a **top-down manner**.
*/

}
