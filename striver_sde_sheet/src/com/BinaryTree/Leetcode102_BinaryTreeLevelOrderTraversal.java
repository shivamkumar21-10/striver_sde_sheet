package com.BinaryTree;

import java.util.*;


public class Leetcode102_BinaryTreeLevelOrderTraversal {


		class Solution {
			public List<List<Integer>> levelOrder(TreeNode root) {
				// Initialize the result list to store levels of the tree.
				List<List<Integer>> ans = new ArrayList<>();

				// Use a queue to traverse the tree level by level.
				Queue<TreeNode> q = new LinkedList<>();

				// Edge case: If the root is null, return an empty list.
				if (root == null)
					return ans;

				// Add the root node to the queue as the starting point of traversal.
				q.add(root);

				// Add a null marker to indicate the end of the first level.
				q.add(null);

				// Temporary list to store values of the current level.
				List<Integer> tempAns = new ArrayList<>();

				// Perform a breadth-first traversal of the tree.
				while (!q.isEmpty()) {
					// Get the front node from the queue.
					TreeNode temp = q.poll();

					// If the node is null, it means we've reached the end of the current level.
					if (temp == null) {
						// Add the current level's values to the result list.
						ans.add(new ArrayList<>(tempAns));

						// Clear the temporary list to start collecting the next level's values.
						tempAns.clear();

						// If the queue is not empty, add another null marker to separate levels.
						if (!q.isEmpty()) {
							q.add(null);
						}
					} else {
						// Add the current node's value to the temporary list.
						tempAns.add(temp.val);

						// Add the left child of the current node to the queue (if it exists).
						if (temp.left != null) {
							q.add(temp.left);
						}

						// Add the right child of the current node to the queue (if it exists).
						if (temp.right != null) {
							q.add(temp.right);
						}
					}
				}

				// Return the list of levels after the traversal is complete.
				return ans;
			}
		}

	}

