package com.stack;

import java.util.*;

public class Leetcode84_LargestRectangleinHistogram {
    
    // Generic Pair class to store a key-value pair (height, index)
    class Pair<E, T> {
        E key;    // Represents the height of the histogram bar
        T value;  // Represents the index of the bar in the array

        public Pair(E key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Nearest Smaller to Left (NSL):
     * --------------------------------
     * Finds the index of the nearest smaller element to the left of each element in the array.
     * This helps determine the left boundary for calculating the width of the rectangle.
     *
     * Intuition:
     * - We traverse the array from left to right.
     * - We use a **monotonic increasing stack** to keep track of elements and their indices.
     * - If the stack is empty, there is no smaller element on the left → store -1.
     * - If the stack's top is smaller, it is the nearest smaller element → store its index.
     * - If the stack's top is greater, we pop elements until we find a smaller one.
     */
    public List<Integer> NSL(int[] arr) {
        List<Integer> res = new ArrayList<>();   // Result list to store the nearest smaller indices
        Stack<Pair<Integer, Integer>> s = new Stack<>();   // Stack to maintain values and their indices

        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && s.peek().key >= arr[i]) {
                s.pop();
            }
            res.add(s.isEmpty() ? -1 : s.peek().value);
            s.push(new Pair<>(arr[i], i)); // Push the current element and its index onto the stack
        }
        return res;
    }

    /**
     * Nearest Smaller to Right (NSR):
     * --------------------------------
     * Finds the index of the nearest smaller element to the right of each element in the array.
     * This helps determine the right boundary for calculating the width of the rectangle.
     *
     * Intuition:
     * - We traverse the array from right to left.
     * - We use a **monotonic increasing stack** to keep track of elements and their indices.
     * - If the stack is empty, there is no smaller element on the right → store array length.
     * - If the stack's top is smaller, it is the nearest smaller element → store its index.
     * - If the stack's top is greater, we pop elements until we find a smaller one.
     */
    public List<Integer> NSR(int[] arr) {
        List<Integer> res = new ArrayList<>();   // Result list to store the nearest smaller indices
        Stack<Pair<Integer, Integer>> s = new Stack<>();   // Stack to maintain values and their indices

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek().key >= arr[i]) {
                s.pop();
            }
            res.add(s.isEmpty() ? arr.length : s.peek().value);
            s.push(new Pair<>(arr[i], i)); // Push the current element and its index onto the stack
        }
        Collections.reverse(res); // Since we traversed from right to left, reverse the result
        return res;
    }

    /**
     * Largest Rectangle Area in Histogram:
     * -------------------------------------
     * Given an array representing histogram heights, this method calculates
     * the maximum area of a rectangle that can be formed.
     *
     * Intuition:
     * - For each bar in the histogram, find how far it can extend left and right.
     * - The width of the rectangle is determined using the nearest smaller elements.
     * - Multiply height by width to find area.
     * - Track the maximum area.
     *
     * Dry Run:
     * Heights: [2, 1, 5, 6, 2, 3]
     * NSL: [-1, -1, 1, 2, 1, 4]
     * NSR: [1, 6, 4, 4, 6, 6]
     * Width: [1, 6, 2, 1, 4, 1]
     * Areas: [2, 6, 10, 6, 8, 3]
     * Max Area: **10**
     */
    public int largestRectangleArea(int[] heights) {
        List<Integer> left = NSL(heights);    // Get nearest smaller elements to the left for each bar
        List<Integer> right = NSR(heights);   // Get nearest smaller elements to the right for each bar
        int maxArea = 0;  // Variable to store the maximum area

        for (int i = 0; i < heights.length; i++) {
            int width = right.get(i) - left.get(i) - 1; // Calculate width of rectangle for current bar
            int area = width * heights[i]; // Calculate area
            maxArea = Math.max(maxArea, area); // Update the maximum area
        }
        return maxArea; // Return the maximum rectangular area
    }

    /**
     * Dry Run for overall execution:
     * --------------------------------
     * Input: heights = [2, 1, 5, 6, 2, 3]
     * Step 1: Compute NSL -> [-1, -1, 1, 2, 1, 4]
     * Step 2: Compute NSR -> [1, 6, 4, 4, 6, 6]
     * Step 3: Compute Width -> [1, 6, 2, 1, 4, 1]
     * Step 4: Compute Areas -> [2, 6, 10, 6, 8, 3]
     * Step 5: Find max -> 10
     */
    public static void main(String[] args) {
        Leetcode84_LargestRectangleinHistogram obj = new Leetcode84_LargestRectangleinHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Maximum Area: " + obj.largestRectangleArea(heights)); // Expected Output: 10
    }
}
