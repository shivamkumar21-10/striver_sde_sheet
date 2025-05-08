package com.stack;
import java.util.*;
public class Leetcode146_LRUCache {
	
	class LRUCache {
	    /**
	     * Doubly Linked List Node to store key-value pairs
	     */
	    class Node {
	        Node prev, next;
	        int key, value;
	        
	        Node(int _key, int _value) {
	            key = _key;
	            value = _value;
	        }
	    }

	    Map<Integer, Node> mapp = new HashMap<>(); // HashMap for O(1) access to nodes
	    int capacity; // Capacity of the cache
	    Node head = new Node(0, 0); // Dummy head node
	    Node tail = new Node(0, 0); // Dummy tail node

	    /**
	     * Initializes the LRUCache with a given capacity.
	     * The cache uses a HashMap and a Doubly Linked List to manage the LRU order.
	     */
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        head.next = tail;
	        tail.prev = head;
	    }
	    
	    /**
	     * Retrieves the value of the key if it exists in the cache.
	     * If the key exists, it is moved to the front (most recently used position).
	     */
	    public int get(int key) {
	        if (!mapp.containsKey(key)) return -1; // Key not present

	        Node node = mapp.get(key);
	        delete(node); // Remove the node from its current position
	        insert(node); // Reinsert at the front (most recently used)
	        return node.value;
	    }
	    
	    /**
	     * Inserts a key-value pair into the cache.
	     * If the key already exists, it updates the value and moves it to the front.
	     * If the cache is at capacity, it removes the least recently used item.
	     */
	    public void put(int key, int value) {
	        if (mapp.containsKey(key)) {
	            delete(mapp.get(key)); // Remove existing node if present
	        }
	        if (mapp.size() == capacity) {
	            delete(tail.prev); // Remove least recently used node (node before tail)
	        }
	        insert(new Node(key, value)); // Insert the new node at the front
	    }

	    /**
	     * Deletes a node from the doubly linked list and removes it from the HashMap.
	     * Used when an item is accessed (to reposition it) or when an item is evicted.
	     */
	    private void delete(Node node) {
	        mapp.remove(node.key);
	        node.prev.next = node.next;
	        node.next.prev = node.prev;
	    }

	    /**
	     * Inserts a node at the front (most recently used position) of the doubly linked list.
	     */
	    private void insert(Node node) {
	        mapp.put(node.key, node);
	        node.next = head.next;
	        node.next.prev = node;
	        head.next = node;
	        node.prev = head;
	    }
	}

	/**
	 * Dry Run:
	 * Operations:
	 * LRUCache obj = new LRUCache(2); // Cache capacity = 2
	 * obj.put(1, 1);  // Cache: {1=1}
	 * obj.put(2, 2);  // Cache: {1=1, 2=2}
	 * obj.get(1);     // Returns 1, Cache: {2=2, 1=1} (1 moved to front)
	 * obj.put(3, 3);  // Evicts key 2, Cache: {1=1, 3=3}
	 * obj.get(2);     // Returns -1 (not found)
	 * obj.put(4, 4);  // Evicts key 1, Cache: {3=3, 4=4}
	 * obj.get(1);     // Returns -1 (not found)
	 * obj.get(3);     // Returns 3, Cache: {4=4, 3=3} (3 moved to front)
	 * obj.get(4);     // Returns 4, Cache: {3=3, 4=4} (4 moved to front)
	 */


}
