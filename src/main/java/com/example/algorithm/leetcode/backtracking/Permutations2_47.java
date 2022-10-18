package com.example.algorithm.leetcode.backtracking;

import java.util.*;

/*
Time Complexity : O(N * N!) because answer.add(copyList(ans) is O(N)

Permutation1보다 살찍 줄었을 것임
 */
public class Permutations2_47 {
	static List<List<Integer>> answer;

	public List<List<Integer>> permuteUnique(int[] nums) {
		answer = new ArrayList<>();
		int len = nums.length;
		Arrays.sort(nums);

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
				if (i > 0 && !taken[i - 1] && nums[i] == nums[i - 1])
					continue;
				// insert part
				taken[i] = true;
				ans.add(nums[i]);

				solve(nums, taken, toTake - 1, ans);

				// remove part
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
