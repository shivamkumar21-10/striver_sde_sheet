package com.BinaryTree;
import java.util.*;

public class BottomView {

	class Solution {
	    // Function to return a list containing the bottom view of the given tree.
	    public ArrayList<Integer> bottomView(Node root) {
	        /*
	         * Observations:
	         * - This problem requires finding the **bottom-most visible nodes** when looking at the tree **from the front**.
	         * - Nodes are categorized based on **horizontal distance (hd)** from the root.
	         * - If multiple nodes have the **same hd**, the node that appears last (deepest level) in level order traversal is stored.
	         * 
	         * Approach:
	         * - Use **TreeMap<Integer, Integer> map** to store the **last node** at each `hd`.
	         * - Perform **Level Order Traversal (BFS)** using a queue.
	         * - Update the `map` as we process nodes, so the bottom-most node for each `hd` remains in the map.
	         * - The final output is the sorted values from the TreeMap.
	         * 
	         * Time Complexity: **O(N log N)** (due to TreeMap operations)
	         * Space Complexity: **O(N)** (storing nodes in the queue and map)
	         */

	        TreeMap<Integer, Integer> map = new TreeMap<>();  // Stores (hd -> node value)
	        ArrayList<Integer> ans = new ArrayList<>();  // Stores the final bottom view

	        Queue<Node> q = new LinkedList<>();
	        q.add(root);
	        root.hd = 0;  // Initialize root horizontal distance as 0

	        while (!q.isEmpty()) {
	            Node front = q.poll();  // Get the front node
	            int hd = front.hd;  // Get its horizontal distance
	            
	            // **Key Step**: Update the map with the latest node at this horizontal distance.
	            // Since we are processing in level-order (BFS), deeper nodes naturally override upper ones.
	            map.put(hd, front.data);

	            // If left child exists, assign `hd - 1` and enqueue it
	            if (front.left != null) {
	                front.left.hd = hd - 1;
	                q.add(front.left);
	            }

	            // If right child exists, assign `hd + 1` and enqueue it
	            if (front.right != null) {
	                front.right.hd = hd + 1;
	                q.add(front.right);
	            }
	        }

	        // Extracting the bottom view from the TreeMap in sorted order of horizontal distance
	        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
	            ans.add(pair.getValue());
	        }
	        return ans;
	    }
	    
	    /*
	    -------------------------------------
	    ### Vertical Order Traversal & Its Usage Here:
	    -------------------------------------
	    **What is Vertical Order Traversal?**
	    - In vertical order traversal, we **group nodes based on their horizontal distance (hd)**.
	    - A **TreeMap<Integer, List<Integer>>** is often used to store nodes in vertical order.

	    **Example Tree:**
	                  1
	                /   \
	               2     3
	              / \   / \
	             4   5 6   7
	                  \   
	                   8  
	                  
	    **Vertical Order Representation (hd-based):**
	    ```
	     hd -2: [4]
	     hd -1: [2]
	     hd  0: [1, 5, 6]
	     hd  1: [3, 8]
	     hd  2: [7]
	    ```

	    **Difference between Vertical View & Bottom View:**
	    - **Vertical Order Traversal** prints **all** nodes in each column.
	    - **Bottom View** prints **only the last (deepest) node** at each `hd`.

	    **Applying Vertical Traversal Concept in Bottom View:**
	    - We track nodes using `hd` and **overwrite values as we traverse BFS**, ensuring the bottom-most node remains.
	    - Using a **TreeMap**, the nodes are sorted in order of `hd`, so we extract them for the final answer.

	    **Example Dry Run for Bottom View:**
	    ```
	                  1 (hd=0)
	                /   \
	          (-1) 2     3 (+1)
	             /   \   /   \
	        (-2) 4   5 6     7 (+2)
	                    \   
	                    8 (+1)
	    ```
	    1. Start BFS:
	       - `map = {0: 1}` (Root processed)
	    2. Process Level 2:
	       - `map = {-1: 2, 0: 1, 1: 3}`
	    3. Process Level 3:
	       - `map = {-2: 4, -1: 2, 0: 5, 1: 3, 2: 7}`
	    4. Process Level 4:
	       - `map = {-2: 4, -1: 2, 0: 6, 1: 8, 2: 7}`
	       
	    **Final Bottom View Output:** `[4, 2, 6, 8, 7]`
	    */
	}


}
