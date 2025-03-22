package com.LinkedListII;

public class GFG_FlatteningaLinkedList {
	
	class Solution {
	    
	    /**
	     * Intuition:
	     * - The given structure is a **linked list of linked lists**, where:
	     *   - `next` pointer connects different linked lists.
	     *   - `bottom` pointer connects nodes within a single sublist.
	     * - Our goal is to **flatten this 2D linked list** into a single sorted linked list using only the `bottom` pointer.
	     * - The **best approach** is to use **recursion and merging** similar to **Merge Sort**:
	     *   1. Recursively flatten the `next` linked lists first.
	     *   2. Merge the current list with the already flattened list using a **sorted merge**.
	     *   3. This approach ensures that we always deal with a sorted structure.
	     */

	    /**
	     * Merges two sorted linked lists based on the bottom pointer.
	     * Similar to merging two sorted lists in Merge Sort.
	     * 
	     * @param a First sorted linked list
	     * @param b Second sorted linked list
	     * @return The head of the merged sorted list
	     */
	    Node mergeNode(Node a, Node b){
	        Node temp = new Node(0); // Dummy node to store the merged list
	        Node res = temp; // Pointer to traverse the merged list
	        
	        // Merging two sorted lists based on bottom pointer
	        while(a != null && b != null){
	            if(a.data <= b.data){ // If a's value is smaller, add 'a' to result list
	                temp.bottom = a; 
	                temp = temp.bottom; // Move temp pointer
	                a = a.bottom; // Move to the next node in 'a'
	            }
	            else{ // If b's value is smaller, add 'b' to result list
	                temp.bottom = b; 
	                temp = temp.bottom; // Move temp pointer
	                b = b.bottom; // Move to the next node in 'b'
	            }
	        }
	        
	        // If nodes remain in 'a', append them
	        if(a != null) temp.bottom = a;
	        else temp.bottom = b; // If nodes remain in 'b', append them
	        
	        return res.bottom; // Return the merged sorted list (skip dummy node)
	    }
	    
	    /**
	     * Flattens a multi-level linked list into a single sorted list.
	     * 
	     * Intuition:
	     * - Instead of merging all lists at once, we reduce the problem size by **recursively flattening the right side first**.
	     * - This converts the problem into merging two sorted lists at each step.
	     * - By the time we reach the first list, all `next` lists have already been flattened into a single list.
	     * - Merging step-by-step ensures that we always merge two sorted lists, keeping operations efficient.
	     * 
	     * Steps:
	     * 1. Recursively flatten the `next` linked lists.
	     * 2. Merge the current list with the already flattened list.
	     * 3. The recursion ensures that each step reduces the number of lists by 1.
	     * 4. This continues until we get a completely flattened linked list.
	     * 
	     * @param root Head of the linked list
	     * @return The head of the completely flattened linked list
	     */
	    Node flatten(Node root) {
	        // Base Case: If the list is empty or contains only one list, return as is.
	        if(root == null || root.next == null){
	            return root;
	        }
	        
	        // Recursively flatten the rest of the list (right side)
	        root.next = flatten(root.next);
	        
	        // Merge the current list with the already flattened right list
	        root = mergeNode(root, root.next);
	        
	        return root; // Return the fully merged list
	    }
	}

	/**
	 * Dry Run:
	 * Given:
	 * 5 -> 10 -> 19 -> 28
	 * |    |     |     |
	 * 7    20    22    35
	 * |          |     |
	 * 8          50    40
	 * |                |
	 * 30               45
	 * 
	 * Step 1: Recursively flatten right lists
	 *   - Flatten(28) → Already flattened as single list
	 *   - Merge(19, Flatten(28)) → Merges (19, 22, 35, 40, 45, 50)
	 *   - Merge(10, Flatten(19)) → Merges (10, 19, 20, 22, 35, 40, 45, 50)
	 *   - Merge(5, Flatten(10)) → Final merged list: (5, 7, 8, 10, 19, 20, 22, 28, 30, 35, 40, 45, 50)
	 *
	 * Time Complexity: O(N * log K), where:
	 *   - N is the total number of nodes.
	 *   - K is the number of linked lists.
	 *   - Merging takes O(N), and since we're recursively reducing the list count, it follows a logarithmic pattern.
	 */  


}
