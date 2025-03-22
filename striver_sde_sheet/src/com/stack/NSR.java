package com.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NSR {

    // This method finds the "next smaller element on the right" for each element in the input array
    static List<Integer> nextGreaterRight(int[] arr) {
        List<Integer> res = new ArrayList<>(); // Result list to store final output
        Stack<Integer> stk = new Stack<>(); // Stack to keep track of elements for comparison
        
        // Traverse the array from right to left to find the "next smaller right" for each element
        for (int i = arr.length - 1; i >= 0; i--) {
            
            // Case 1: If stack is empty, no smaller element exists on the right side
            if (stk.isEmpty()) {
                res.add(-1); // Add -1 as there is no smaller element on the right
            }
            
            // Case 2: Stack is not empty and the top of the stack is smaller than the current element
            else if (stk.peek() < arr[i]) {
                res.add(stk.peek()); // The top of stack is the next smaller right element
            }
            
            // Case 3: Stack is not empty, but the top of stack is greater than or equal to the current element
            else if (stk.peek() >= arr[i]) {
                
                // Pop elements until we find a smaller element or the stack becomes empty
                while (!stk.isEmpty() && stk.peek() >= arr[i]) {
                    stk.pop();
                }
                
                // After popping, check if stack is empty
                if (stk.isEmpty()) {
                    res.add(-1); // No smaller element on the right if stack is empty
                } else {
                    res.add(stk.peek()); // Found the next smaller right element
                }
            }
            
            // Push the current element onto the stack for future comparisons
            stk.add(arr[i]);
        }
        
        // Reverse the result list as we traversed from right to left
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        // Sample input array
        int[] inp = new int[] {1, 3, 2, 4};
        
        // Print the result which contains the "next smaller right" elements for each element in the array
        System.out.println(nextGreaterRight(inp));
    }
}
