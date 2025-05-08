package neetcode.array;

public class ProductsofArrayExceptSelf {
	
	//My solution
	class Solution {
	    public int[] productExceptSelf(int[] nums) {
	        int n = nums.length;

	        // Create output array to store final results
	        int[] ans = new int[n];

	        // Initialize prefix and suffix multipliers
	        int[] pre = new int[n]; // pre[i] = product of all elements before index i
	        int[] suf = new int[n]; // suf[i] = product of all elements after index i

	        // Initialize first value of prefix product
	        pre[0] = 1; // Nothing to the left of index 0
	        for (int i = 1; i < n; i++) {
	            pre[i] = pre[i - 1] * nums[i - 1]; // Multiply previous prefix by previous element
	        }

	        // Initialize last value of suffix product
	        suf[n - 1] = 1; // Nothing to the right of last index
	        for (int i = n - 2; i >= 0; i--) {
	            suf[i] = suf[i + 1] * nums[i + 1]; // Multiply next suffix by next element
	        }

	        // Multiply prefix and suffix products to get the answer
	        for (int i = 0; i < n; i++) {
	            ans[i] = pre[i] * suf[i];
	        }

	        return ans;

	        /*
	        ----------------------------
	        ✅ Intuition:
	        - We can't use division.
	        - So for each index i, answer[i] = product of all elements to left * product of all elements to right

	        ----------------------------
	        🧠 Dry Run:
	        nums = [1, 2, 3, 4]

	        pre = [1, 1, 2, 6]
	              [1, 1*1, 1*2, 1*2*3]

	        suf = [24, 12, 4, 1]
	              [2*3*4, 3*4, 4, 1]

	        ans = [24, 12, 8, 6]

	        ----------------------------
	        ⏱️ Time Complexity:
	        O(n) - Three linear traversals

	        🧠 Space Complexity:
	        O(n) - Used two additional arrays (pre and suf)
	        Can be reduced to O(1) extra space if we modify ans in-place with prefix and then use a suffix variable
	        ----------------------------
	        */
	    }
	}

	//Optimixed space complexity  to O(N) not using prefix & suffix array using ans array to do so
	class Solution2 {
	    public int[] productExceptSelf(int[] nums) {
	        int n = nums.length;
	        int[] ans = new int[n];

	        // Step 1: Build prefix product in the output array itself
	        // ans[i] will contain product of all elements before index i
	        ans[0] = 1; // Nothing to the left of index 0
	        for (int i = 1; i < n; i++) {
	            ans[i] = ans[i - 1] * nums[i - 1];
	        }

	        // Step 2: Multiply with suffix product on the fly using a variable
	        // 'suffix' stores the product of all elements after the current index
	        int suffix = 1;
	        for (int i = n - 1; i >= 0; i--) {
	            ans[i] = ans[i] * suffix;
	            suffix *= nums[i]; // update suffix for the next element to the left
	        }

	        return ans;

	        /*
	        ----------------------------
	        ✅ Intuition:
	        - For each index i, result = product of all elements to its left (prefix) * product of all elements to its right (suffix)
	        - We calculate prefix in the result array
	        - Then multiply with suffix using a single variable to save space

	        ----------------------------
	        🧠 Dry Run:
	        nums = [1, 2, 3, 4]

	        Prefix pass:
	        ans = [1, 1, 2, 6]

	        Suffix pass:
	        suffix = 1
	        i = 3 → ans[3] = 6 * 1 = 6, suffix = 1 * 4 = 4
	        i = 2 → ans[2] = 2 * 4 = 8, suffix = 4 * 3 = 12
	        i = 1 → ans[1] = 1 * 12 = 12, suffix = 12 * 2 = 24
	        i = 0 → ans[0] = 1 * 24 = 24

	        Final ans = [24, 12, 8, 6]

	        ----------------------------
	        ⏱️ Time Complexity:
	        O(n) — Two linear passes

	        🧠 Space Complexity:
	        O(1) — No extra space used apart from output array
	        ----------------------------
	        */
	    }
	}


}
