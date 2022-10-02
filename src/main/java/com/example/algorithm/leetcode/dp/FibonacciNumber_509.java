package com.example.algorithm.leetcode.dp;

import java.util.*;

public class FibonacciNumber_509 {
	static int[] cache;

	public int fib(int n) {
		// init cache
		cache = new int[n+1];
		Arrays.fill(cache,-1);

		return solve(n);
	}

	private int solve(int n){
		if(n <= 1) return n;
		if(cache[n] != -1) return cache[n];
		return cache[n] = solve(n-1) + solve(n-2);
	}
}
