package com.LinkedListII;

public class Leetcode430_FlattenaMultilevelDoublyLinkedList {
	
	/*
	// Definition for a Node.
	class Node {
	    public int val;
	    public Node prev;
	    public Node next;
	    public Node child;
	};
	*/

	class Solution {
	    /**
	     * Intuition:
	     * - We have a **doubly linked list** where each node has a `next` pointer and an optional `child` pointer.
	     * - Our goal is to **flatten** this list such that all child lists are integrated into the main list,
	     *   maintaining the depth-first order (process each child before moving to the next node).
	     * - We traverse the list **iteratively** to find nodes with children and properly integrate them.
	     * - The key challenge is correctly inserting the **child sublist** while maintaining the doubly linked list structure.
	     * - To do this, we find the **tail** (last node) of the child sublist before linking it back to the main list.
	     * 
	     * Time Complexity: **O(N)** (Each node is visited once)
	     * Space Complexity: **O(1)** (No extra space is used; we modify the list in-place)
	     */
	    public Node flatten(Node head) {
	        Node curr = head; // Start traversal from the head
	        
	        while(curr != null) {
	            if(curr.child != null) { // If the current node has a child list
	                Node tail = findTail(curr.child); // Find the last node of the child list

	                /**
	                 * Why do we need to find the tail?
	                 * - The tail is the last node in the child sublist.
	                 * - This helps us properly **reconnect** the remaining part of the original list.
	                 * - Once we merge the child list into the main list, the tail should connect to `curr.next`.
	                 * - This ensures that we maintain the doubly linked list structure.
	                 */

	                // If there is a next node after 'curr', connect it to the tail of the child list
	                if(curr.next != null) {
	                    curr.next.prev = tail; // Establish backward link from next node to tail
	                }
	                
	                tail.next = curr.next; // Connect tail’s next to curr's original next
	                curr.next = curr.child; // Connect curr’s next to its child (flattening the list)
	                curr.child.prev = curr; // Establish backward link from child to curr
	                curr.child = null; // Remove the child pointer after flattening
	            }
	            curr = curr.next; // Move to the next node in the flattened list
	        }
	        return head; // Return the modified head of the list
	    }
	    
	    /**
	     * Finds the last node (tail) of the given child list.
	     * This helps in linking the child list correctly with the main list.
	     * 
	     * @param child The head of the child sublist
	     * @return The tail (last node) of the child sublist
	     */
	    public Node findTail(Node child) {
	        /**
	         * Why do we find the tail?
	         * - The last node of the child list (`tail`) is crucial for reconnecting the `next` nodes.
	         * - If we insert the child list **without finding the tail**, the original `next` pointers would be lost.
	         * - By finding the tail, we ensure that after inserting the child list, we can properly attach the remaining nodes.
	         * 
	         * Example:
	         * Given:
	         * 1 - 2 - 3 - 4 - 5
	         *         |
	         *         6 - 7
	         *         |
	         *         8 - 9
	         *
	         * - When we process node `3`, we must insert `6 -> 7 -> 8 -> 9` before `4`.
	         * - The **tail of the child list is `9`**, so we connect `9.next = 4`.
	         * - If we don’t find the tail, `4` would be disconnected.
	         */

	        while(child.next != null) { // Traverse till the last node in the child list
	            child = child.next;
	        }
	        return child; // Return the tail node
	    }
	}

	/**
	 * Dry Run (Step-by-Step Explanation):
	 * 
	 * Given:
	 * 1 - 2 - 3 - 4 - 5
	 *         |
	 *         6 - 7
	 *         |
	 *         8 - 9
	 * 
	 * Initial state:
	 * - Nodes `1 -> 2 -> 3 -> 4 -> 5` exist as a doubly linked list.
	 * - Node `3` has a child list: `6 -> 7`, and node `7` has a child list: `8 -> 9`.

	 * Step 1: Processing Node `3`
	 * - `curr = 3`, `curr.child = 6`
	 * - Find tail of `6 -> 7 -> 8 -> 9` → `tail = 9`
	 * - Connect `tail.next = 4` (attach the tail of child list to 3's next node)
	 * - Set `3.next = 6`, `6.prev = 3`, and remove `3.child` pointer
	 * - The list now looks like: `1 -> 2 -> 3 -> 6 -> 7 -> 8 -> 9 -> 4 -> 5`

	 * Step 2: Processing Node `6`, `7`, `8`, `9`
	 * - These nodes don’t have children, so we continue moving forward.

	 * Step 3: Processing Node `4`, `5`
	 * - No child nodes, so the process completes.

	 * Final Flattened List:
	 * 1 - 2 - 3 - 6 - 7 - 8 - 9 - 4 - 5
	 *
	 * The original child links are removed, and the list is now fully connected using only `next` and `prev` pointers.
	 */


}
