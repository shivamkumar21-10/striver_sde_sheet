package com.stack;

import java.util.Stack;

public class SortaStack {

	// Function to reverse the entire stack using recursion
	public static void reverse(Stack<Integer> s) {
		/*
		 * ---------------------- INTUITION ---------------------- - We need to reverse
		 * a stack without using extra space. - Instead of using an auxiliary stack, we
		 * use recursion to manipulate the original stack. - If we can: 1. Remove the
		 * top element 2. Reverse the remaining stack 3. Insert the removed element at
		 * the bottom - The stack will be reversed.
		 * 
		 * ---------------------- BASE CASE ---------------------- - If the stack has
		 * only one element, it's already reversed, so return.
		 */
		if (s.size() == 1) {
			return;
		}

		/*
		 * ---------------------- RECURSIVE CASE ---------------------- - Step 1: Remove
		 * the top element and store it. - Step 2: Recursively reverse the remaining
		 * stack. - Step 3: Insert the removed element at the bottom of the reversed
		 * stack.
		 */
		int temp = s.pop(); // Store the top element
		reverse(s); // Recursively reverse the remaining stack
		insert(s, temp); // Insert the removed element back at the correct position in reversed stack
	}

	// Helper function to insert an element at the bottom of the stack
	private static void insert(Stack<Integer> s, int temp) {
		/*
		 * ---------------------- INTUITION ---------------------- - When we remove all
		 * elements, the stack becomes empty. - The deepest recursive call places the
		 * first removed element at the bottom. - Each function call then pushes back
		 * the previously removed elements in order.
		 */

		// Base case: If stack is empty, push the element to the bottom
		if (s.size() == 0) {
			s.push(temp); // Push the element when the stack is empty
			return;
		}

		/*
		 * ---------------------- RECURSIVE CASE ---------------------- - Step 1: Remove
		 * the top element and store it. - Step 2: Recursively insert 'temp' in the
		 * smaller stack. - Step 3: Push the removed element back on top.
		 */
		int last = s.pop(); // Store the top element
		insert(s, temp); // Recursively insert 'temp' in the smaller stack
		s.push(last); // Push the last element back to the top after 'temp' is inserted at the bottom
	}

	public static void main(String[] args) {
		/*
		 * ---------------------- STACK INITIALIZATION ---------------------- - Creating
		 * a stack and pushing elements onto it.
		 */
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);

		// Printing the original stack before reversal
		System.out.println("Original Stack: " + s);

		// Call the reverse function to reverse the stack
		reverse(s);

		// Print the reversed stack
		System.out.println("Reversed Stack: " + s);
	}
}

/*
 * ---------------------------------------------------------------- ## **DRY RUN
 * OF THE CODE (Step-by-Step Execution)**
 * ---------------------------------------------------------------- ###
 * **Initial Stack Before Reversal:** Stack: [1, 2, 3, 4, 5, 6, 7] (Top: 7)
 * 
 * ### **Step 1: Calling `reverse(s)` (Recursive Calls)** -
 * `reverse([1,2,3,4,5,6,7])` → Pops **7**, calls `reverse([1,2,3,4,5,6])` -
 * `reverse([1,2,3,4,5,6])` → Pops **6**, calls `reverse([1,2,3,4,5])` -
 * `reverse([1,2,3,4,5])` → Pops **5**, calls `reverse([1,2,3,4])` -
 * `reverse([1,2,3,4])` → Pops **4**, calls `reverse([1,2,3])` -
 * `reverse([1,2,3])` → Pops **3**, calls `reverse([1,2])` - `reverse([1,2])` →
 * Pops **2**, calls `reverse([1])` - `reverse([1])` → Base case reached,
 * recursion starts **unwinding**.
 * 
 * ### **Step 2: Calling `insert(s, temp)` (Rebuilding in Reverse Order)** -
 * `insert([ ], 1 )` → Stack: `[1]` - `insert([1], 2 )` → Stack: `[2, 1]` -
 * `insert([2,1], 3 )` → Stack: `[3, 2, 1]` - `insert([3,2,1], 4 )` → Stack:
 * `[4, 3, 2, 1]` - `insert([4,3,2,1], 5 )` → Stack: `[5, 4, 3, 2, 1]` -
 * `insert([5,4,3,2,1], 6 )` → Stack: `[6, 5, 4, 3, 2, 1]` -
 * `insert([6,5,4,3,2,1], 7 )` → Stack: `[7, 6, 5, 4, 3, 2, 1]`
 * 
 * ### **Final Stack After Reversal:** Stack: **[7, 6, 5, 4, 3, 2, 1]** (Top: 1)
 * 
 * ---------------------------------------------------------------- ## **TIME
 * COMPLEXITY ANALYSIS**
 * ---------------------------------------------------------------- 1. **Reverse
 * Function (`reverse(s)`)** - Calls `insert()` for each element. - Total `n`
 * calls.
 * 
 * 2. **Insert Function (`insert(s, temp)`)** - Takes **O(n)** time in the worst
 * case.
 * 
 * 3. **Overall Complexity** - `reverse()` calls `insert()` **n** times. - Each
 * `insert()` takes **O(n)** in worst case. - **Total Complexity: O(n²)
 * (Quadratic time complexity).**
 * ----------------------------------------------------------------
 */
