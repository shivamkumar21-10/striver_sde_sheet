package com.LinkedListII;


public class Leetcode160_IntersectionofTwoLinkedLists {
	
	//Brute force
	//outer loop to loop through one linked list 
	//inner loop check that in another linked list if node is same as of outer linked list
	
	//TC - O(mxn)
	
	//Better - Hashing
	//Traverse LL1 and store the reference 
	//and wile traversing LL2 check in hashtable if it is present if yes that is your intersection point
	//TC - O(m+n) assuming hashtable take O(1) for lookup
	//SC - O(m)
	
	//optimal - 1
	//Steps
	//step1 - take two variable - d1 point to head of LL1 and d2 pointing to head of LL2
	//step2 -  to move bolth simulatneously and note the length
	//step3 - move d1/d2 which is bigger LL to the difference of both length
	//step4 - start moving both dummy node simultaneosuly and where both colllides that will be your intersection
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public class Solution {
	    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        /*
	         * Intuition:
	         * We need to find the node where two singly linked lists intersect.
	         * Since both lists can have different lengths, we first calculate their lengths
	         * and then align them at the same starting position before traversing together.
	         * 
	         * Approach:
	         * 1. Calculate the length of both linked lists.
	         * 2. Align the longer list’s head with the shorter list by skipping extra nodes.
	         * 3. Traverse both lists simultaneously until they meet at the intersection node.
	         * 
	         * This ensures that both pointers reach the intersection point at the same time.
	         * If they do not intersect, both pointers will reach null.
	         */

	        ListNode d1 = headA;
	        ListNode d2 = headB;
	        int n = 0;
	        int m = 0;

	        // Step 1: Calculate the length of list A
	        while (d1 != null) {
	            n++;
	            d1 = d1.next;
	        }

	        // Step 2: Calculate the length of list B
	        while (d2 != null) {
	            m++;
	            d2 = d2.next;
	        }

	        // Step 3: Reset pointers to the head of both lists
	        d1 = headA;
	        d2 = headB;

	        // Step 4: Align the longer list with the shorter list
	        if (n > m) {
	            for (int i = 0; i < n - m; i++) {
	                d1 = d1.next;  // Move d1 forward
	            }
	        } else {
	            for (int i = 0; i < m - n; i++) {
	                d2 = d2.next;  // Move d2 forward
	            }
	        }

	        // Step 5: Traverse both lists together until intersection is found
	        while (d1 != d2) {
	            d1 = d1.next;
	            d2 = d2.next;
	        }

	        // Step 6: Return the intersection node (or null if no intersection exists)
	        return d1;
	    }
	}

	/*
	 * Dry Run:
	 * 
	 * Example:
	 * Input:
	 * List A:  4 -> 1 -> 8 -> 4 -> 5
	 * List B:      5 -> 6 -> 1 -> 8 -> 4 -> 5
	 *                        ^
	 *                       (Intersection)
	 * 
	 * Step 1: Calculate lengths
	 * Length of A (n) = 5
	 * Length of B (m) = 6
	 * 
	 * Step 2: Align both lists
	 * Since B is longer, move its head forward by 1 step:
	 * New alignment:
	 * A:       1 -> 8 -> 4 -> 5
	 * B:   6 -> 1 -> 8 -> 4 -> 5
	 * 
	 * Step 3: Traverse together until intersection is found
	 * Both pointers meet at node with value **8**.
	 * 
	 * Output: Intersection node with value 8.
	 *
	 * Edge Cases Considered:
	 * - No intersection (both pointers will reach null).
	 * - Lists of equal length (no need to realign).
	 * - Intersection at head (pointers meet at the first node).
	 * 
	 * Time Complexity: O(N + M) [Traversing both lists once]
	 * Space Complexity: O(1) [Using only two pointers]
	 */

	
	//optimal -2 
	//Steps
	//step1 - take two variable - d1 point to head of LL1 and d2 pointing to head of LL2
	//step2 - move both simulataneously and when one reaches null assign it to the head on other linked list (d1 reaches null assing it to head of LL2 and viceversa)
	//at that moment u assign d1 to head of LL2 d2 will be exactly at difference of length nodes
	//now u will starat from same point and collide at the intersection
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public class Solution2 {
	    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

	        /*
	         * Intuition:
	         * The problem is to find the intersection node of two singly linked lists.
	         * Instead of using extra space or calculating lengths separately, we use two pointers.
	         * 
	         * - We initialize two pointers, d1 and d2, at the heads of List A and List B.
	         * - Both pointers traverse their respective lists one node at a time.
	         * - If a pointer reaches the end of its list, it is redirected to the head of the other list.
	         * - If there is an intersection, both pointers will eventually meet at that node.
	         * - If there is no intersection, both pointers will reach null at the same time.
	         * 
	         * Why does this work?
	         * - By switching lists, both pointers traverse the same total number of nodes:
	         *   (Length of List A + Length of List B).
	         * - This means they are guaranteed to meet at the intersection or reach null together.
	         */

	        if (headA == null || headB == null) return null; // If either list is empty, return null

	        ListNode d1 = headA;
	        ListNode d2 = headB;

	        // Traverse both lists until pointers meet at intersection or reach the end (null)
	        while (d1 != d2) {
	            /*
	             * If d1 reaches the end of List A, redirect it to headB
	             * Else, move it to the next node
	             */
	            d1 = (d1 == null) ? headB : d1.next;

	            /*
	             * If d2 reaches the end of List B, redirect it to headA
	             * Else, move it to the next node
	             */
	            d2 = (d2 == null) ? headA : d2.next;
	        }

	        return d1; // If d1 and d2 meet, return the intersection node; otherwise, return null
	    }
	}

	/*
	
/*
 * Dry Run Example:
 * 
 * Given two linked lists:
 * 
 * List A:  1 → 3 → 5 → 7 → 9 → 11 → 15 → 20
 * List B:        2 → 4 → 6 → 9 → 11 → 15 → 20
 *                                ↑
 *                            (Intersection at node 9)
 * 
 * Step-by-step execution:
 * 
 * Initial:
 * d1 = 1 (List A), d2 = 2 (List B)
 * 
 * Iteration 1:
 * d1 moves to 3, d2 moves to 4
 * 
 * Iteration 2:
 * d1 moves to 5, d2 moves to 6
 * 
 * Iteration 3:
 * d1 moves to 7, d2 moves to 9 (intersection found)
 * 
 * Iteration 4:
 * d1 moves to 9, d2 moves to 11
 * 
 * Iteration 5:
 * d1 moves to 11, d2 moves to 15
 * 
 * Iteration 6:
 * d1 moves to 15, d2 moves to 20
 * 
 * Iteration 7:
 * d1 moves to 20, d2 moves to null (end of List B)
 * 
 * Iteration 8:
 * d1 moves to null (end of List A), d2 resets to headA (1)
 * 
 * Iteration 9:
 * d1 resets to headB (2), d2 moves to 3
 * 
 * Iteration 10:
 * d1 moves to 4, d2 moves to 5
 * 
 * Iteration 11:
 * d1 moves to 6, d2 moves to 7
 * 
 * Iteration 12:
 * d1 moves to 9, d2 moves to 9 (Intersection found!)
 * 
 * Both d1 and d2 now point to the intersection node (9), so we return node 9.
 * 
 * Time Complexity: O(N + M) (each node is visited at most twice)
 * Space Complexity: O(1) (no extra space used)
	 * 
	 * Key Observations:
	 * - When there is an intersection, switching lists ensures both pointers meet at the same node.
	 * - If no intersection exists, both pointers reach null at the same time, ensuring termination.
	 * 
	 * Complexity Analysis:
	 * - Time Complexity: O(N + M), where N and M are the lengths of the two lists.
	 * - Space Complexity: O(1), since only two pointers are used.
	 */

	
}
