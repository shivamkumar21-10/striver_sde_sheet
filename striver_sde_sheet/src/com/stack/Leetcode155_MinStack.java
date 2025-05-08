package com.stack;

import java.util.Stack;

public class Leetcode155_MinStack {
	
	class MinStack {
	    /**
	     * Helper class to store pairs of (value, minimum-so-far)
	     * This allows us to efficiently keep track of the minimum value
	     * at any point in the stack.
	     */
	    class Pair{
	        int first;  // Stores the actual value
	        int second; // Stores the minimum value in the stack at this point

	        public Pair(int first, int second){
	            this.first = first;
	            this.second = second;
	        }
	    }

	    // Stack to store elements along with the minimum element at each state
	    Stack<Pair> stk = new Stack<>();

	    /**
	     * Constructor to initialize MinStack
	     */
	    public MinStack() {
	        // Stack is initially empty
	    }
	    
	    /**
	     * Pushes a value onto the stack while maintaining the minimum element so far.
	     * If the stack is empty, the value itself is the minimum.
	     * Otherwise, we push the value along with the new minimum.
	     * 
	     * Intuition:
	     * We need to get the minimum value in constant time O(1). Instead of recomputing the
	     * minimum by iterating through the stack, we store the minimum value along with each element.
	     * 
	     * Logic:
	     * - If stack is empty, push the element as (val, val) since it's the only element.
	     * - Otherwise, calculate the new minimum and push (val, min-so-far).
	     */
	    public void push(int val) {
	        if(stk.isEmpty()){
	            // First element, so min is the value itself
	            stk.push(new Pair(val,val));
	        }
	        else{
	            // Compute the minimum so far and push it with the value
	            int currentMin = Math.min(val, stk.peek().second);
	            stk.push(new Pair(val, currentMin));
	        }
	    }
	    
	    /**
	     * Removes the top element from the stack if it is not empty.
	     * 
	     * Intuition:
	     * We remove the topmost element normally, as we maintain minimum
	     * values along with the stack.
	     */
	    public void pop() {
	        if (!stk.isEmpty()) {
	            stk.pop();
	        }
	    }
	    
	    /**
	     * Returns the top element of the stack.
	     * If the stack is empty, returns -1.
	     * 
	     * Intuition:
	     * The stack follows LIFO (Last In, First Out), so we return the top element.
	     */
	    public int top() {
	        if(!stk.isEmpty()) return stk.peek().first;
	        else return -1;
	    }
	    
	    /**
	     * Returns the minimum element in the stack.
	     * If the stack is empty, returns -1.
	     * 
	     * Intuition:
	     * Since we stored the minimum value at each level, we can fetch it in O(1) time.
	     */
	    public int getMin() {
	        if(!stk.isEmpty()) return stk.peek().second;
	        else return -1;
	    }
	}

	/**
	 * Dry Run:
	 * Operations:
	 * MinStack obj = new MinStack();
	 * obj.push(5);    // Stack: [(5,5)]
	 * obj.push(3);    // Stack: [(5,5), (3,3)]
	 * obj.push(7);    // Stack: [(5,5), (3,3), (7,3)]
	 * obj.push(2);    // Stack: [(5,5), (3,3), (7,3), (2,2)]
	 * obj.getMin();   // Returns 2
	 * obj.pop();      // Stack: [(5,5), (3,3), (7,3)]
	 * obj.getMin();   // Returns 3
	 * obj.pop();      // Stack: [(5,5), (3,3)]
	 * obj.getMin();   // Returns 3
	 * obj.pop();      // Stack: [(5,5)]
	 * obj.getMin();   // Returns 5
	 */

	/**
	 * Your MinStack object will be instantiated and called as such:
	 * MinStack obj = new MinStack();
	 * obj.push(val);
	 * obj.pop();
	 * int param_3 = obj.top();
	 * int param_4 = obj.getMin();
	 */


}
