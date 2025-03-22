package com.BinaryTree;

import java.util.*;

public class Leetcode987_VerticalOrderTraversalofaBinaryTree {
	
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
	     * This helper class (Tuple) helps in BFS traversal.
	     * It stores:
	     * - The `node`: Current TreeNode being processed.
	     * - `row (y)`: The depth (or level) of the node.
	     * - `col (x)`: The horizontal position of the node in the tree.
	     */
	    class Tuple {
	        TreeNode node;
	        int row;  // Represents depth in the tree (y-coordinate)
	        int col;  // Represents horizontal position (x-coordinate)

	        Tuple(TreeNode node, int row, int col) {
	            this.node = node;
	            this.row = row;
	            this.col = col;
	        }
	    }

	    /**
	     * Returns a list of vertical order traversal of a binary tree.
	     * 
	     * @param root The root of the binary tree.
	     * @return A list of lists containing node values in vertical order.
	     */
	    public List<List<Integer>> verticalTraversal(TreeNode root) {
	        // TreeMap ensures columns (x-coordinates) are stored in sorted order.
	        /**
	         * हम एक complex data structure का उपयोग कर रहे हैं:
	         * 
	         * TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map
	         * 
	         * 1. **Outer TreeMap<Integer, ...>** → x-coordinates store करता है (column-wise sorting)
	         * 2. **Inner TreeMap<Integer, PriorityQueue<Integer>>** → y-coordinates (row-wise sorting)
	         * 3. **PriorityQueue<Integer>** → अगर एक ही (x, y) पर multiple nodes हों तो उन्हें ascending order में रखने के लिए
	         */
	        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

	        // Queue for BFS traversal
	        Queue<Tuple> q = new LinkedList<>();
	        q.offer(new Tuple(root, 0, 0)); // Start BFS from root at (0,0)

	        /**
	         * BFS Traversal:
	         * - We process each node level by level.
	         * - We store nodes column-wise in `map`.
	         * - If two nodes are at the same column and row, they are sorted using PriorityQueue.
	         */
	        while (!q.isEmpty()) {
	            Tuple tuple = q.poll();
	            TreeNode node = tuple.node;
	            int x = tuple.col; // Horizontal position
	            int y = tuple.row; // Depth level

	            /**
	             * Why are we using (x, y) coordinates?
	             * 
	             * - `x` (column) tells us which vertical line the node belongs to.
	             * - `y` (row) tells us how deep the node is in the tree.
	             * - Nodes with the same `x` coordinate are in the same vertical line.
	             * - If multiple nodes have the same (x, y), they are stored in ascending order.
	             * 
	             * Visualization of (x, y) mapping:
	             * 
	             *        1 (0,0)
	             *       /   \
	             *  (2,-1,1)  (3,1,1)
	             *     /  \      /  \
	             * (4,-2,2) (5,0,2) (6,0,2) (7,2,2)
	             * 
	             * Storing these (x, y) pairs allows us to sort and organize them
	             * properly to construct the vertical order traversal.
	             */

	            // If column `x` is not in the map, add it
	            map.putIfAbsent(x, new TreeMap<>());

	            // If row `y` is not in column `x`, add a PriorityQueue to maintain sorting order
	            map.get(x).putIfAbsent(y, new PriorityQueue<>());

	            // Store the node value in PriorityQueue to maintain sorted order for same (x, y)
	            map.get(x).get(y).offer(node.val);

	            /**
	             * BFS ensures that:
	             * - We process nodes from left to right at each level.
	             * - Lower `x` values get processed before higher `x` values.
	             * - Nodes with the same `x` and `y` are sorted using PriorityQueue.
	             */

	            // Left child: Move left in tree (x decreases), go to next level (y increases)
	            if (node.left != null) {
	                q.offer(new Tuple(node.left, y + 1, x - 1));
	            }

	            // Right child: Move right in tree (x increases), go to next level (y increases)
	            if (node.right != null) {
	                q.offer(new Tuple(node.right, y + 1, x + 1));
	            }
	        }

	        /**
	         * Construct the final output list:
	         * - Iterate over the TreeMap (sorted order of x).
	         * - For each column, extract the nodes in row order.
	         */
	        List<List<Integer>> result = new ArrayList<>();
	        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
	            result.add(new ArrayList<>()); // Create new list for this column

	            for (PriorityQueue<Integer> nodes : ys.values()) {
	                while (!nodes.isEmpty()) {
	                    result.get(result.size() - 1).add(nodes.poll()); // Retrieve sorted nodes
	                }
	            }
	        }

	        return result;
	    }
	}

	/**
	 * Detailed Explanation of `x` and `y` Coordinate System:
	 * 
	 * 1. **Understanding `x` (Horizontal Distance)**
	 *    - `x` represents how far left or right a node is.
	 *    - Left child: `x - 1`
	 *    - Right child: `x + 1`
	 *    - Nodes with the same `x` coordinate are in the same vertical column.
	 * 
	 * 2. **Understanding `y` (Vertical Depth)**
	 *    - `y` represents the depth of the node in the tree.
	 *    - Root starts at `y = 0`.
	 *    - Each child moves `y + 1` when going down.
	 * 
	 * 3. **Example Dry Run**
	 * 
	 * Given Tree:
	 * 
	 *            1
	 *          /   \
	 *         2     3
	 *        / \   / \
	 *       4   6 5   7
	 *          /
	 *         8
	 * 
	 * The nodes get mapped as:
	 * 
	 * | Node | x  | y  |
	 * |------|----|----|
	 * |  1   | 0  | 0  |
	 * |  2   | -1 | 1  |
	 * |  3   | 1  | 1  |
	 * |  4   | -2 | 2  |
	 * |  6   | 0  | 2  |
	 * |  5   | 0  | 2  |
	 * |  7   | 2  | 2  |
	 * |  8   | -1 | 3  |
	 * 
	 * **TreeMap Storage**
	 * 
	 * ```
	 * {
	 *   -2: {2: [4]},
	 *   -1: {1: [2], 3: [8]},
	 *    0: {0: [1], 2: [5, 6]},
	 *    1: {1: [3]},
	 *    2: {2: [7]}
	 * }
	 * ```
	 * 
	 * **Final Output:**
	 * ```
	 * [
	 *   [4],      // -2
	 *   [2, 8],   // -1
	 *   [1, 5, 6],//  0
	 *   [3],      //  1
	 *   [7]       //  2
	 * ]
	 * ```
	 * 
	 * **Why Sorting?**
	 * - `x` values (TreeMap) ensure columns are processed from left to right.
	 * - `y` values (TreeMap) ensure nodes are processed from top to bottom.
	 * - `PriorityQueue` ensures that if multiple nodes share (x, y), they are sorted by value.
	 * 
	 * **Time Complexity Analysis**
	 * - **BFS Traversal**: `O(N)`
	 * - **TreeMap Insertions**: `O(N log N)`
	 * - **PriorityQueue Operations**: `O(log N) per insertion`
	 * - **Overall Complexity**: `O(N log N)`
	 */
	
	 /* **Final Thoughts:**
	 * 1. इस approach से हमें एकदम sorted vertical order traversal मिलता है।
	 * 2. BFS ensures कि हर level के nodes सही order में process हों।
	 * 3. PriorityQueue lexicographical sorting का काम कर देता है।
	 * 
	 * अब यह code बहुत efficient और readable हो गया! 🚀
	 */


}
