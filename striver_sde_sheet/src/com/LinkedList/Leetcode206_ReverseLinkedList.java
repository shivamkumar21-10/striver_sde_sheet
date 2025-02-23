package com.LinkedList;

public class Leetcode206_ReverseLinkedList {
	
//	Reversing a linked list means changing the direction of the next pointers so that the last node becomes the head, and the head becomes the last node. Instead of moving forward through the list, we reverse the links, making each node point to its previous node.
//
//	We maintain three pointers:
//
//	prev → Initially null, it keeps track of the reversed portion.
//	curr → Initially head, it represents the node being processed.
//	next → Stores curr.next before modifying links to avoid losing access to the remaining list.
//	We iterate through the list, adjusting the next pointers of each node to point backward until all links are reversed.
	
	 //Input
//	1 → 2 → 3 → 4 → 5 → null
	
//	output
//	null ← 1 ← 2 ← 3 ← 4 ← 5
	
	//only diff is reverse the link and 1 should point to null and head should point to 5
	
	/*
    Dry Run for Reversing a Linked List
    -----------------------------------

    Input:  1 → 2 → 3 → 4 → 5 → null
    Expected Output:  5 ← 4 ← 3 ← 2 ← 1 ← null

    Initial State:
    prev = null, curr = head (1), next = null

    Step 1:
    curr = 1, next = 2 (saved for next iteration)
    curr.next = prev (null)  →  1 ← null
    prev = 1, curr = 2

    Step 2:
    curr = 2, next = 3
    curr.next = prev (1)  →  2 ← 1
    prev = 2, curr = 3

    Step 3:
    curr = 3, next = 4
    curr.next = prev (2)  →  3 ← 2 ← 1
    prev = 3, curr = 4

    Step 4:
    curr = 4, next = 5
    curr.next = prev (3)  →  4 ← 3 ← 2 ← 1
    prev = 4, curr = 5

    Step 5:
    curr = 5, next = null
    curr.next = prev (4)  →  5 ← 4 ← 3 ← 2 ← 1
    prev = 5, curr = null (loop ends)

    Final State:
    head = prev (5) → 5 ← 4 ← 3 ← 2 ← 1 ← null
*/

	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 */

	class Solution {
	    /**
	     * Intuition:
	     * Reversing a linked list means changing the direction of the next pointers
	     * so that the last node becomes the new head and the head becomes the last node.
	     * We need to update each node’s `next` pointer to point to its previous node.
	     * 
	     * Approach:
	     * - We use three pointers: `prev`, `head`, and `next`.
	     * - `prev` keeps track of the reversed part of the list.
	     * - `head` is the current node being processed.
	     * - `next` is used to temporarily store the next node before changing the link.
	     * - We iterate through the list, modifying each node's `next` pointer to point backward.
	     * - Finally, the `prev` pointer will hold the new head of the reversed list.
	     *
	     * Time Complexity: O(n)  - We traverse each node once.
	     * Space Complexity: O(1) - We use only constant extra space.
	     */
	    public ListNode reverseList(ListNode head) {
	        ListNode prev = null;  // Initially, previous is null because the new tail will point to null.

	        while (head != null) {  // Traverse the list until we reach the end.
	            ListNode next = head.next;  // Save the next node before changing the link.
	            head.next = prev;  // Reverse the link: make current node point to the previous node.
	            prev = head;  // Move `prev` to the current node.
	            head = next;  // Move `head` forward to the next node.
	        }

	        return prev;  // `prev` is now the new head of the reversed list.
	    }
	}


}
