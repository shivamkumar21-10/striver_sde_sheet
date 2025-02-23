package com.LinkedList;

public class Leetcode876_MiddleoftheLinkedList {
	
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
	     * - We need to find the middle node of a singly linked list.
	     * - If the list has an odd number of nodes, return the exact middle node.
	     * - If the list has an even number of nodes, return the second middle node.
	     * 
	     * Approach:
	     * - We use **two pointers**: `slow` and `fast`.
	     * - `slow` moves **one step** at a time.
	     * - `fast` moves **two steps** at a time.
	     * - When `fast` reaches the end of the list (or `null`), `slow` will be at the middle.
	     *
	     * Explanation:
	     * - If `n` is odd (e.g., 5 nodes: 1 → 2 → 3 → 4 → 5), `slow` stops at node `3`.
	     * - If `n` is even (e.g., 6 nodes: 1 → 2 → 3 → 4 → 5 → 6), `slow` stops at node `4`.
	     *
	     * Time Complexity: O(n)  - We traverse each node at most once.
	     * Space Complexity: O(1) - We use only two pointers, no extra space.
	     */
	    public ListNode middleNode(ListNode head) {
	        ListNode fast = head; // Fast pointer moves two steps at a time
	        ListNode slow = head; // Slow pointer moves one step at a time

	        // Move fast pointer two steps and slow pointer one step until fast reaches the end
	        while (fast != null && fast.next != null) {
	            slow = slow.next;       // Move slow one step
	            fast = fast.next.next;  // Move fast two steps
	        }

	        return slow; // `slow` now points to the middle node
	    }
	}


}
