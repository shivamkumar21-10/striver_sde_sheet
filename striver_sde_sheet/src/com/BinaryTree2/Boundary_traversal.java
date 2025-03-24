package com.BinaryTree2;
import java.util.*;
public class Boundary_traversal {


	class Solution {
	    
	    /**
	     * Function to check if a node is a leaf node.
	     * A node is a leaf if it has no left and right children.
	     * @param node - the current node to check
	     * @return true if the node is a leaf, false otherwise
	     */
	    boolean isLeaf(Node node){
	        return node.left == null && node.right == null;
	    }
	    
	    /**
	     * Function to add left boundary nodes to the result list (excluding leaf nodes).
	     * 
	     * The left boundary consists of nodes encountered while traversing from 
	     * the root's left child down to the last non-leaf node.
	     * 
	     * Approach:
	     * - Start from the left child of the root.
	     * - Traverse downward following the leftmost child whenever possible.
	     * - If a left child does not exist, take the right child.
	     * - Stop when a leaf node is encountered (leaf nodes are handled separately).
	     * 
	     * Edge Cases:
	     * - If the root has no left child, the left boundary does not exist.
	     * - If all nodes on the left boundary are leaves, they should not be added here.
	     * 
	     * Time Complexity: **O(H)**, where H is the height of the tree.
	     * Space Complexity: **O(1)** (modifies the result list in-place).
	     */
	    void addLeftWithoutleaves(Node node, ArrayList<Integer> res){
	        if (node == null) return; // Edge case: Null tree
	        
	        Node curr = node.left; // Start from left child
	        
	        while (curr != null) {
	            if (!isLeaf(curr)) res.add(curr.data); // Add only non-leaf nodes
	            
	            // Move downward in the left boundary
	            if (curr.left != null) 
	                curr = curr.left;
	            else 
	                curr = curr.right; // If no left child, take the right child
	        }
	    }
	    
	    /**
	     * Function to add right boundary nodes to the result list (excluding leaf nodes).
	     * 
	     * The right boundary consists of nodes encountered while traversing from 
	     * the root's right child down to the last non-leaf node.
	     * 
	     * Approach:
	     * - Start from the right child of the root.
	     * - Traverse downward following the rightmost child whenever possible.
	     * - If a right child does not exist, take the left child.
	     * - Stop when a leaf node is encountered (leaf nodes are handled separately).
	     * - **Store the nodes temporarily** and add them in reverse order.
	     * 
	     * Edge Cases:
	     * - If the root has no right child, the right boundary does not exist.
	     * - If all nodes on the right boundary are leaves, they should not be added here.
	     * 
	     * Time Complexity: **O(H)**
	     * Space Complexity: **O(H)** (temporary storage for reversed nodes).
	     */
	    void addRightWithoutleaves(Node node, ArrayList<Integer> res){
	        if (node == null) return; // Edge case: Null tree
	        
	        Node curr = node.right; // Start from right child
	        ArrayList<Integer> tmp = new ArrayList<>(); // Temporary list to store right boundary
	        
	        while (curr != null) {
	            if (!isLeaf(curr)) tmp.add(curr.data); // Add only non-leaf nodes
	            
	            // Move downward in the right boundary
	            if (curr.right != null) 
	                curr = curr.right;
	            else 
	                curr = curr.left; // If no right child, take the left child
	        }
	        
	        // Add collected right boundary nodes in reverse order (bottom-up)
	        for (int i = tmp.size() - 1; i >= 0; i--) {
	            res.add(tmp.get(i));
	        }
	    }
	    
	    /**
	     * Function to add all leaf nodes (left to right) to the result list.
	     * 
	     * The leaves are nodes that have no children. They should be collected in a 
	     * left-to-right manner using **preorder traversal**.
	     * 
	     * Approach:
	     * - If the current node is a leaf, add it to the result list.
	     * - Recursively traverse the left and right subtrees.
	     * 
	     * Edge Cases:
	     * - If the tree has only one node (which is a leaf), it should be added.
	     * 
	     * Time Complexity: **O(N)** (traverses all nodes).
	     * Space Complexity: **O(1)** (modifies the result list in-place).
	     */
	    void addLeaves(Node root, ArrayList<Integer> res){
	        if (root == null) return; // Base case: Null node
	        
	        if (isLeaf(root)) { // If it's a leaf, add to result
	            res.add(root.data);
	            return;
	        }
	        
	        // Recursively add leaves from left and right subtrees
	        addLeaves(root.left, res);
	        addLeaves(root.right, res);
	    }
	    
	    /**
	     * Main function to compute the boundary traversal of a binary tree.
	     * 
	     * Boundary traversal consists of:
	     * 1. The root node (always part of boundary).
	     * 2. The left boundary (excluding leaf nodes).
	     * 3. All leaf nodes (from left to right).
	     * 4. The right boundary (excluding leaf nodes, added in reverse order).
	     * 
	     * Edge Cases:
	     * - If the root is null, return an empty list.
	     * - If the root is the **only node**, return just that node.
	     * - If there are no left/right subtrees, the boundary consists only of leaves.
	     * 
	     * Time Complexity: **O(N)**
	     * Space Complexity: **O(H)** (recursive stack for leaves and right boundary storage).
	     */
	    ArrayList<Integer> boundaryTraversal(Node node) {
	        ArrayList<Integer> ans  = new ArrayList<>();
	        
	        if (node == null) return ans; // Edge case: Empty tree

	        // Add the root node (always part of boundary)
	        ans.add(node.data);
	        
	        // If the root itself is a leaf, return it immediately
	        if (isLeaf(node)) return ans;

	        // Add left boundary (excluding leaf nodes)
	        addLeftWithoutleaves(node, ans);
	        
	        // Add all leaf nodes (left to right)
	        addLeaves(node, ans);
	        
	        // Add right boundary (excluding leaf nodes, added in reverse order)
	        addRightWithoutleaves(node, ans);
	        
	        return ans;
	    }
	}

}
