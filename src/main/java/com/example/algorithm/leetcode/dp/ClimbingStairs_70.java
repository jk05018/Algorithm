package com.example.algorithm.leetcode.dp;

import java.util.*;

public class ClimbingStairs_70 {
	static int[] cache;

	public int climbStairs(int n) {
		// init Cache
		cache = new int[n + 1];
		Arrays.fill(cache, -1);

		return step(n);
	}

	private int step(int n) {
		// base case
		if (n <= 2) {
			return n;
		}

		// check Cache
		if (cache[n] != -1) {
			return cache[n];
		}

		// solve
		return cache[n] = step(n - 1) + step(n - 2);
	}
}
