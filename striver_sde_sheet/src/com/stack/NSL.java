package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NSL {

    // This method returns a list of "next smaller elements on the left" for each element in the array.
    // Each element in the result list corresponds to the nearest smaller element found on the left side of the array.
    static List<Integer> nextGreaterLeft(int[] arr) {
        List<Integer> res = new ArrayList<>(); // Result list to store the final answer
        Stack<Integer> stk = new Stack<>(); // Stack to keep track of elements in a decreasing order from the left
        
        // Traverse the array from left to right
        for(int i = 0; i < arr.length; i++) {
            // Case 1: If stack is empty, no smaller element exists on the left side
            if(stk.isEmpty()) {
                res.add(-1); // Add -1 to result as there is no "smaller" element on the left
            }
            
            // Case 2: Stack is not empty and top of stack is smaller than current element
            else if(stk.peek() < arr[i]) {
                res.add(stk.peek()); // Add the top of stack as it’s the "next smaller left"
            }
            
            // Case 3: Stack is not empty but top of stack is greater than or equal to the current element
            else if(stk.peek() >= arr[i]) {
                // Remove elements from the stack until we find a smaller element or the stack is empty
                while(!stk.isEmpty() && stk.peek() >= arr[i]) {
                    stk.pop();
                }
                
                // After popping, if stack is empty, there is no smaller element on the left
                if(stk.isEmpty()) {
                    res.add(-1); // Add -1 since no smaller element is found
                } else {
                    res.add(stk.peek()); // Add the top of stack as it’s the "next smaller left"
                }
            }
            
            // Push the current element to stack, useful for future elements in array
            stk.add(arr[i]);
        }
        
        return res; // Return the list of next smaller elements on the left
    }

    public static void main(String[] args) {
        // Sample input array
        int[] inp = new int[] {1, 3, 2, 4};
        
        // Print the result which contains the "next smaller left" elements for each element in the array
        System.out.println(nextGreaterLeft(inp));
    }
}
