package com.Array1;

public class FindNCR {
	
	static int ncr(int n, int r) {
		int res = 1;
		for(int i=0; i<r; i++) {
			res = res*(n-i);
			res = res/(i+1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(ncr(5,3));
		
	}

}
