package com.stack;

import java.util.*;

public class NGL {

    // Method to find the next greater element on the left side for each element in the array
    static List<Integer> nextGreaterLeft(int[] arr) {
        List<Integer> res = new ArrayList<>(); // List to store the results
        Stack<Integer> stk = new Stack<>(); // Stack to keep track of elements as we iterate

        // Traverse the array from the start to end (left to right)
        for (int i = 0; i < arr.length; i++) {
            // Case 1: Stack is empty - no greater element exists to the left
            if (stk.isEmpty()) {
                res.add(-1); // Add -1 to result as there is no "greater" element on the left
            }
            
            // Case 2: Stack is not empty and top of stack is greater than current element
            else if (!stk.isEmpty() && stk.peek() > arr[i]) {
                res.add(stk.peek()); // Add the top of stack to the result as it’s the "next greater left"
            }
            
            // Case 3: Stack is not empty but top of stack is less than or equal to current element
            else if (!stk.isEmpty() && stk.peek() <= arr[i]) {
                // Pop elements from the stack until we find a greater element or the stack is empty
                while (!stk.isEmpty() && stk.peek() <= arr[i]) {
                    stk.pop();
                }

                // If stack is empty after popping, no greater element exists to the left
                if (stk.isEmpty()) {
                    res.add(-1); // Add -1 to the result
                } else {
                    res.add(stk.peek()); // Top of stack now is the "next greater left"
                }
            }
            
            // Push the current element to the stack for future comparisons
            stk.add(arr[i]);
        }
        
        return res; // Return the result list containing "next greater left" for each element
    }

    public static void main(String[] args) {
        int[] inp = new int[] {1, 3, 2, 4};
        System.out.println(nextGreaterLeft(inp)); // Expected output: [-1, -1, 3, -1]
    }
}
