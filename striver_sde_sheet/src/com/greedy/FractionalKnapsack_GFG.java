package com.greedy;

import java.util.*;

public class FractionalKnapsack_GFG {
	class Solution {
	    
	    // Class to store item with value and weight
	    class Item {
	        int value, weight;

	        Item(int value, int weight) {
	            this.value = value;
	            this.weight = weight;
	        }
	    }

	    // Function to get the maximum total value in the knapsack.
	    double fractionalKnapsack(List<Integer> val, List<Integer> wt, int capacity) {
	        int n = val.size();

	        // 🧠 Intuition:
	        // We are allowed to take fractions of items to maximize total value.
	        // Greedy approach: Always pick the item with highest value-to-weight ratio first.

	        // Step 1️⃣: Create a list of items with value and weight
	        List<Item> items = new ArrayList<>();
	        for (int i = 0; i < n; i++) {
	            items.add(new Item(val.get(i), wt.get(i)));
	        }

	        // Step 2️⃣: Sort items based on value/weight ratio in descending order
	        items.sort((a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

	        double totalValue = 0.0; // Total value accumulated in knapsack
	        int remainingCapacity = capacity;

	        // Step 3️⃣: Traverse sorted items and take as much as possible from each
	        for (Item item : items) {
	            if (remainingCapacity == 0) break; // Knapsack is full

	            if (item.weight <= remainingCapacity) {
	                // Take the whole item
	                totalValue += item.value;
	                remainingCapacity -= item.weight;
	            } else {
	                // Take the fraction of the item
	                double fraction = (double) remainingCapacity / item.weight;
	                totalValue += item.value * fraction;
	                remainingCapacity = 0; // Now it's full
	                break;
	            }
	        }

	        // ✅ Dry Run Example:
	        // val = [60, 100, 120], wt = [10, 20, 30], capacity = 50
	        // Ratios = [6.0, 5.0, 4.0]
	        // Take full 60 (10kg), full 100 (20kg), 20kg from 120 (4.0 per kg) → total value = 60 + 100 + 80 = 240

	        // ⏱️ Time Complexity: 
	        // O(n log n) for sorting + O(n) for iteration ⇒ Total: O(n log n)

	        // 📦 Space Complexity: 
	        // O(n) for storing items

	        return totalValue;
	    }
	}


}
