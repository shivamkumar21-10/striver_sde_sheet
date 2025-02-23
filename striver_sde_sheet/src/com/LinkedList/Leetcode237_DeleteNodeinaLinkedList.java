package com.LinkedList;

public class Leetcode237_DeleteNodeinaLinkedList {
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public void deleteNode(ListNode node) {
	        /*
	         * Intuition:
	         * Normally, to delete a node in a singly linked list, we need access to its previous node.
	         * However, in this problem, we are only given the node to be deleted, not its predecessor.
	         * 
	         * The trick here is to **copy the value of the next node** into the current node
	         * and then bypass the next node by adjusting the pointer.
	         * This effectively "deletes" the given node without needing access to the previous one.
	         * 
	         * Constraints:
	         * - The given node is not the last node in the list.
	         */

	        // Step 1: Store the next node reference
	        ListNode p = node.next;
	        
	        // Step 2: Copy the value of the next node into the current node
	        node.val = p.val;
	        
	        // Step 3: Skip the next node by pointing `node.next` to `p.next`
	        node.next = p.next;

	        // Step 4: (Optional) Disconnect `p` from the list to free memory (not necessary in Java)
	        p.next = null;
	    }
	}

	/*
	 * Dry Run:
	 * 
	 * Example:
	 * Input: head = [4,5,1,9], node = 5
	 * 
	 * Step 1: Before deletion:
	 * 4 -> 5 -> 1 -> 9
	 *        ^
	 *       (Given node)
	 *
	 * Step 2: Copy the value of next node (1) into 5
	 * 4 -> 1 -> 1 -> 9
	 *
	 * Step 3: Adjust pointers to skip the duplicate node
	 * 4 -> 1 -> 9
	 *
	 * Output: [4,1,9] (5 is deleted)
	 *
	 * Edge Cases Considered:
	 * - The given node is never the last node (ensured by constraints).
	 * - Works in O(1) time since only a few pointer updates are required.
	 */


}
