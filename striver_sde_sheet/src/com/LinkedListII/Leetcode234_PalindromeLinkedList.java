package com.LinkedListII;

public class Leetcode234_PalindromeLinkedList {
	
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
	     * This method reverses a linked list.
	     * The idea is to iterate through the list, reversing the direction of each node's next pointer.
	     * 
	     * Intuition:
	     * - We need to reverse the second half of the list to check for palindrome properties.
	     * - We use three pointers: prev (to store the reversed portion), curr (current node), and next (to track the next node).
	     */
	    public ListNode reverse(ListNode head) {
	        ListNode prev = null;
	        ListNode curr = head;
	        while(curr != null) {
	            ListNode next = curr.next; // Store next node
	            curr.next = prev;          // Reverse the link
	            prev = curr;               // Move prev to current
	            curr = next;               // Move to next node
	        }
	        return prev; // New head of reversed list
	    }

	    /**
	     * This method checks if a given linked list is a palindrome.
	     * 
	     * Intuition:
	     * - A palindrome is symmetric, meaning the first half should match the second half in reverse.
	     * - We use two pointers (slow and fast) to find the middle of the list.
	     * - Then, we reverse the second half and compare it with the first half.
	     * - If they match, the list is a palindrome; otherwise, it isn't.
	     */
	    public boolean isPalindrome(ListNode head) {
	        if (head == null || head.next == null) return true; // Edge case: Empty list or single element is always a palindrome

	        ListNode f = head.next; // Fast pointer (moves 2 steps)
	        ListNode s = head;      // Slow pointer (moves 1 step)

	        /**
	         * Why initialize f as head.next instead of head?
	         * - If we initialize f = head, the loop condition (f != null && f.next != null) may cause unnecessary iterations.
	         * - By setting f = head.next, we ensure that:
	         *    - In an even-length list, s stops at the first middle node.
	         *    - In an odd-length list, s stops at the exact middle node.
	         * - This prevents incorrect splitting of the list into two halves.
	         */

	        // Step 1: Find the middle of the list using slow and fast pointers
	        while(f!=null && f.next != null){
	            s = s.next;
	            f = f.next.next;
	        }

	        // Step 2: Reverse the second half of the list
	        ListNode rev = reverse(s.next);

	        // Step 3: Compare both halves
	        while(rev != null){
	            if(head.val != rev.val) return false; // If mismatch, it's not a palindrome
	            head = head.next;
	            rev = rev.next;
	        }
	        return true; // All values matched, it's a palindrome
	    }
	}

	/*
	 * Dry Run Example:
	 * Input: 1 → 2 → 3 → 2 → 1
	 *
	 * Step 1: Find Middle
	 * Slow (s) moves: 1 → 2 → 3
	 * Fast (f) moves: 2 → 1 (stops at the last node)
	 * Middle node: 3
	 *
	 * Step 2: Reverse second half
	 * Original second half: 3 → 2 → 1
	 * Reversed second half: 1 → 2 → 3
	 *
	 * Step 3: Compare halves
	 * 1 (head) == 1 (reversed) ✅
	 * 2 (head) == 2 (reversed) ✅
	 * 3 (head) == 3 (reversed) ✅
	 * All match, so it's a palindrome.
	 *
	 * Time Complexity: O(N) - We traverse the list twice (once to find the middle, once to reverse and compare).
	 * Space Complexity: O(1) - We modify the list in place.
	 */


}
