package com.LinkedListII;

public class Leetcode142_LinkedListCycleII {

	//Brute - using hashing
	//store the node in hashtable 
	//the moment u found the node which is already preent in traversing through LL then u got the start point of the cycle
	//TC - O(n) SC-O(n)
	
	
	//optimal
	//same as detect the cycle
	//after the moment u got the f == s then u start moving slow by 1 and head by 1 the placee collide is your start index of cycle
	//TC - O(n) SC-O(1)
	
	//intution

	
	/**
	 * Definition for singly-linked list.
	 * class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public class Solution {
	    public ListNode detectCycle(ListNode head) {
	        /*
	         * Intuition:
	         * - We use Floyd’s Cycle Detection Algorithm (Tortoise and Hare Algorithm) to detect the cycle.
	         * - If there is a cycle, the fast and slow pointers will meet at some point inside the cycle.
	         * - Once they meet, we use a mathematical approach to determine the cycle’s starting point.
	         */

	        ListNode f = head; // Fast pointer (moves 2 steps)
	        ListNode s = head; // Slow pointer (moves 1 step)

	        // Step 1: Detect Cycle
	        while (f != null && f.next != null) { // Ensure fast pointer can move safely
	            f = f.next.next; // Move fast pointer by 2 steps
	            s = s.next;      // Move slow pointer by 1 step

	            if (f == s) break; // If they meet, cycle is detected
	        }

	        // Step 2: Check if no cycle exists
	        if (f == null || f.next == null) return null; // If fast pointer reaches null, no cycle

	        /*
	         * Why does moving one pointer back to head and moving both at the same speed give the cycle's starting node?
	         * 
	         * Let's say:
	         * - `L` is the distance from head to the start of the cycle.
	         * - `C` is the length of the cycle.
	         * - `X` is the distance from the cycle start to the meeting point of the two pointers.
	         * 
	         * The slow pointer travels: L + X
	         * The fast pointer travels: L + X + kC (where k is some integer, as it loops around)
	         * Since the fast pointer moves twice as fast as the slow one:
	         *  2(L + X) = L + X + kC
	         *  => L + X = kC
	         *  => L = kC - X
	         *  This means the distance from head to the start of the cycle (L) is the same as the distance 
	         *  from the meeting point (X) to the cycle start when moving at the same speed.
	         * 
	         * Therefore, when we move one pointer back to head and keep both pointers moving at the same speed, 
	         * they will meet exactly at the start of the cycle.
	         */

	        // Step 3: Move head pointer and slow pointer one step at a time
	        while (head != s) { 
	            head = head.next; // Move head forward
	            s = s.next; // Move slow pointer forward
	        }

	        return head; // This is the start of the cycle
	    }
	}

	/*
	 * Dry Run Example:
	 *
	 * 1 → 2 → 3 → 4 → 5 
	 *           ↑       ↓
	 *           8 ← 7 ← 6
	 *
	 * Step 1: Detect Cycle
	 * - `s = 2`, `f = 3`
	 * - `s = 3`, `f = 5`
	 * - `s = 4`, `f = 7`
	 * - `s = 5`, `f = 3` (cycle detected)
	 * - `s = 6`, `f = 5`
	 * - `s = 7`, `f = 7` (meeting point)
	 *
	 * Step 2: Move one pointer to head
	 * - Move `head` and `s` one step at a time:
	 *   - `head = 2`, `s = 8`
	 *   - `head = 3`, `s = 3` (they meet, cycle starts at node `3`)
	 *
	 * Complexity Analysis:
	 * - Time Complexity: O(N), each node is visited at most twice.
	 * - Space Complexity: O(1), only two extra pointers are used.
	 */
 
}

