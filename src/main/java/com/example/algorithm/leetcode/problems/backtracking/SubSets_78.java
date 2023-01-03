package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

/*
Time Complexity : O(N* 2^N)
 */
public class SubSets_78 {
	static List<List<Integer>> answer;
	static int N;

	public List<List<Integer>> subsets(int[] nums) {
		// init Settings
		answer = new ArrayList<>();
		N = nums.length;

		pick(nums, new ArrayList<Integer>(), 0);

		return answer;
	}

	public void pick(int[] nums, List<Integer> picked, int last) {
		answer.add(new ArrayList<Integer>(picked));

		for (int next = last; next < N; ++next) {
			picked.add(nums[next]);
			pick(nums, picked, next + 1);
			picked.remove(picked.size() - 1);
		}
	}
}
