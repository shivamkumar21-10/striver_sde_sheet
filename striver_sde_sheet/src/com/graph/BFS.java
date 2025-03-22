package com.graph;

import java.util.*;

public class BFS {
	
	static void BFStraversal(int source, Map<Integer, List<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> isVisited = new HashSet<>();
		
		q.add(source);
		isVisited.add(source);
		
		while(!q.isEmpty()) {
			int front = q.poll();
			System.out.print(front+ " ");
			for(int nbr: graph.get(front)) {
				if(!isVisited.contains(nbr)) {
					isVisited.add(nbr);
					q.add(nbr);
				}
			}
		}
		
		
	}

	public static void main(String[] args) {
		List<List<Integer>> edgeList = new ArrayList<>();
		edgeList.add(Arrays.asList(0,1));
		edgeList.add(Arrays.asList(1,4));
		edgeList.add(Arrays.asList(1,2));
		edgeList.add(Arrays.asList(2,3));
		
		Map<Integer, List<Integer>> graph = new HashMap<>();
		
		for(int i=0; i<edgeList.size(); i++) {
			int a = edgeList.get(i).get(0);
			int b = edgeList.get(i).get(1);
			
			graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
			graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
		}
		int source = 0;
		BFStraversal(source,graph);

	}

}
