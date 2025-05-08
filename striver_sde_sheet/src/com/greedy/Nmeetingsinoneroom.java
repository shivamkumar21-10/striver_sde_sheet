package com.greedy;
import java.util.*;
public class Nmeetingsinoneroom {
	


	class Meeting {
	    int start, end;
	    
	    public Meeting(int start, int end) {
	        this.start = start;
	        this.end = end;
	    }
	}

	class NMeetingsInOneRoom {
	    /**
	     * Function to find the maximum number of meetings that can be scheduled.
	     * 
	     * Intuition:
	     * - We want to schedule the maximum number of non-overlapping meetings.
	     * - The **greedy approach** helps us achieve this by **always selecting** the meeting that ends the earliest.
	     * - Sorting the meetings by **end time** ensures that we maximize room availability.
	     * - If we always pick the meeting that ends the earliest and move forward, 
	     *   we create more room for upcoming meetings.
	     * 
	     * Logic:
	     * 1. **Sort** the meetings by their **end time** (ascending order).
	     * 2. **Iterate through the sorted meetings**, keeping track of the **end time of the last scheduled meeting**.
	     * 3. If a meeting's **start time** is greater than the last scheduled meeting's end time, 
	     *    select it and update `lastEndTime`.
	     * 4. Keep a count of the number of meetings that can be conducted.
	     * 
	     * @param start - array of meeting start times
	     * @param end - array of meeting end times
	     * @param n - number of meetings
	     * @return Maximum number of non-overlapping meetings
	     */
	    public static int maxMeetings(int[] start, int[] end, int n) {
	        List<Meeting> meetings = new ArrayList<>();
	        
	        // Step 1: Store meeting start and end times in a list
	        for (int i = 0; i < n; i++) {
	            meetings.add(new Meeting(start[i], end[i]));
	        }

	        // Step 2: Sort meetings by end time to ensure we choose meetings that finish earlier
	        meetings.sort(Comparator.comparingInt(a -> a.end));

	        int count = 0;        // Number of meetings that can be conducted
	        int lastEndTime = -1; // Stores the end time of the last selected meeting

	        // Step 3: Iterate through sorted meetings and apply greedy selection
	        for (Meeting m : meetings) {
	            if (m.start > lastEndTime) {  // If meeting starts after last meeting ends, schedule it
	                count++;
	                lastEndTime = m.end;  // Update last scheduled meeting end time
	            }
	        }

	        return count;  // Return the maximum number of meetings
	    }

	    public static void main(String[] args) {
	        int[] start = {1, 3, 0, 5, 8, 5};
	        int[] end = {2, 4, 6, 7, 9, 9};
	        int n = start.length;

	        System.out.println("Maximum meetings: " + maxMeetings(start, end, n)); 
	        // Expected Output: 4 (Meetings at index 0, 1, 3, 4)
	    }
	}

	/**
	 * Dry Run:
	 * Given:
	 * Start:  [1, 3, 0, 5, 8, 5]
	 * End:    [2, 4, 6, 7, 9, 9]
	 *
	 * Step 1: Sort meetings by end time:
	 * [(1,2), (3,4), (0,6), (5,7), (8,9), (5,9)]
	 *
	 * Step 2: Selecting meetings greedily:
	 * ✅ (1,2) → ✅ (3,4) → ❌ (0,6) → ✅ (5,7) → ✅ (8,9) → ❌ (5,9)
	 *
	 * Maximum Meetings = 4
	 */


}
