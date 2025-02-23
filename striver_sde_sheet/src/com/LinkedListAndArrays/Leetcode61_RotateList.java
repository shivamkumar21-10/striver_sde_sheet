package com.LinkedListAndArrays;

public class Leetcode61_RotateList {
	
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
	    public ListNode rotateRight(ListNode head, int k) {

	        // Base Case: If the list is empty or no rotation is needed, return head as is.
	        if(head == null || k == 0) return head;

	        // Step 1: Calculate the length of the linked list
	        int len = 0;
	        ListNode pt = head;

	        while(pt.next != null){ // Traverse to find the length
	            len++;
	            pt = pt.next;
	        }
	        
	        // At this point, pt is pointing to the last node.

	        // Step 2: Convert the list into a circular linked list
	        pt.next = head; // Connect the last node to the first node

	        // Step 3: Handle cases where k is greater than or equal to len
	        // Since rotating by `len` is the same as no rotation, take k % (len + 1)
	        if(k >= len){
	            k = k % (len + 1); // Avoid unnecessary full rotations
	        }

	        // Step 4: Find the new tail at (len - k)th position
	        ListNode temp = head;
	        for(int i = 0; i < len - k; i++){
	            temp = temp.next;
	        }

	        // Step 5: Set new head and break the cycle
	        ListNode ans = temp.next;
	        temp.next = null; // Disconnect the new tail from the new head

	        return ans;
	    }
	}

	/**
	 * Intuition:
	 * - The problem requires rotating the linked list `k` times to the right.
	 * - Instead of shifting elements one by one, we can use a more optimal approach:
	 *   - Find the length of the list.
	 *   - Create a circular linked list.
	 *   - Move `len - k` steps to find the new tail and break the cycle.
	 *   - The new head will be the next node after the new tail.
	 *
	 * Key Points:
	 * 1. If k >= len, we optimize by taking k = k % len to avoid redundant rotations.
	 * 2. Instead of modifying the list k times, we make a single traversal to find the new tail.
	 * 3. Converting the list to a circular one allows us to easily "rotate" it without shifting nodes individually.
	 *
	 * Dry Run Example:
	 * =================
	 * Input: head = [1,2,3,4,5], k = 2
	 * 
	 * Step 1: Calculate Length (len = 5)
	 * Linked List: 1 -> 2 -> 3 -> 4 -> 5
	 * 
	 * Step 2: Make Circular
	 * Circular List: 1 -> 2 -> 3 -> 4 -> 5 -> (points back to 1)
	 * 
	 * Step 3: Compute Effective Rotations:
	 * k = 2 % 5 = 2 (No change)
	 * 
	 * Step 4: Find New Tail (5 - 2 = 3rd node)
	 * Traversing to the 3rd node: 1 -> 2 -> 3 (new tail)
	 * 
	 * Step 5: Set New Head (4) and Break the Circle
	 * New Head: 4 -> 5 -> 1 -> 2 -> 3
	 * Return: [4,5,1,2,3]
	 *
	 * Time Complexity: O(N) (Single pass to find length, another pass to find new tail)
	 * Space Complexity: O(1) (In-place rotation)
	 */


}
