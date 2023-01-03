package com.example.algorithm.leetcode.problems.dp;

import java.util.*;

public class TribonacciNumber_1137 {
	static int[] cache;

	public int tribonacci(int n) {
		// init cache
		cache = new int[n+1];
		Arrays.fill(cache,-1);

		return solve(n);
	}

	private int solve(int n){
		if(n == 0) return 0;
		if(n == 1 || n == 2) return 1;
		if(cache[n] != -1) return cache[n];

		return cache[n] = solve(n-1) + solve(n-2) + solve(n-3);
	}
}
