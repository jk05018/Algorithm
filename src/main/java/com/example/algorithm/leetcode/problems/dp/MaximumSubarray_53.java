package com.example.algorithm.leetcode.problems.dp;

import java.util.*;

/*
	두 풀이 모두 같은 아이디어를 착안하고 있음.
	계속 더해가다 음수가 되면 버린다!
 */
public class MaximumSubarray_53 {
	static int[] cache;
	static int N;

	/*
	Algoritm with memoization
	 */
	public int maxSubArray(int[] nums) {
		// init Settings
		N = nums.length;
		cache = new int[N];

		Arrays.fill(cache, -1);

		// solve
		int max = -99999;

		for(int i=0; i<N ; ++i){
			max = Math.max(max, solve(nums, i));
		}

		return max;
	}

	public int solve(int[] nums, int index){
		// base case
		if(index == N-1){
			return nums[index];
		}

		// check Cache
		if(cache[index] != -1){
			return cache[index];
		}

		// solve
		return cache[index] = Math.max(nums[index], nums[index] + solve(nums, index + 1));
	}


	/**
	 * Kadane's Algorithm O(N)
	 **/

	public int maxSubArray_kadane(int[] nums) {
		int sum = 0, max = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; ++i) {
			sum += nums[i];
			max = Math.max(max, sum);

			if (sum < 0)
				sum = 0;
		}

		return max;
	}
}
