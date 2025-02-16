package com.Array3;

public class Leetcode169_MajorityElement {
	
//	Intuition of Moore’s Voting Algorithm
//	The problem is to find the majority element, i.e., the element that appears more than n/2 times in an array of size n.
//	A majority element always exists if given by the problem constraint.
//	Key Observation: If an element appears more than n/2 times, it will always survive the cancellation process in Moore’s Voting Algorithm.

//	Moore’s Voting Algorithm - Stepwise Explanation
	
//	1. Candidate Selection (Voting Phase)
//
//	Maintain a count variable to track votes.
//	Iterate through the array:
//	If count == 0, select the current number as the new candidate.
//	If the current number is the same as the candidate, increase count.
//	Otherwise, decrease count (cancel out one occurrence).
	
//	2. Validation Phase
//
//	After the first pass, the candidate might be the majority element, but we need to verify it.
//	Count occurrences of the candidate to ensure it appears more than n/2 times.
	
	class Solution {
	    public int majorityElement(int[] nums) {
	        /*
	        ************************** INTUITION **************************
	        - We need to find an element that appears more than n/2 times.
	        - The idea is to use a "voting system" where elements cancel each other out.
	        - If an element is the majority (>n/2 occurrences), it will **always** survive till the end.

	        ************************** MOORE'S VOTING ALGORITHM **************************
	        Step 1: Select a candidate by maintaining a count
	        Step 2: Verify if the candidate actually occurs more than n/2 times
	        */

	        int candidate = -1;
	        int count = 0;

	        // Step 1: Voting Phase - Select a candidate
	        for (int num : nums) {
	            if (count == 0) {
	                candidate = num; // Select a new candidate
	            }

	            if (num == candidate) {
	                count++; // Increase count if same as candidate
	            } else {
	                count--; // Otherwise, decrease count
	            }
	        }

	        // Step 2: Verification Phase - Ensure candidate is truly the majority
	        count = 0;
	        for (int num : nums) {
	            if (num == candidate) {
	                count++;
	            }
	        }

	        if (count > nums.length / 2) {
	            return candidate; // Valid majority element
	        }

	        return -1; // Edge case: No majority element (if not guaranteed)
	        
	        /*
	        ************************** DRY RUN EXAMPLE **************************
	        Example: nums = [3, 3, 4, 2, 3, 3, 3]

	        Step | Element | Candidate | Count | Explanation
	        -------------------------------------------------
	        1    |  3      |  3        |  1    | Select 3 as candidate
	        2    |  3      |  3        |  2    | 3 matches candidate, count++
	        3    |  4      |  3        |  1    | 4 is different, count--
	        4    |  2      |  3        |  0    | 2 is different, count--
	        5    |  3      |  3        |  1    | Count = 0, select 3 as new candidate
	        6    |  3      |  3        |  2    | 3 matches candidate, count++
	        7    |  3      |  3        |  3    | 3 matches candidate, count++

	        Candidate after first pass = 3

	        Step 2: Verification
	        Count occurrences of candidate 3:
	        - 3 appears 5 times, which is > 7/2 (3.5), so 3 is the majority element.

	        Output: 3
	        */
	    }
	}


}
