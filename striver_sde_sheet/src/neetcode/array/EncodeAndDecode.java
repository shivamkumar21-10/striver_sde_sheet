package neetcode.array;

import java.util.*;

public class EncodeAndDecode {
	
	class Solution {

	    // Function to encode a list of strings into a single string
	    public String encode(List<String> strs) {
	        // Initialize a StringBuffer for efficient string concatenation
	        StringBuffer sb = new StringBuffer();

	        // Iterate through the list of strings
	        for(int i = 0; i < strs.size(); i++){
	            // For each string, append its length followed by a '#' and then the string itself
	            // This helps in identifying where one string ends and the next begins during decoding
	            sb.append(strs.get(i).length());
	            sb.append("#");
	            sb.append(strs.get(i));
	        }

	        // Return the final encoded string
	        return sb.toString();

	        /*
	        Example:
	        Input: ["hello", "world"]
	        Encoded: "5#hello5#world"
	        */
	    }

	    // Function to decode the encoded string back into the list of strings
	    public List<String> decode(String str) {
	        // Result list to store decoded strings
	        List<String> ans = new ArrayList<>();
	        int i = 0;

	        // Start traversing the encoded string
	        while(i < str.length()){
	            int j = i;

	            // Find the position of '#' to identify the number part (length of original string)
	            while (str.charAt(j) != '#') {
	                j++;
	            }

	            // Extract the number (length of the next string) from i to j
	            int len = Integer.parseInt(str.substring(i, j));
	            j++; // Move past the '#'

	            // Now extract the actual string of length `len` starting from position j
	            String word = str.substring(j, j + len);
	            ans.add(word); // Add the decoded word to result

	            // Move i to the next encoded part
	            i = j + len;
	        }

	        return ans;

	        /*
	        Dry Run:
	        str = "5#hello5#world"
	        i = 0 → j = 1 → len = 5 → j = 2 → word = "hello" → i = 7
	        i = 7 → j = 8 → len = 5 → j = 9 → word = "world" → i = 14
	        Output: ["hello", "world"]
	        */
	    }
	}

	/*
	------------------------------------
	⏱️ Time Complexity:
	- encode: O(N), where N is total characters in all strings
	- decode: O(N), where N is total length of the encoded string

	🧠 Space Complexity:
	- encode: O(1) extra (excluding output string)
	- decode: O(K) where K is the number of strings (for output list)
	------------------------------------
	*/


}
