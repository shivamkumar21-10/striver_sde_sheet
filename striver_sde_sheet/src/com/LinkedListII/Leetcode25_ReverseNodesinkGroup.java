package com.LinkedListII;

public class Leetcode25_ReverseNodesinkGroup {
	
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
	    public ListNode reverseKGroup(ListNode head, int k) {
	        // Edge case: If the list is empty or k is 1 (which means no change needed), return the head as it is.
	        if (head == null || k == 1) return head;

	        // A dummy node is used to simplify operations at the head of the list.
	        // This ensures we don't lose track of the modified list structure after the first reversal.
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;

	        // `curr` - Used to traverse through the list
	        // `nex` - Used to track the node that will be moved
	        // `pre` - Marks the node before the sublist being reversed
	        ListNode curr = dummy, nex = dummy, pre = dummy;
	        int count = 0;

	        // Step 1: Count the total number of nodes in the linked list
	        while (curr.next != null) {
	            curr = curr.next;
	            count++;
	        }

	        // Step 2: Reverse nodes in groups of k as long as at least k nodes remain
	        while (count >= k) {
	            curr = pre.next; // The first node of the current k-group
	            nex = curr.next; // The second node of the current k-group

	            // Step 3: Reverse k nodes using a loop
	            for (int i = 1; i < k; i++) { 
	                // Detach `nex` from its current position and move it to the front of the sublist
	                curr.next = nex.next;  // Skip over `nex` in its original position
	                nex.next = pre.next;   // Attach `nex` at the beginning of the reversed segment
	                pre.next = nex;        // Update the previous group's last node to point to `nex`
	                nex = curr.next;       // Move `nex` forward to the next node to be processed
	            }

	            // Move `pre` to `curr` since `curr` is now the last node of the reversed group
	            pre = curr;

	            // Reduce the count by k since we processed `k` elements
	            count -= k;
	        }

	        // Return the new head of the modified list
	        return dummy.next;
	    }
	}

	/**
	 * Intuition:
	 * - The problem requires us to reverse every k consecutive nodes in a linked list.
	 * - If the number of remaining nodes is less than k, they should remain unchanged.
	 * - The challenge is to reverse the nodes in-place without extra space.
	 * - The approach involves carefully adjusting node links to achieve the desired reversal.
	 * - We use a dummy node to simplify the reversal process at the head of the list.

	 * Logic Breakdown:
	 * - Step 1: First, we traverse the list once to count the total number of nodes.
	 * - Step 2: We only proceed with reversal if there are at least k nodes left.
	 * - Step 3: Using three pointers (`pre`, `curr`, and `nex`), we reverse k nodes at a time.
	 * - Step 4: After reversing one group, we move `pre` forward to prepare for the next group.
	 * - Step 5: The process continues until we run out of k-sized groups.
	 * - Step 6: The dummy node ensures we maintain correct pointers to the new head.

	 * Dry Run Example:
	 * Input: head = [1,2,3,4,5], k = 3
	 * 
	 * Original List: 
	 * 1 -> 2 -> 3 -> 4 -> 5
	 *
	 * Step 1: Reverse first k-group (1,2,3):
	 *     - `curr = 1`, `nex = 2`
	 *     - Swap 2 before 1: (2 -> 1 -> 3 -> 4 -> 5)
	 *     - Swap 3 before 2: (3 -> 2 -> 1 -> 4 -> 5)
	 *
	 * Step 2: Move `pre` to `1`, start reversing next k-group (4,5)
	 *     - Since remaining nodes < k, they stay unchanged.
	 *
	 * Final Output: 3 -> 2 -> 1 -> 4 -> 5
	 *
	 * Key Observations:
	 * - The dummy node helps track the head of the modified list.
	 * - The loop correctly handles both complete k-sized groups and leftover nodes.
	 * - The order of swaps ensures minimal pointer adjustments.
	 */


}
