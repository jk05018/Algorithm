package com.example.algorithm.leetcode.backtracking;

import java.util.*;

public class CombinationSum2_40 {
	static List<List<Integer>> answer;

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		answer = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);

		solve(candidates, target, 0, 0, new ArrayList<Integer>());

		return answer;
	}

	private void solve(int[] cand, int target, int sum, int index, List<Integer> ans) {
		if (target < sum)
			return;
		if (target == sum) {
			answer.add(copyList(ans));
			return;
		}

		for (int i = index; i < cand.length; ++i) {
			if (i > index && cand[i] == cand[i - 1])
				continue;
			ans.add(cand[i]);
			solve(cand, target, sum + cand[i], i + 1, ans);
			ans.remove(ans.size() - 1);
		}
	}

	private List<Integer> copyList(List<Integer> input) {
		List<Integer> list = new ArrayList<>();

		for (int num : input) {
			list.add(num);
		}

		return list;
	}
}
