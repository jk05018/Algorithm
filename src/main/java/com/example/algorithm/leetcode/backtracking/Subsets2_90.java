package com.example.algorithm.leetcode.backtracking;

import java.util.*;

public class Subsets2_90 {
	static List<List<Integer>> answers;
	static int N;

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		// init Settings
		answers = new ArrayList<>();
		N = nums.length;

		Arrays.sort(nums);

		// solve
		solve(nums, new ArrayList<Integer>(), 0);

		return answers;
	}

	public void solve(int[] nums, List<Integer> answer, int index) {
		answers.add(new ArrayList<>(answer));

		for (int i = index; i < N; ++i) {
			if (i > index && nums[i] == nums[i - 1]) {
				continue;
			}

			answer.add(nums[i]);
			solve(nums, answer, i + 1);
			answer.remove(answer.size() - 1);
		}
	}
}
