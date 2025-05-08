package com.greedy;

public class JobSequencingProblem_GFFG {
	class Solution {

	    // A Job class to represent each job with its ID, deadline, and profit
	    class Job {
	        int id;       // Unique ID for the job
	        int deadline; // Deadline by which the job must be completed
	        int profit;   // Profit if the job is completed before or on its deadline

	        Job(int id, int deadline, int profit) {
	            this.id = id;
	            this.deadline = deadline;
	            this.profit = profit;
	        }
	    }

	    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
	        int n = deadline.length;

	        // 🧠 Intuition:
	        // We need to schedule the most profitable jobs such that they are done before their deadline.
	        // We aim to **maximize total profit** and also keep track of the **number of jobs done**.

	        // 🔍 Observation:
	        // Since one job can be done per unit of time, we will try to assign each job to the latest available slot on or before its deadline.
	        // Greedy approach helps: Sort jobs by profit in descending order and assign to latest available time slot.

	        // Step 1️⃣: Create a list of Job objects
	        List<Job> jobs = new ArrayList<>();
	        for (int i = 0; i < n; i++) {
	            jobs.add(new Job(i, deadline[i], profit[i]));
	        }

	        // Step 2️⃣: Sort the jobs in descending order of profit (Greedy approach)
	        jobs.sort((a, b) -> b.profit - a.profit);

	        // Step 3️⃣: Find the maximum deadline to size the schedule slots
	        int maxDeadline = 0;
	        for (Job job : jobs) {
	            maxDeadline = Math.max(maxDeadline, job.deadline);
	        }

	        // Step 4️⃣: Create a slot array to keep track of used time slots
	        boolean[] slots = new boolean[maxDeadline + 1]; // Index 1-based (0 unused)

	        int jobCount = 0;    // To count number of jobs scheduled
	        int totalProfit = 0; // To accumulate total profit

	        // Step 5️⃣: Try to place each job in the latest free slot before its deadline
	        for (Job job : jobs) {
	            for (int t = job.deadline; t > 0; t--) {
	                if (!slots[t]) { // If time slot is free
	                    slots[t] = true; // Mark the slot as filled
	                    jobCount++;     // Count this job
	                    totalProfit += job.profit; // Add profit
	                    break;          // Move on to next job
	                }
	            }
	        }

	        // Step 6️⃣: Return result as list [number of jobs done, total profit]
	        ArrayList<Integer> res = new ArrayList<>();
	        res.add(jobCount);
	        res.add(totalProfit);
	        return res;

	        // ✅ Dry Run Example:
	        // deadline[] = {2, 1, 2}
	        // profit[]   = {100, 19, 27}
	        // After sorting: [100, 27, 19]
	        // Job 0: deadline=2 → slot 2 free → assign to slot 2
	        // Job 2: deadline=2 → slot 2 used → try slot 1 → free → assign to slot 1
	        // Job 1: deadline=1 → slot 1 used → skip
	        // Result: 2 jobs done, total profit = 100 + 27 = 127

	        // ⏱️ Time Complexity:
	        // O(n log n) for sorting + O(n * maxDeadline) in worst case to find slot for each job
	        // So overall: O(n log n + n * D), where D is the max deadline

	        // 📦 Space Complexity:
	        // O(D) for the slot array
	    }
	}


}
