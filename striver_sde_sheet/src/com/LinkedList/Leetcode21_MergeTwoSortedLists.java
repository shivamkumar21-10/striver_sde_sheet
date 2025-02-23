package com.LinkedList;

public class Leetcode21_MergeTwoSortedLists {
	
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
	
	//This solution we are using one extra linked list

	class Solution {
	    /**
	     * Intuition:
	     * - We are given two sorted linked lists and need to merge them into a single sorted list.
	     * - Since both lists are already sorted, we can efficiently merge them using a **two-pointer approach**.
	     * - We traverse both lists and always pick the node with the smaller value to form the merged list.
	     * - If one list is completely processed before the other, we simply attach the remaining nodes.
	     * 
	     * Approach:
	     * - Create a **dummy node** to simplify edge cases and tracking the merged list.
	     * - Use a **temp pointer** to iterate and build the merged list.
	     * - Compare `list1.val` and `list2.val`, always attaching the smaller node to `temp`.
	     * - Move `list1` or `list2` forward accordingly.
	     * - If one list is exhausted, attach the remaining nodes of the other list.
	     *
	     * Time Complexity: O(n + m)  - We traverse both lists once.
	     * Space Complexity: O(1)  - We use only extra pointers.
	     */
	    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
	        ListNode dummy = new ListNode(0); // Dummy node to simplify merging
	        ListNode temp = dummy; // Pointer to track the last node in the merged list
	        
	        // Merge both lists by always picking the smaller value
	        while (list1 != null && list2 != null) {
	            if (list1.val <= list2.val) {
	                temp.next = list1; // Attach list1's current node
	                list1 = list1.next; // Move list1 forward
	            } else {
	                temp.next = list2; // Attach list2's current node
	                list2 = list2.next; // Move list2 forward
	            }
	            temp = temp.next; // Move temp forward to keep track
	        }
	        
	        // Attach the remaining nodes of the non-empty list
	        if (list1 != null) {
	            temp.next = list1;
	        } else {
	            temp.next = list2;
	        }
	        
	        return dummy.next; // The merged list starts from dummy.next
	    }
	}

	/*
	 * Dry Run Example:
	 * Input:
	 * list1 = 1 → 3 → 5
	 * list2 = 2 → 4 → 6
	 * 
	 * Iteration 1:
	 * - list1.val = 1, list2.val = 2
	 * - 1 ≤ 2 → Attach list1 (1) to merged list
	 * - Move list1 forward → list1 now at 3
	 * - Merged List: 1
	 * 
	 * Iteration 2:
	 * - list1.val = 3, list2.val = 2
	 * - 2 < 3 → Attach list2 (2) to merged list
	 * - Move list2 forward → list2 now at 4
	 * - Merged List: 1 → 2
	 * 
	 * Iteration 3:
	 * - list1.val = 3, list2.val = 4
	 * - 3 ≤ 4 → Attach list1 (3) to merged list
	 * - Move list1 forward → list1 now at 5
	 * - Merged List: 1 → 2 → 3
	 * 
	 * Iteration 4:
	 * - list1.val = 5, list2.val = 4
	 * - 4 < 5 → Attach list2 (4) to merged list
	 * - Move list2 forward → list2 now at 6
	 * - Merged List: 1 → 2 → 3 → 4
	 * 
	 * Iteration 5:
	 * - list1.val = 5, list2.val = 6
	 * - 5 ≤ 6 → Attach list1 (5) to merged list
	 * - Move list1 forward → list1 now at null
	 * - Merged List: 1 → 2 → 3 → 4 → 5
	 * 
	 * Remaining Nodes:
	 * - list1 is null, list2 has remaining node 6
	 * - Attach list2 to merged list
	 * - Final Merged List: 1 → 2 → 3 → 4 → 5 → 6
	 * 
	 * Output: 1 → 2 → 3 → 4 → 5 → 6
	 */


	//Inplace without using extra space
	
}
