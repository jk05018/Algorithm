package com.example.algorithm.leetcode.backtracking;

import java.util.*;

public class Permutations_46 {
	static List<List<Integer>> answer;

	public List<List<Integer>> permute(int[] nums) {
		answer = new ArrayList<>();
		int len = nums.length;
		solve(nums, new boolean[len], len, new ArrayList<Integer>());

		return answer;
	}

	private void solve(int[] nums, boolean[] taken, int toTake, List<Integer> ans) {
		if (toTake == 0) {
			answer.add(copyList(ans));
			return;
		}

		for (int i = 0; i < nums.length; ++i) {
			if (!taken[i]) {
				// insert to ans
				taken[i] = true;
				ans.add(nums[i]);

				solve(nums, taken, toTake - 1, ans);

				taken[i] = false;
				ans.remove(ans.size() - 1);
			}
		}
	}

	private List<Integer> copyList(List<Integer> list) {
		List<Integer> toList = new ArrayList<>();

		for (int i : list) {
			toList.add(i);
		}

		return toList;
	}
}
