package com.example.algorithm.leetcode.problems.dp;

import java.util.*;

public class MinCostClimbingStairs_746 {
	static final int INF = 987_654_321;
	static int N;
	static int[] cache;

	public int minCostClimbingStairs(int[] cost) {
		// init Cache
		N = cost.length;
		cache = new int[N];
		Arrays.fill(cache, -1);

		return Math.min(climb(cost, 0), climb(cost, 1));
	}

	private int climb(int[] cost, int start) {
		// base case
		if (start == N)
			return 0;

		// check Cache
		if (cache[start] != -1) {
			return cache[start];
		}

		// solve
		int ret = INF;

		for (int next = 1; next <= 2; ++next) {
			if (start + next <= N) {
				ret = Math.min(ret, cost[start] + climb(cost, start + next));
			}
		}

		return cache[start] = ret;
	}
}
