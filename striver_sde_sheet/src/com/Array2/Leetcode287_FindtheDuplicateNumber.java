package com.Array2;

public class Leetcode287_FindtheDuplicateNumber {
	
	//************Approach1 - Binary Search****************
	
//	We are using Binary Search on the Value Range instead of the index.
//	Instead of searching for the duplicate directly, we check how many numbers are ≤ mid at every step.
//	If the count of numbers ≤ mid is more than mid, it means the duplicate must be in the lower half.
//	Otherwise, it is in the upper half.
	
//	** Problem Understanding
//	We have n+1 numbers in the range [1, n], which means at least one number must be repeated.
//	Since the numbers are in a specific range, we can use binary search on values instead of indexes to efficiently find the duplicate.

//	** Key Observations That Led to This Approach
//	1. Counting Elements ≤ mid Gives a Clue
//
//	Instead of directly searching for the duplicate, we check how many numbers in nums[] are ≤ mid.
//	If a number is repeated, the count of numbers ≤ mid will exceed mid.
//	
//	2. Binary Search Helps Reduce Search Space
//
//	Instead of a brute-force O(N²) approach, we narrow down the possible duplicate range using binary search.
//	We start with low = 1 and high = n (since the duplicate must be within this range).
//	By checking count ≤ mid, we can determine whether to search in the lower or upper half.
	
	class Solution {
	    public int findDuplicate(int[] nums) {
	        int n = nums.length; // Number of elements

	        int low = 1;  // Smallest possible value in nums[]
	        int high = n - 1; // Largest possible value in nums[]
	        int cnt;  // Variable to count numbers <= mid

	        // Binary Search on the value range
	        while (low <= high) {
	            int mid = (low + high) / 2;  // Find middle of the current range
	            cnt = 0;

	            // Count how many numbers in the array are ≤ mid
	            for (int num : nums) {
	                if (num <= mid) {
	                    cnt++;
	                }
	            }

	            /**
	             * If count(≤ mid) is more than mid, it means the duplicate is in the lower half.
	             * - A duplicate exists in [1, mid], so reduce high.
	             */
	            if (cnt > mid) {
	                high = mid - 1;
	            }
	            /**
	             * If count(≤ mid) is not more than mid, the duplicate is in the upper half.
	             * - Move low up to search in [mid+1, n]
	             */
	            else {
	                low = mid + 1;
	            }
	        }

	        // When the loop exits, 'low' is the duplicate number
	        return low;
	    }
	}
	
	//Dry Run
	// Given Input: nums = [1, 3, 4, 2, 2]
//	low = 1, high = 4
//
//	1st Iteration:
//	mid = (1+4)/2 = 2
//	Count of numbers ≤ 2:
//	  nums[0] = 1 ✅
//	  nums[1] = 3 ❌
//	  nums[2] = 4 ❌
//	  nums[3] = 2 ✅
//	  nums[4] = 2 ✅
//	Total count = 3
//
//	Since count(≤ 2) > 2 → The duplicate must be ≤ 2
//	high = mid - 1 = 1
//
//	2nd Iteration:
//	mid = (1+1)/2 = 1
//	Count of numbers ≤ 1:
//	  nums[0] = 1 ✅
//	Total count = 1
//
//	Since count(≤ 1) ≤ 1 → The duplicate must be > 1
//	low = mid + 1 = 2
//
//	Loop exits, return `low = 2`

	//**************Approach2 - Slow and Fast Pointer*********************
	
//	Intuition Behind the Approach
//	1. The problem states that there is one duplicate number in an array where numbers range from 1 to n.
//	2. Since the numbers themselves act as pointers to the next index, we can treat the array as a linked list where:
//		Each index represents a node.
//		Each value at that index points to another node.
//	3. Floyd’s Cycle Detection Algorithm (Tortoise and Hare) is used to detect cycles in linked lists.
//	4. If a cycle exists in the list, it means there is a duplicate value.
	
//	Observations That Led to This Approach
//	1. Cycle Formation:
//
//	Since the values are limited between 1 to n, and there are n+1 elements, at least one number must repeat.
//	This repeated number creates a cycle in the array (like a loop in a linked list).
//	2. Detecting the Cycle Using Two Pointers (Tortoise & Hare):
//
//		2.1Use two pointers:
//			Slow pointer (slow) moves one step at a time.
//			Fast pointer (fast) moves two steps at a time.
//		If there is a cycle, these pointers will eventually meet.
//	3.Finding the Duplicate Number:
//
//		Reset one pointer to the start (nums[0]).
//		Move both pointers one step at a time.
//		The point where they meet is the duplicate number.
	
	class Solution2 {
	    public int findDuplicate(int[] nums) {

	        /**
	         * Step 1: Initialize two pointers: 
	         * - `slow` starts from nums[0] and moves 1 step at a time.
	         * - `fast` starts from nums[0] and moves 2 steps at a time.
	         * - This follows Floyd’s cycle detection approach to find a meeting point in the cycle.
	         */
	        int slow = nums[0];
	        int fast = nums[0];

	        do {
	            slow = nums[slow];       // Move `slow` one step
	            fast = nums[nums[fast]]; // Move `fast` two steps
	        } while (slow != fast);      // Continue until they meet, confirming a cycle exists.

	        /**
	         * Step 2: Find the entry point of the cycle (which is the duplicate number).
	         * - Reset `fast` to the start of the array.
	         * - Move both `slow` and `fast` one step at a time.
	         * - When they meet again, it's at the duplicate number.
	         */
	        fast = nums[0];
	        while (slow != fast) {
	            slow = nums[slow]; // Move `slow` one step
	            fast = nums[fast]; // Move `fast` one step
	        }

	        /**
	         * Step 3: Return the duplicate number where `slow` and `fast` meet.
	         */
	        return slow;
	    }
	}
	
	//Dry run
	// Given Input: nums = [1, 3, 4, 2, 2]
	// The array can be visualized as:
	// Index:   0  1  2  3  4
	// Values:  1  3  4  2  2
	//
	// The numbers act as "next pointers":
	// nums[0] -> nums[1] (1 -> 3)
	// nums[1] -> nums[3] (3 -> 2)
	// nums[3] -> nums[2] (2 -> 4)
	// nums[2] -> nums[4] (4 -> 2)
	// nums[4] -> nums[2] (2 -> 4) --> CYCLE

	// Step 1: Detecting the Cycle using Slow and Fast
//	slow = nums[0] = 1
//	fast = nums[0] = 1

	// Iteration 1:
//	slow = nums[1] = 3
//	fast = nums[nums[1]] = nums[3] = 2

	// Iteration 2:
//	slow = nums[3] = 2
//	fast = nums[nums[3]] = nums[4] = 2

	// Since slow == fast (both are 2), cycle is detected.

	// Step 2: Find Duplicate Number
//	fast is reset to nums[0] = 1

	// Iteration 1:
//	slow = nums[2] = 4
//	fast = nums[1] = 3

	// Iteration 2:
//	slow = nums[4] = 2
//	fast = nums[3] = 2

	// Both slow and fast meet at index 2, which means the duplicate number is:
//	return 2;


	
	
	
}
