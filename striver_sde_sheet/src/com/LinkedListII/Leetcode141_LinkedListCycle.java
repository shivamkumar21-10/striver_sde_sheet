package com.LinkedListII;


public class Leetcode141_LinkedListCycle {
	
	/**
	 * Definition for singly-linked list.
	 * class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) {
	 * val = x;
	 * next = null;
	 * }
	 * }
	 */
	public class Solution {
	    public boolean hasCycle(ListNode head) {
	        /*
	         * Intuition:
	         * - We use two pointers: slow (s) and fast (f).
	         * - The slow pointer moves one step at a time.
	         * - The fast pointer moves two steps at a time.
	         * - If the linked list has a cycle, the fast pointer will eventually meet the slow pointer.
	         * - If there is no cycle, the fast pointer will reach null, and we return false.
	         */

	        ListNode f = head; // Fast pointer
	        ListNode s = head; // Slow pointer

	        while (f != null && f.next != null) { // Ensures the fast pointer can safely move ahead
	            f = f.next.next; // Fast pointer moves 2 steps
	            s = s.next;       // Slow pointer moves 1 step
	            
	            if (f == s) { // If they meet, cycle exists
	                return true;
	            }
	        }

	        return false; // If fast reaches null, no cycle exists
	    }
	}

	/*
	 * Why `f != null && f.next != null` should be in this order:
	 * - `f` represents the fast pointer.
	 * - Before accessing `f.next`, we must ensure that `f` is not null.
	 * - If `f` were null, trying to access `f.next` would cause a `NullPointerException`.
	 * - Hence, checking `f != null` first avoids a potential runtime error.
	 * - The correct order is:
	 *   - First: `f != null` → ensures the fast pointer exists.
	 *   - Second: `f.next != null` → ensures the fast pointer can safely move two steps ahead.
	 * - If we reverse the order (`f.next != null && f != null`), `f.next` would be accessed before confirming `f` is not null, leading to an error.
	 */

	/*
	 * Dry Run Example:
	 * 
	 * Given a linked list with a cycle:
	 * 
	 * 1 → 2 → 3 → 4 → 5 
	 *        ↑         ↓
	 *        8 ← 7 ← 6
	 * 
	 * Step-by-step execution:
	 * 
	 * Initial: 
	 * s = 1, f = 1
	 * 
	 * Iteration 1:
	 * s moves to 2, f moves to 3
	 * 
	 * Iteration 2:
	 * s moves to 3, f moves to 5
	 * 
	 * Iteration 3:
	 * s moves to 4, f moves to 7
	 * 
	 * Iteration 4:
	 * s moves to 5, f moves to 3 (due to the cycle)
	 * 
	 * Iteration 5:
	 * s moves to 6, f moves to 5
	 * 
	 * Iteration 6:
	 * s moves to 7, f moves to 7 (cycle detected!)
	 * 
	 * Since `s == f`, we return `true`, meaning the linked list has a cycle.
	 * 
	 * Time Complexity: O(N) (fast pointer visits each node at most once)
	 * Space Complexity: O(1) (only two extra pointers used)
	 */


}
