package com.Array4;
import java.util.*;

public class Leetcode18_4Sum {
	
	class Solution {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        // Step 1: Sort the array to ensure that we can skip duplicate values and use the two-pointer technique effectively.
	        Arrays.sort(nums);  // Sorted nums: [-2, -1, 0, 0, 1, 2]
	        int n = nums.length;  // Length of the array

	        List<List<Integer>> ans = new ArrayList<>();  // This will store the final list of quadruplets

	        // Step 2: Iterate over the array with index 'i' as the first number of the quadruplet
	        for (int i = 0; i < n; i++) {
	            // Step 2.1: Skip duplicate values for 'i' to avoid repeating the same quadruplet
	            if (i > 0 && nums[i - 1] == nums[i]) continue;

	            // Step 3: Iterate over the array with index 'j' as the second number of the quadruplet
	            for (int j = i + 1; j < n; j++) {
	                // Step 3.1: Skip duplicate values for 'j' to avoid repeating the same quadruplet
	                if (j > i + 1 && nums[j - 1] == nums[j]) continue;

	                // Step 4: Initialize two pointers, k and l, for the third and fourth elements of the quadruplet
	                int k = j + 1;  // Pointer for the third element (starts after 'j')
	                int l = n - 1;  // Pointer for the fourth element (starts at the end of the array)

	                // Step 5: Use the two-pointer technique to find the remaining two elements of the quadruplet
	                while (k < l) {
	                    // Calculate the sum of the current quadruplet
	                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];

	                    // Step 5.1: If the sum equals the target, add the quadruplet to the result list
	                    if (sum == target) {
	                        List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
	                        ans.add(temp);  // Add the quadruplet to the answer list

	                        // Move both pointers to avoid duplicates
	                        k++;  // Move the 'k' pointer to the right
	                        l--;  // Move the 'l' pointer to the left

	                        // Step 5.2: Skip duplicate values for 'k' and 'l'
	                        while (k < l && nums[k] == nums[k - 1]) k++;  // Skip duplicates for 'k'
	                        while (l > k && nums[l] == nums[l + 1]) l--;  // Skip duplicates for 'l'
	                    }
	                    // Step 5.3: If the sum is greater than the target, move the 'l' pointer left
	                    else if (sum > target) {
	                        l--;
	                    }
	                    // Step 5.4: If the sum is less than the target, move the 'k' pointer right
	                    else {
	                        k++;
	                    }
	                }
	            }
	        }

	        // Step 6: Return the list of quadruplets that sum to the target
	        return ans;
	    }
	}
	
	
//	Dry Run:
//		Input: nums = [1, 0, -1, 0, -2, 2], target = 0
//
//		Sorted nums: [-2, -1, 0, 0, 1, 2]
//
//		Iteration 1 (i = 0, nums[i] = -2):
//		Start the second loop (j = 1, nums[j] = -1).
//		Initialize pointers k = 2 and l = 5.
//		First inner loop (k = 2, l = 5):
//
//		Sum = -2 + -1 + 0 + 2 = -1 (less than target). Move k++ to k = 3.
//		Second inner loop (k = 3, l = 5):
//
//		Sum = -2 + -1 + 0 + 2 = -1 (less than target). Move k++ to k = 4.
//		Third inner loop (k = 4, l = 5):
//
//		Sum = -2 + -1 + 1 + 2 = 0 (equal to target). Add [-2, -1, 1, 2] to the result list.
//		Move k++ to k = 5 and l-- to l = 4. Now k > l, exit the loop.
	
	
//		Iteration 2 (i = 0, nums[i] = -2):
//		Move to the second loop (j = 2, nums[j] = 0).
//		Initialize pointers k = 3 and l = 5.
//		First inner loop (k = 3, l = 5):
//
//		Sum = -2 + 0 + 0 + 2 = 0 (equal to target). Add [-2, 0, 0, 2] to the result list.
//		Move k++ to k = 4 and l-- to l = 4. Now k > l, exit the loop.
	
	
//		Iteration 3 (i = 1, nums[i] = -1):
//		Move to the second loop (j = 2, nums[j] = 0).
//		Initialize pointers k = 3 and l = 5.
//		First inner loop (k = 3, l = 5):
//
//		Sum = -1 + 0 + 0 + 2 = 1 (greater than target). Move l-- to l = 4.
//		Second inner loop (k = 3, l = 4):
//
//		Sum = -1 + 0 + 0 + 1 = 0 (equal to target). Add [-1, 0, 0, 1] to the result list.
//		Move k++ to k = 4 and l-- to l = 3. Now k > l, exit the loop.
//		
//		Final Result:
//		The quadruplets that sum to the target are: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]].
//
//		Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]


}
