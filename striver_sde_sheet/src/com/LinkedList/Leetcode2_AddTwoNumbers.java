package com.LinkedList;

public class Leetcode2_AddTwoNumbers {
	
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
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        // Intuition:
	        // We traverse both linked lists simultaneously, adding corresponding digits.
	        // Since numbers are stored in reverse order, the least significant digit comes first.
	        // We need to handle carry-over when summing two digits.
	        // If one list is shorter, we treat missing digits as zero.
	        // Finally, if there's a remaining carry, we append an extra node.

	        // Dummy node acts as the starting point of the result list.
	        ListNode dummy = new ListNode(0);
	        ListNode temp = dummy; // Pointer to construct the result list
	        int carry = 0; // Variable to store carry-over from sum of two digits

	        // Traverse both lists while at least one has remaining digits or there's a carry
	        while (l1 != null || l2 != null || carry != 0) {
	            int sum = 0; // Store sum of the current digits and carry
	            
	            // Add l1's value if available and move l1 forward
	            if (l1 != null) {
	                sum += l1.val;
	                l1 = l1.next;
	            }
	            
	            // Add l2's value if available and move l2 forward
	            if (l2 != null) {
	                sum += l2.val;
	                l2 = l2.next;
	            }
	            
	            // Add the carry from the previous sum
	            sum += carry;
	            
	            // Update carry for the next step (sum may be more than 9)
	            carry = sum / 10;
	            
	            // Create a new node with the single-digit value (sum % 10) and link it to the result
	            ListNode node = new ListNode(sum % 10);
	            temp.next = node; // Attach new node to result list
	            temp = temp.next; // Move pointer to the new node
	        }

	        // Return the result linked list starting from the next of dummy node
	        return dummy.next;
	    }
	}

	/*
	 * Dry Run:
	 * 
	 * Example 1:
	 * Input:
	 * l1 = 2 → 4 → 3 (represents 342)
	 * l2 = 5 → 6 → 4 (represents 465)
	 * 
	 * Initial state:
	 * dummy = 0 → null
	 * temp = dummy
	 * carry = 0
	 * 
	 * Iteration 1:
	 * l1.val = 2, l2.val = 5
	 * sum = 2 + 5 + 0 = 7
	 * carry = 7 / 10 = 0
	 * new node = 7
	 * dummy = 0 → 7 → null
	 * temp = 7
	 * Move l1 to next (4), l2 to next (6)
	 * 
	 * Iteration 2:
	 * l1.val = 4, l2.val = 6
	 * sum = 4 + 6 + 0 = 10
	 * carry = 10 / 10 = 1
	 * new node = 10 % 10 = 0
	 * dummy = 0 → 7 → 0 → null
	 * temp = 0
	 * Move l1 to next (3), l2 to next (4)
	 * 
	 * Iteration 3:
	 * l1.val = 3, l2.val = 4
	 * sum = 3 + 4 + 1 = 8
	 * carry = 8 / 10 = 0
	 * new node = 8
	 * dummy = 0 → 7 → 0 → 8 → null
	 * temp = 8
	 * Move l1 to null, l2 to null
	 * 
	 * Final check:
	 * carry = 0, l1 = null, l2 = null
	 * Result linked list: 7 → 0 → 8 (represents 807)
	 * 
	 * Output: 7 → 0 → 8
	 * 
	 * Example 2:
	 * Input:
	 * l1 = 9 → 9 → 9
	 * l2 = 1
	 * 
	 * Iteration 1:
	 * sum = 9 + 1 + 0 = 10
	 * carry = 1
	 * new node = 0
	 * 
	 * Iteration 2:
	 * sum = 9 + 0 + 1 = 10
	 * carry = 1
	 * new node = 0
	 * 
	 * Iteration 3:
	 * sum = 9 + 0 + 1 = 10
	 * carry = 1
	 * new node = 0
	 * 
	 * Iteration 4:
	 * carry = 1
	 * new node = 1
	 * 
	 * Output: 0 → 0 → 0 → 1 (represents 1000)
	 */


}
