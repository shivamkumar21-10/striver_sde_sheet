package com.Array2;
import java.util.*;

public class Leetcode56_MergeIntervals {
	
	
	//Brute force
	class Solution {

	    public int[][] merge(int[][] interval) {
	        // Step 1: Sort the intervals based on the start time
	        // This ensures that we process intervals in increasing order of their start value.
	        Arrays.sort(interval, (a, b) -> Integer.compare(a[0], b[0]));

	        // Step 2: Create a list to store the merged intervals dynamically.
	        List<int[]> ans = new ArrayList<>();

	        // Step 3: Iterate over all intervals
	        for (int i = 0; i < interval.length; i++) {
	            int start = interval[i][0]; // Extract the start time of the current interval
	            int end = interval[i][1];   // Extract the end time of the current interval

	            // **Optimization Check:** If the last interval in `ans` already covers this one, skip it.
	            // This is an efficiency check to avoid redundant processing.
	            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1)[1]) {
	                continue;  // No need to process as it is already merged.
	            }

	            // **Step 4: Merge Overlapping Intervals**
	            // Iterate through the remaining intervals and merge if there is an overlap.
	            for (int j = i + 1; j < interval.length; j++) {
	                if (interval[j][0] <= end) { 
	                    // If the next interval starts before the current one ends, merge it.
	                    end = Math.max(end, interval[j][1]); // Extend the end boundary to cover the overlap.
	                } else {
	                    // If there is no overlap, stop merging further.
	                    break;
	                }
	            }

	            // Step 5: Add the merged interval to the answer list.
	            ans.add(new int[]{start, end});
	        }

	        // Step 6: Convert the dynamic list to a 2D array and return the result.
	        return ans.toArray(new int[ans.size()][]);
	    }
	}
	
	//*****************************optimal***********************************************

	class Solution2 {
	    public int[][] merge(int[][] intervals) {
	        // Step 1: Edge case - If the input array is empty, return an empty array.
	        if (intervals == null || intervals.length == 0) {
	            return new int[0][];
	        }

	        // Step 2: Sort the intervals based on the start value (interval[i][0]).
	        // Sorting helps in merging overlapping intervals in a single pass.
	        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

	        // Step 3: Create a list to store the merged intervals.
	        List<int[]> ans = new ArrayList<>();

	        // Step 4: Traverse through the sorted intervals.
	        for (int i = 0; i < intervals.length; i++) {

	            // Case 1: If 'ans' is empty OR current interval does not overlap with the last added interval.
	            if (ans.isEmpty() || intervals[i][0] > ans.get(ans.size() - 1)[1]) {
	                // No overlap, so add the current interval as a new entry.
	                ans.add(new int[]{intervals[i][0], intervals[i][1]});
	            } 
	            // Case 2: If there is an overlap with the last interval in 'ans'.
	            else {
	                // Merge the intervals by updating the last interval’s end time to the maximum of both.
	                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
	            }
	        }

	        // Convert the list to an array and return the result.
	        return ans.toArray(new int[ans.size()][]);
	    }
	}
	
	// Example Input:
//	intervals = [[1,3], [2,6], [8,10], [15,18]]

	// Step 1: Sorting the intervals (Already sorted in this case):
//	Sorted intervals: [[1,3], [2,6], [8,10], [15,18]]

	// Step 2: Iterating through each interval

	// i = 0 -> First interval [1,3]
	// ans is empty, so add [1,3] to ans
//	ans = [[1,3]]

	// i = 1 -> Second interval [2,6]
	// Condition: 2 <= 3 (Overlapping with last interval [1,3])
	// Merge by updating last interval’s end to max(3,6) = 6
//	ans = [[1,6]]

	// i = 2 -> Third interval [8,10]
	// Condition: 8 > 6 (No overlap with last interval [1,6])
	// Add [8,10] as a new separate interval
//	ans = [[1,6], [8,10]]

	// i = 3 -> Fourth interval [15,18]
	// Condition: 15 > 10 (No overlap with last interval [8,10])
	// Add [15,18] as a new separate interval
//	ans = [[1,6], [8,10], [15,18]]

	// Step 3: Convert list to array and return result
//	Output: [[1,6], [8,10], [15,18]]
	
	//step by step logic
//	1. Why Sorting?

//			Sorting ensures that all intervals are processed in an increasing order of start time.
//			This helps in merging overlapping intervals in a single pass.
	
//	2. Why Checking for Overlap?
//
//			If the start of the current interval is smaller than or equal to the end of the last interval in ans, they overlap and should be merged.
//			Else, it's a separate interval, so we just add it.
	
//	3.Why Use a List Instead of an Array?
//
//			We don't know the final number of merged intervals in advance.
//			Using a List<int[]> allows dynamic storage, and we convert it to an array before returning.
	
//	Time & Space Complexity
//	Sorting takes O(NlogN)
//	Merging takes O(N)
//	Overall Complexity: O(NlogN) (due to sorting).
//	Space Complexity: O(N) (for storing merged intervals).


	


}
