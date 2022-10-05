package com.example.algorithm.leetcode.dp;

import java.util.*;

public class HouseRobber_198 {
	static int N;
	static int[] cache;

	public int rob(int[] nums) {
		// init Cache
		N = nums.length;
		cache = new int[N];
		Arrays.fill(cache, -1);

		int ret = -1;

		for (int i = 0; i < N; ++i) {
			ret = Math.max(ret, solve(nums, i));
		}

		return ret;

	}

	private int solve(int[] nums, int locate) {
		// base case
		if (locate >= N) {
			return 0;
		}

		// init Cache
		if (cache[locate] != -1) {
			return cache[locate];
		}

		// solve
		return cache[locate] = Math.max(solve(nums, locate + 1), nums[locate] + solve(nums, locate + 2));
	}
}
