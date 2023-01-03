package com.example.algorithm.leetcode.problems.dp;

import java.util.*;

public class DeleteAndEarn_740 {
	static int[] cache;
	static int N;

	public int deleteAndEarn_recursive(int[] nums) {
		// init Settings
		N = nums.length;
		cache = new int[N];

		Arrays.sort(nums);
		Arrays.fill(cache, -1);

		// solve
		int ret = -1;

		for (int i = 0; i < N; ++i) {
			ret = Math.max(ret, solve(nums, i));
		}

		return ret;
	}

	int solve(int[] nums, int index) {
		// check Cache
		if (cache[index] != -1) {
			return cache[index];
		}

		// solve
		int ret = nums[index];

		for (int next = index + 1; next < N; ++next) {
			if (nums[next] == nums[index] || nums[next] >= nums[index] + 2) {
				ret = Math.max(ret, nums[index] + solve(nums, next));
			}
		}

		return cache[index] = ret;
	}

	/*
	반복적 해결이 더 간단하다. 하지만 나는 아직 익히지 못했다.
	일단 메오이제이션부터 정복해야 한다.
	 */
	public int deleteAndEarn_iteration(int[] nums) {
		int[] sum = new int[10002];

		for(int i = 0; i < nums.length; i++){
			sum[nums[i]] += nums[i];
		}

		for(int i = 2; i < sum.length; i++){
			sum[i] = Math.max(sum[i-1], sum[i-2] + sum[i]);
		}
		return sum[10001];
	}
}
