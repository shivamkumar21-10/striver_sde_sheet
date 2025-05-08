package com.BinaryTree;
import java.util.*;
public class TopView {


	class Solution {
		
	    // A helper class to store a tree node along with its horizontal distance (hd)
	    static class Pair {
	        Node node; // The tree node
	        int hd;    // Horizontal distance of the node from the root

	        // Constructor to initialize Pair object with a node and its horizontal distance
	        public Pair(Node node, int hd) {
	            this.node = node;
	            this.hd = hd;
	        }
	    }

	    static ArrayList<Integer> topView(Node root) {
	        /*
	         * ========== Observations and Problem Breakdown ==========
	         * 
	         * ➤ The **top view** of a binary tree consists of the first (top-most) node 
	         *   that appears at every horizontal distance (hd) when the tree is viewed from above.
	         *   
	         * ➤ The **horizontal distance (hd)**:
	         *   - **Root node** → hd = 0
	         *   - **Left child** → hd = hd - 1
	         *   - **Right child** → hd = hd + 1
	         *   
	         * ➤ If multiple nodes share the same hd:
	         *   - The **first node encountered at that hd** in level-order traversal 
	         *     (i.e., the top-most node) is selected.
	         * 
	         * ➤ To efficiently store and retrieve nodes based on their hd, we use:
	         *   - **TreeMap<Integer, Integer> map** → Maintains nodes sorted by hd.
	         *   - **Queue<Pair> q** → Used for **level-order traversal** (BFS).
	         * 
	         * ➤ **Approach (BFS - Level Order Traversal)**
	         *   1. Traverse the tree using a **queue** (FIFO structure) for level-order traversal.
	         *   2. Maintain a **TreeMap** to store the **first encountered** node for each hd.
	         *   3. If an hd appears for the first time → Store it in the map.
	         *   4. Add left and right children of each node into the queue with updated hds.
	         *   5. Extract the nodes from the map and return as the **top view** list.
	         */

	        ArrayList<Integer> ans = new ArrayList<>(); // List to store the final top view

	        Map<Integer, Integer> map = new TreeMap<>(); // Sorted map to store first nodes encountered at each hd
	        Queue<Pair> q = new LinkedList<>(); // Queue for BFS traversal (FIFO order)

	        // Start BFS traversal from the root node with horizontal distance 0
	        q.add(new Pair(root, 0));

	        while (!q.isEmpty()) {
	            /*
	             * Processing each node in level-order traversal.
	             * Dequeue the front node from the queue and retrieve its hd.
	             */
	            Pair pair = q.poll();
	            Node node = pair.node;
	            int hd = pair.hd;

	            /*
	             * Check if this horizontal distance (hd) has been encountered before.
	             * - If NOT → Store the node data in the map (since it's the first node at this hd).
	             * - If YES → Ignore it, as the first node is already stored.
	             */
	            if (!map.containsKey(hd)) {
	                map.put(hd, node.data);
	            }

	            /*
	             * Enqueue the left child of the current node:
	             * - Assign its hd = hd - 1 (moving left decreases hd).
	             */
	            if (node.left != null) {
	                q.add(new Pair(node.left, hd - 1));
	            }

	            /*
	             * Enqueue the right child of the current node:
	             * - Assign its hd = hd + 1 (moving right increases hd).
	             */
	            if (node.right != null) {
	                q.add(new Pair(node.right, hd + 1));
	            }
	        }

	        /*
	         * Extracting values from the TreeMap and storing in the result list.
	         * - The TreeMap ensures values are stored in sorted order of hd.
	         * - This maintains the **left-to-right order** for top view.
	         */
	        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	            ans.add(entry.getValue());
	        }

	        return ans; // Returning the final top view list
	    }
	}

	/*
	 * ======================= Dry Run Example =======================
	 * Consider the binary tree:
	 * 
	 *            1
	 *          /   \
	 *         2     3
	 *        / \     \
	 *       4   5     6
	 *          /
	 *         7
	 * 
	 * Step-by-step BFS traversal:
	 * - Process **1 (hd = 0)** → Store in map.
	 * - Process **2 (hd = -1)** → Store in map.
	 * - Process **3 (hd = 1)** → Store in map.
	 * - Process **4 (hd = -2)** → Store in map.
	 * - Process **5 (hd = 0)** → Ignored (since 1 is already stored at hd=0).
	 * - Process **6 (hd = 2)** → Store in map.
	 * - Process **7 (hd = -1)** → Ignored (since 2 is already stored at hd=-1).
	 * 
	 * **Final Top View Output:** [4, 2, 1, 3, 6]
	 * 
	 * =============================================================
	 * 
	 * ========== Explanation of Vertical View and Its Usage ==========
	 * 
	 * - **What is a Vertical View?**
	 *   - It groups nodes based on their **horizontal distance (hd)**.
	 *   - Nodes at the same hd are **vertically aligned**.
	 * 
	 * - **How is Vertical View Used Here?**
	 *   - The **TreeMap** maintains nodes sorted by hd.
	 *   - The first node encountered at each hd is stored.
	 *   - This helps in extracting the **top-most node** from each vertical column.
	 * 
	 * - **Difference Between Top View and Bottom View:**
	 *   - **Top View** → The first (top-most) node at each hd is selected.
	 *   - **Bottom View** → The last (bottom-most) node at each hd is selected.
	 * 
	 * - **Why TreeMap?**
	 *   - It keeps the hds sorted (ensuring left-to-right order).
	 *   - Allows **O(log N) insertion** and **O(N) traversal** for extracting the result.
	 */

}
