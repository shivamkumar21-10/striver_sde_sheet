package com.greedy;

import java.util.Arrays;

public class MinimumPlatforms_GFG {
	
	class Solution {
	    // Function to find the minimum number of platforms required at the
	    // railway station such that no train waits.
	    static int findPlatform(int arr[], int dep[]) {

	        // Variable to keep track of current count of platforms needed at any time
	        int cnt = 0;

	        // Variable to store the maximum platforms needed at any point in time (final answer)
	        int maxTim = Integer.MIN_VALUE;

	        // -----------------------------
	        // 👀 Observation & Intuition:
	        // Every train occupies a platform from arrival to departure.
	        // If multiple trains overlap in their time windows, we need multiple platforms.
	        // So the problem reduces to finding the maximum number of overlapping intervals.
	        //
	        // To solve this efficiently, we sort the arrival and departure times.
	        // Then we traverse both arrays with two pointers, simulating a timeline of train events.
	        // -----------------------------

	        // Step 1: Sort arrival and departure times so that we can process the earliest events first
	        Arrays.sort(arr); // O(N log N)
	        Arrays.sort(dep); // O(N log N)

	        // i -> pointer for arrivals, j -> pointer for departures
	        int i = 0;
	        int j = 0;

	        // Step 2: Traverse both arrays to simulate the timeline of train events
	        while (i < arr.length && j < dep.length) {

	            // If next train arrives before or exactly when another departs → need one more platform
	            if (arr[i] <= dep[j]) {
	                cnt++; // one more platform needed
	                i++;   // move to next arrival
	            } else {
	                // If the next train departs before another arrives → free a platform
	                cnt--; // one platform freed
	                j++;   // move to next departure
	            }

	            // Update max platform count seen so far
	            maxTim = Math.max(maxTim, cnt);
	        }

	        // Step 3: Return the maximum number of platforms needed at any point
	        return maxTim;
	    }
	}

	/*
	🧪 Dry Run Example:
	Input:
	arr[] = [900, 940, 950, 1100, 1500, 1800]
	dep[] = [910, 1200, 1120, 1130, 1900, 2000]

	After sorting:
	arr[] = [900, 940, 950, 1100, 1500, 1800]
	dep[] = [910, 1120, 1130, 1200, 1900, 2000]

	Simulation:
	-> 900 arrives, cnt = 1, maxTim = 1
	-> 940 arrives, cnt = 2, maxTim = 2
	-> 950 arrives, cnt = 3, maxTim = 3
	-> 910 departs, cnt = 2
	-> 1100 arrives, cnt = 3
	-> 1120 departs, cnt = 2
	-> 1130 departs, cnt = 1
	-> 1200 departs, cnt = 0
	-> 1500 arrives, cnt = 1
	-> 1800 arrives, cnt = 2
	-> 1900 departs, cnt = 1
	-> 2000 departs, cnt = 0

	✅ Final maxTim = 3 → So we need 3 platforms.

	-------------------------------------
	⏱️ Time Complexity:
	- Sorting: O(N log N)
	- Traversing with two pointers: O(N)
	=> Total: O(N log N)

	🧠 Space Complexity:
	- O(1) auxiliary space (in-place sorting, no extra space used apart from counters)
	-------------------------------------
	*/


}
