package com.LinkedList;

public class Leetcode19RemoveNthNodeFromEndofList {
	
	//one solution come in mind is find length of linkedlist(N) by traversing and keep counter increaing
	//now you can do is given length of linkedlist - given n ie, N-n th element from from start
	
	//one edge case is if n==N then just do head  = head.next
	
	//Time complexity O(N) + O(N) - but given in problem to do it in single pass
	
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
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	        // Intuition:
	        // The problem requires us to remove the Nth node from the end of the linked list.
	        // A naive approach would be:
	        // 1. Traverse the list to count the number of nodes.
	        // 2. Find the (length - N)th node from the start and remove it.
	        // However, this requires two traversals: one for counting and another for deletion.
	        // 
	        // To optimize, we use the **two-pointer technique** (slow and fast pointers) to find the target in one traversal:
	        // - Move `fast` pointer N steps ahead first.
	        // - Then move both `fast` and `slow` together until `fast` reaches the last node.
	        // - `slow` will be just before the target node, allowing us to remove it efficiently.

	        // Step 1: Create a dummy node to handle edge cases (e.g., removing the head node).
	        ListNode dummy = new ListNode(0, head);
	        ListNode s = dummy; // Slow pointer
	        ListNode f = dummy; // Fast pointer

	        // Step 2: Move the fast pointer `n` steps ahead
	        while (n > 0 && f.next != null) {
	            f = f.next;
	            n--;
	        }

	        // Step 3: Move both pointers together until fast reaches the last node
	        while (f.next != null) {
	            f = f.next;
	            s = s.next;
	        }

	        // Step 4: Remove the Nth node from the end
	        s.next = s.next.next;

	        // Step 5: Return the modified list skipping the dummy node
	        return dummy.next;
	    }
	}

	/*
	 * Dry Run:
	 *
	 * Example:
	 * Input: head = [1,2,3,4,5], n = 2
	 *
	 * Step 1: Create a dummy node
	 * dummy -> 1 -> 2 -> 3 -> 4 -> 5
	 * s = dummy, f = dummy
	 *
	 * Step 2: Move fast pointer `n` steps ahead
	 * Move 1 step: f = 1
	 * Move 2 steps: f = 2
	 *
	 * Step 3: Move both pointers together until fast reaches the last node
	 * s = 1, f = 3
	 * s = 2, f = 4
	 * s = 3, f = 5 (fast reaches last node)
	 *
	 * Step 4: Remove the target node
	 * Before: 1 -> 2 -> 3 -> 4 -> 5
	 * Remove 4: 1 -> 2 -> 3 -> 5
	 *
	 * Output: [1,2,3,5]
	 */

	
	
//	Why Slow and Fast Pointers?
//			1. One Pass Optimization:
//
//			A brute-force solution would require two passes: first to find the length and second to find the node to delete.
//			By using slow and fast pointers, we eliminate the first pass and find the target node in one traversal.
//			
//			2. Maintaining a Fixed Distance:
//				
//			Moving fast n steps ahead ensures that when fast reaches the last node, slow is positioned just before the target node.
//			
//			3. Handles Edge Cases Gracefully:
//
//			Using a dummy node allows us to remove the head node without additional conditions.
//			Works for various cases, including single-node lists or removing the last node.
//			This method is efficient (O(N) time, O(1) space) and avoids unnecessary calculations. 

}
