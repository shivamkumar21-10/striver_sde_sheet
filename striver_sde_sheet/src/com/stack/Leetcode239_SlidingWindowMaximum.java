package com.stack;

import java.util.*;

public class Leetcode239_SlidingWindowMaximum {

    // Function to find the maximum in each sliding window of size 'k'
    public static int[] maxSlidingWindow(int[] nums, int k) {
        /* 
        ---------------------- INTUITION ----------------------
        - We need to find the maximum element in every subarray (window) of size 'k'.
        - Instead of recalculating the max for each window (O(n*k)), we use a **deque** (O(n)).
        - The deque maintains **indices of elements** in decreasing order.
        - The **front of the deque** always stores the max of the current window.
        - When the window moves, we:
          1. Remove out-of-bound elements from the front.
          2. Maintain a decreasing order (remove smaller elements from the back).
          3. Add the deque’s front element to the result list.

        ---------------------- VARIABLES ----------------------
        */
        int i = 0;  // Left pointer (start of the window)
        int j = 0;  // Right pointer (end of the window)

        List<Integer> lst = new ArrayList<>(); // Stores max of each window

        // Deque to store indices of elements, ensuring descending order
        Deque<Integer> deque = new ArrayDeque<>();

        // Start iterating through the array
        while (j < nums.length) {

            /*
            ---------------------- MAINTAIN DECREASING ORDER ----------------------
            - Remove elements from the back of the deque that are smaller than nums[j].
            - This ensures the largest element is always at the front.
            */
            while (!deque.isEmpty() && deque.getLast() < nums[j]) {
                deque.removeLast();  // Remove smaller elements
            }

            // Add the current element (index) to the deque
            deque.addLast(nums[j]);

            /*
            ---------------------- VALID WINDOW CHECK ----------------------
            - We form a valid window when `j + 1 >= k`.
            - We now record the max element for this window.
            */
            if (j + 1 >= k) {

                // The maximum for the current window is the front of the deque
                lst.add(deque.getFirst());

                // If the element at index `i` is at the front of the deque, remove it (as it leaves the window)
                if (deque.getFirst() == nums[i]) {
                    deque.removeFirst();
                }

                // Move left pointer to shrink the window
                i++;
            }

            // Move right pointer to expand the window
            j++;
        }

        /*
        ---------------------- CONVERT LIST TO ARRAY ----------------------
        - Convert `lst` (ArrayList) to an integer array and return.
        */
        int[] res = new int[lst.size()];
        for (int p = 0; p < lst.size(); p++) {
            res[p] = lst.get(p);
        }

        return res;
    }

    public static void main(String[] args) {
        /*
        ---------------------- TEST CASE ----------------------
        - Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        - Expected Output: [3, 3, 5, 5, 6, 7]
        */
        int[] q = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ans = maxSlidingWindow(q, 3);

        // Print the result
        for (int p = 0; p < ans.length; p++) {
            System.out.print(ans[p] + " ");
        }
    }
}

/*
----------------------------------------------------------------
## **DRY RUN OF THE CODE (Step-by-Step Execution)**
----------------------------------------------------------------
### **Initial Input:**
   nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
   Window size = 3

### **Step 1: Sliding Window Processing**
   - Start with an **empty deque**.
   - Maintain a **decreasing order** in deque (store indices).

### **Step-by-Step Execution:**
| Window        | Deque (Stores Indices) | Max Element |
|--------------|----------------------|-------------|
| [1, 3, -1]  | [3, -1]               | 3           |
| [3, -1, -3] | [3, -1, -3]           | 3           |
| [-1, -3, 5] | [5]                   | 5           |
| [-3, 5, 3]  | [5, 3]                | 5           |
| [5, 3, 6]   | [6]                   | 6           |
| [3, 6, 7]   | [7]                   | 7           |

### **Final Output:**
   **[3, 3, 5, 5, 6, 7]**

----------------------------------------------------------------
## **TIME COMPLEXITY ANALYSIS**
----------------------------------------------------------------
1. **Each element is processed once** (added & removed from deque at most once).
2. **Deque operations (add/remove) take O(1).**
3. **Overall Complexity: O(n) (Linear time).**
----------------------------------------------------------------
*/
