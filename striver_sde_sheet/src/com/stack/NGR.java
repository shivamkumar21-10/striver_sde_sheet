package com.stack;

import java.util.*;

public class NGR {
    
    // Method to find the next greater element for each element in the array
    static List<Integer> nextGreaterRight(int[] arr) {
        List<Integer> res = new ArrayList<>(); // List to store the result
        Stack<Integer> stk = new Stack<>(); // Stack to keep track of elements
        
        /**
         * Intuition:
         * - We need to find the **Next Greater Element (NGE)** for each element in the array.
         * - Instead of a **brute-force approach** (O(N^2)), we use a **monotonic decreasing stack**.
         * - The idea is to traverse the array **from right to left** and maintain a stack 
         *   to efficiently track greater elements.
         * - If an element `arr[i]` has a greater element to its right in the stack, 
         *   that is the NGE. Otherwise, there is no greater element.
         * 
         * Time Complexity: **O(N)** (Each element is pushed and popped from the stack at most once)
         * Space Complexity: **O(N)** (In the worst case, all elements are stored in the stack)
         */

        // Traverse the array from the end to the beginning
        for (int i = arr.length - 1; i >= 0; i--) {
            
            // If the stack is empty, there is no greater element to the right
            if (stk.isEmpty()) {
                res.add(-1); // No next greater element
            }
            // If the top of the stack is greater than the current element
            else if (!stk.isEmpty() && stk.peek() > arr[i]) {
                res.add(stk.peek()); // The top of the stack is the next greater element
            }
            // If the top of the stack is less than or equal to the current element
            else if (!stk.isEmpty() && stk.peek() <= arr[i]) {
                
                // Pop elements from the stack until we find a greater element or stack becomes empty
                while (!stk.isEmpty() && stk.peek() <= arr[i]) {
                    stk.pop();
                }
                
                // If stack is empty, no greater element exists to the right
                if (stk.isEmpty()) {
                    res.add(-1);
                } else {
                    res.add(stk.peek()); // The top of the stack is now the next greater element
                }
            }
            
            // Push the current element to the stack for future comparisons
            stk.add(arr[i]);
        }
        
        // Reverse the result list to match the original array's left-to-right order
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] inp = new int[] {1, 3, 2, 4};
        System.out.println(nextGreaterRight(inp)); // Expected output: [3, 4, 4, -1]
    }
}

/**
 * Dry Run Example:
 * 
 * Given: arr = [1, 3, 2, 4]
 * 
 * Iteration 1 (i = 3, arr[i] = 4):
 * - Stack is empty, so result = [-1]
 * - Push 4 into stack → stack = [4]
 * 
 * Iteration 2 (i = 2, arr[i] = 2):
 * - Top of stack (4) is greater than 2 → result = [4, -1]
 * - Push 2 into stack → stack = [4, 2]
 * 
 * Iteration 3 (i = 1, arr[i] = 3):
 * - Top of stack (2) is **smaller**, so pop 2
 * - Top of stack (4) is **greater**, so result = [4, 4, -1]
 * - Push 3 into stack → stack = [4, 3]
 * 
 * Iteration 4 (i = 0, arr[i] = 1):
 * - Top of stack (3) is greater than 1 → result = [3, 4, 4, -1]
 * - Push 1 into stack → stack = [4, 3, 1]
 * 
 * Final Result (after reversing): [3, 4, 4, -1]
 */
