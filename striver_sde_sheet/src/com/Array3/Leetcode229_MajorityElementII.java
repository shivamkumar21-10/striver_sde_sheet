package com.Array3;
import java.util.*;
public class Leetcode229_MajorityElementII {
	


	class Solution {
	    /**
	     * Boyer-Moore Voting Algorithm to find elements appearing more than ⌊n/3⌋ times.
	     * 
	     * Intuition:
	     * 1. If an element appears more than ⌊n/3⌋ times, there can be at most 2 such elements.
	     * 2. Use two candidate variables and their counts to track the potential majority elements.
	     * 3. If a new element is found and both candidate slots are full, decrement both counts.
	     * 4. Finally, verify the candidates by counting their occurrences.
	     */
	    public List<Integer> majorityElement(int[] nums) {
	        // Step 1: Identify two potential majority candidates
	        int ele1 = Integer.MIN_VALUE, cnt1 = 0;
	        int ele2 = Integer.MIN_VALUE, cnt2 = 0;

	        for (int i = 0; i < nums.length; i++) {
	            if (cnt1 == 0 && nums[i] != ele2) { // Assign first candidate
	                ele1 = nums[i];
	                cnt1 = 1;
	            } else if (cnt2 == 0 && nums[i] != ele1) { // Assign second candidate
	                ele2 = nums[i];
	                cnt2 = 1;
	            } else if (nums[i] == ele1) { // Increment count of first candidate
	                cnt1++;
	            } else if (nums[i] == ele2) { // Increment count of second candidate
	                cnt2++;
	            } else { // Reduce count when encountering a different element
	                cnt1--;
	                cnt2--;
	            }
	        }

	        // Step 2: Verify the candidates
	        int cnt3 = 0, cnt4 = 0;
	        for (int i = 0; i < nums.length; i++) {
	            if (nums[i] == ele1) cnt3++;
	            if (nums[i] == ele2) cnt4++;
	        }

	        List<Integer> ans = new ArrayList<>();
	        if (cnt3 > nums.length / 3) ans.add(ele1);
	        if (cnt4 > nums.length / 3) ans.add(ele2);

	        return ans;
	    }
	}

	/**
	 * Dry Run Example:
	 * 
	 * Input: nums = [1,1,1,3,3,2,2,2]
	 * 
	 * Step 1: Identify Majority Candidates
	 * -------------------------------------------------
	 * Iteration | nums[i] | ele1 | cnt1 | ele2 | cnt2 | Action
	 * -------------------------------------------------
	 *    0     |   1    |  1   |  1   |  -   |  0   | Set ele1 = 1
	 *    1     |   1    |  1   |  2   |  -   |  0   | Increase cnt1
	 *    2     |   1    |  1   |  3   |  -   |  0   | Increase cnt1
	 *    3     |   3    |  1   |  3   |  3   |  1   | Set ele2 = 3
	 *    4     |   3    |  1   |  3   |  3   |  2   | Increase cnt2
	 *    5     |   2    |  1   |  2   |  3   |  1   | Reduce cnt1, cnt2
	 *    6     |   2    |  1   |  1   |  3   |  0   | Reduce cnt1
	 *    7     |   2    |  2   |  1   |  3   |  0   | Set ele1 = 2
	 * 
	 * Step 2: Count occurrences to verify candidates:
	 * ele1 = 2 appears 3 times
	 * ele2 = 3 appears 2 times
	 * 
	 * Since only 2 appears more than ⌊8/3⌋ = 2 times, answer = [2]
	 * 
	 * Output: [2]
	 */


}
