package com.example.algorithm.leetcode.problems.dp;

import java.util.*;

public class JumpGame2_45 {
	static int[] cache;
	static int N;
	static int INF = 987_654_321;

	public int jump(int[] nums) {
		// init Settings
		N = nums.length;
		cache = new int[N];

		Arrays.fill(cache, -1);

		// solve
		return jump(nums, 0);
	}

	int jump(int[] nums, int index) {
		// base case
		if (index == N - 1) {
			return 0;
		}

		// check cache
		if (cache[index] != -1) {
			return cache[index];
		}

		// solve
		int ret = INF;

		for (int add = 1; add <= nums[index]; ++add) {
			if (nums[index] == 0) {
				continue;
			}

			if (index + add < N) {
				ret = Math.min(ret, 1 + jump(nums, index + add));
			}

		}

		return cache[index] = ret;
	}
}
