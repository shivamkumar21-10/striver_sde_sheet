package com.string2;
import java.util.*;
public class Leetcode242_ValidAnagram {
	
//	*****************************Using Sorting -- not optimal ************************
	

	class Solution {
	    /**
	     * Function to check if two strings are anagrams.
	     *
	     * @param s First input string.
	     * @param t Second input string.
	     * @return true if t is an anagram of s, false otherwise.
	     *
	     * 🔹 Intuition:
	     * - Two strings are anagrams if they contain the same characters with the same frequency.
	     * - Sorting both strings should result in identical sequences if they are anagrams.
	     * - Convert both strings into character arrays, sort them, and compare them.
	     */
	    public boolean isAnagram(String s, String t) {
	        // Convert first string to character array
	        char[] c = s.toCharArray();
	        // Sort the character array
	        Arrays.sort(c);

	        // Convert second string to character array
	        char[] b = t.toCharArray();
	        // Sort the character array
	        Arrays.sort(b);

	        // Compare the sorted versions of both strings
	        return new String(c).equals(new String(b)) ? true : false;
	        // This returns true if sorted strings are equal, otherwise false.
	    }
	}

	/*
	====================================================================
	🔹 Dry Run Example:
	====================================================================

	Example 1:
	s = "listen", t = "silent"

	Step 1: Convert to character arrays:
	    c = ['l', 'i', 's', 't', 'e', 'n']
	    b = ['s', 'i', 'l', 'e', 'n', 't']

	Step 2: Sort the arrays:
	    c = ['e', 'i', 'l', 'n', 's', 't']
	    b = ['e', 'i', 'l', 'n', 's', 't']

	Step 3: Compare sorted strings:
	    "eilnst" == "eilnst" → ✅ True (Anagram)

	Example 2:
	s = "rat", t = "car"

	Step 1: Convert to character arrays:
	    c = ['r', 'a', 't']
	    b = ['c', 'a', 'r']

	Step 2: Sort the arrays:
	    c = ['a', 'r', 't']
	    b = ['a', 'c', 'r']

	Step 3: Compare sorted strings:
	    "art" != "acr" → ❌ False (Not Anagram)

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity: O(N log N)** (Sorting takes O(N log N), converting to char array takes O(N))
	- **Space Complexity: O(N)** (Two additional character arrays of size N)
	====================================================================
*/
	
//	*********************Using HashMap -- optimal **************************************************

	class Solution2 {
	    /**
	     * Function to check if two strings are anagrams using HashMap.
	     *
	     * @param s First input string.
	     * @param t Second input string.
	     * @return true if t is an anagram of s, false otherwise.
	     *
	     * 🔹 Intuition:
	     * - Instead of sorting, we use a HashMap to store character frequencies.
	     * - If both strings are anagrams, the frequency of each character must match.
	     * - We increase the count while traversing `s` and decrease it while traversing `t`.
	     * - At the end, if all values in the HashMap are `0`, it means both strings are anagrams.
	     */
	    public boolean isAnagram(String s, String t) {
	        // If lengths are different, they can't be anagrams.
	        if (s.length() != t.length()) return false;

	        // HashMap to store character frequency.
	        Map<Character, Integer> map = new HashMap<>();

	        // Traverse the first string and populate the frequency map.
	        for (char c : s.toCharArray()) {
	            map.put(c, map.getOrDefault(c, 0) + 1);
	        }

	        // Traverse the second string and decrement the frequency.
	        for (char c : t.toCharArray()) {
	            map.put(c, map.getOrDefault(c, 0) - 1);
	        }

	        // If any character has a non-zero frequency, return false.
	        for (char c : map.keySet()) {
	            if (map.get(c) != 0) {
	                return false;
	            }
	        }

	        // If all character frequencies are zero, the strings are anagrams.
	        return true;
	    }
	}
}
	/*
	====================================================================
	🔹 Dry Run Example:
	====================================================================

	Example 1:
	s = "listen", t = "silent"

	Step 1: Populate frequency map for `s`:
	    {'l': 1, 'i': 1, 's': 1, 't': 1, 'e': 1, 'n': 1}

	Step 2: Reduce frequency using `t`:
	    {'l': 0, 'i': 0, 's': 0, 't': 0, 'e': 0, 'n': 0}

	Step 3: All values are zero → ✅ True (Anagram)

	Example 2:
	s = "rat", t = "car"

	Step 1: Populate frequency map for `s`:
	    {'r': 1, 'a': 1, 't': 1}

	Step 2: Reduce frequency using `t`:
	    {'r': 0, 'a': 0, 't': 1, 'c': -1}  (Mismatch)

	Step 3: Some values are non-zero → ❌ False (Not Anagram)

	====================================================================
	🔹 Comparison with Previous Solution:
	====================================================================

	Previous Sorting Approach:
	✔ Sorts both strings → **O(N log N)**
	✔ Compares sorted versions → **O(N)**
	✔ **Overall Complexity: O(N log N)** (due to sorting)

	Current HashMap Approach:
	✔ Uses a HashMap to store character frequencies
	✔ Traverses both strings once → **O(N)**
	✔ Checks frequency counts → **O(N)**
	✔ **Overall Complexity: O(N)**

	✅ **Why is this optimal?**
	- Sorting takes **O(N log N)**, but HashMap operations (insert/update/get) take **O(1)** on average.
	- Instead of sorting, we just **store and update counts**, making it **faster and more memory-efficient**.

	====================================================================
	🔹 Complexity Analysis:
	====================================================================
	- **Time Complexity: O(N)** (Single pass for each string + HashMap lookup)
	- **Space Complexity: O(1)** (At most 26 keys for lowercase English letters)
	====================================================================
*/
