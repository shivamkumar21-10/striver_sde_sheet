package com.Array4;

import java.util.HashMap;

public class Leetcode3_LongestSubstringWithoutRepeatingCharacters {
	
	class Solution {
	    public int lengthOfLongestSubstring(String s) {
	        // Intuition: The problem asks us to find the length of the longest substring that doesn't contain repeating characters.
	        // To do this efficiently, we can use the sliding window technique. The idea is to use two pointers ('i' and 'j') to represent 
	        // a window, and a HashMap to keep track of the frequencies of characters within that window. By expanding the window with 'j'
	        // and shrinking it with 'i', we can easily track and avoid repeating characters.

	        int i = 0;  // Left pointer of the sliding window, initially pointing to the start of the string
	        int j = 0;  // Right pointer of the sliding window, initially pointing to the start of the string
	        HashMap<Character, Integer> map = new HashMap<>();  // Map to store the frequency of characters in the current window
	        int maxLen = 0;  // This will store the length of the longest valid substring found so far
	        
	        // Logic: The outer while loop moves the right pointer ('j') across the string. For each character, we add it to the map 
	        // and check if there are any repeating characters. If there are, we move the left pointer ('i') to shrink the window 
	        // and remove the repeating character(s) until the window becomes valid again (i.e., no duplicates).
	        while (j < s.length()) {
	            // Add the character at 'j' to the map or update its frequency
	            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);

	            // Step 1: While loop ensures that the window only contains unique characters
	            // If the window contains duplicate characters (i.e., map.size() < j - i + 1), we move 'i' to shrink the window
	            while (map.size() < j - i + 1) {
	                // Decrease the frequency of the character at 'i' in the map (i.e., removing it from the window)
	                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
	                
	                // If the frequency of the character at 'i' is now 0, remove it from the map (character no longer in the window)
	                if (map.get(s.charAt(i)) == 0) {
	                    map.remove(s.charAt(i));
	                }
	                
	                // Move 'i' to the right to shrink the window
	                i++;
	            }

	            // Step 2: If the window has unique characters, update the maxLen with the length of the current valid window
	            if (map.size() == j - i + 1) {
	                maxLen = Math.max(maxLen, j - i + 1);
	            }

	            // Step 3: Move the right pointer 'j' to the right to expand the window for the next iteration
	            j++;
	        }

	        // Return the length of the longest substring without repeating characters
	        return maxLen;
	    }
	}

	
//	Detailed Dry Run:
//		Input: s = "abcabcbb"
//
//		Initial State:
//		i = 0, j = 0, maxLen = 0, map = {} (empty map at the start)
//		
//		
//		First iteration (j = 0):
//		Character at j = 0 is 'a'.
//		Add 'a' to the map: map = {'a': 1}.
//		The window contains only one character 'a', so it's valid and no duplicates.
//		Update maxLen = Math.max(0, 1) = 1 (length of window is 1).
//		Move j to 1 for the next iteration.
//		
//		
//		Second iteration (j = 1):
//			
//		Character at j = 1 is 'b'.
//		Add 'b' to the map: map = {'a': 1, 'b': 1}.
//		The window contains 'a' and 'b', both unique, so the window is valid.
//		Update maxLen = Math.max(1, 2) = 2 (length of window is 2).
//		Move j to 2 for the next iteration.
//		
//		
//		Third iteration (j = 2):
//			
//		Character at j = 2 is 'c'.
//		Add 'c' to the map: map = {'a': 1, 'b': 1, 'c': 1}.
//		The window contains 'a', 'b', and 'c', all unique.
//		Update maxLen = Math.max(2, 3) = 3 (length of window is 3).
//		Move j to 3 for the next iteration.
//		
//		
//		Fourth iteration (j = 3):
//
//		Character at j = 3 is 'a'.
//		Add 'a' to the map: map = {'a': 2, 'b': 1, 'c': 1}.
//		Now the map contains duplicate characters ('a' appears twice), so we need to shrink the window by moving i.
//		Move i to 1, remove one occurrence of 'a': map = {'a': 1, 'b': 1, 'c': 1}.
//		The window now contains 'b', 'c', and 'a', which are valid.
//		maxLen remains 3 (window length is still 3).
//		Move j to 4 for the next iteration.
//		
//		
//		Fifth iteration (j = 4):
//
//		Character at j = 4 is 'b'.
//		Add 'b' to the map: map = {'a': 1, 'b': 2, 'c': 1}.
//		The map contains duplicate characters ('b' appears twice), so we need to shrink the window by moving i.
//		Move i to 2, remove one occurrence of 'b': map = {'a': 1, 'b': 1, 'c': 1}.
//		The window now contains 'c', 'a', and 'b', which are valid.
//		maxLen remains 3 (window length is still 3).
//		Move j to 5 for the next iteration.
//		
//		
//		Sixth iteration (j = 5):
//			
//		Character at j = 5 is 'c'.
//		Add 'c' to the map: map = {'a': 1, 'b': 1, 'c': 2}.
//		The map contains duplicate characters ('c' appears twice), so we need to shrink the window by moving i.
//		Move i to 3, remove one occurrence of 'c': map = {'a': 1, 'b': 1, 'c': 1}.
//		The window now contains 'a', 'b', and 'c', which are valid.
//		maxLen remains 3 (window length is still 3).
//		Move j to 6, but since the loop ends at j = s.length(), we exit.
//		
//		
//		Final Output:
//
//		The longest substring without repeating characters is "abc", and its length is 3.
//		Output: 3




}
