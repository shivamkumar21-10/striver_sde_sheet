package neetcode.array;

import java.util.*;

public class Leetcode49_GroupAnagrams {
	
//*************************APPROACH - 1(Sorting) ********************************************
	class Solution {
	    public List<List<String>> groupAnagrams(String[] strs) {
	        // Intuition:
	        // Anagrams are strings that have the same characters in any order.
	        // If we sort the characters in the string, all anagrams will look the same.
	        // We can use this sorted string as a key in a map and group the anagrams together.

	        // Map to store sorted string as key and list of anagrams as value
	        Map<String, List<String>> map = new HashMap<>();

	        // Traverse each word in the input array
	        for (int i = 0; i < strs.length; i++) {
	            String curr = strs[i]; // Current string

	            // Convert string to character array and sort it to get the normalized key
	            char[] arr = curr.toCharArray();
	            Arrays.sort(arr); // Sorting helps group all anagrams together
	            String sorted = new String(arr); // This will act as the key

	            // Check if this sorted key already exists in map
	            if (map.containsKey(sorted)) {
	                // If yes, retrieve the list and add the current string
	                List<String> currList = map.get(sorted);
	                currList.add(curr); // Add original string to the anagram group
	                map.put(sorted, currList); // (Reinserting not necessary as we modify reference)
	            } else {
	                // If not present, create a new list with the current string
	                map.put(sorted, new ArrayList<>(Arrays.asList(curr)));
	            }
	        }

	        // Prepare final answer list
	        List<List<String>> ans = new ArrayList<>();

	        // Extract all grouped anagrams from the map and add to result
	        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	            ans.add(entry.getValue()); // Each value is a list of grouped anagrams
	        }

	        return ans; // Return final list of grouped anagrams

	        // Dry Run Example:
	        // Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
	        // Sorted keys:
	        // "eat" -> "aet", "tea" -> "aet", "tan" -> "ant"
	        // "ate" -> "aet", "nat" -> "ant", "bat" -> "abt"
	        // Map will look like:
	        // "aet": ["eat", "tea", "ate"]
	        // "ant": ["tan", "nat"]
	        // "abt": ["bat"]
	        // Final output: [["eat","tea","ate"],["tan","nat"],["bat"]]

	        // Time Complexity: O(n * k log k)
	        // where n = number of strings, k = max length of a string
	        // Sorting each string takes O(k log k), done for n strings

	        // Space Complexity: O(n * k)
	        // Map stores n strings of average length k
	        // Sorting uses temporary O(k) per string as well
	    }
	}


}
