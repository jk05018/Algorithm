package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

public class CombinationSum_39 {
	static List<List<Integer>> answer;

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		answer = new ArrayList<List<Integer>>();
		solve(candidates, target, new ArrayList<Integer>(), 0, 0);
		return answer;
	}

	public void solve(int[] cand, int target, List<Integer> ans, int index, int sum) {
		// base case 1 : 실패
		if (target < sum)
			return;
		// base case 2 : 성공
		if (target == sum) {
			answer.add(copyList(ans));
			return;
		}

		for (int i = index; i < cand.length; ++i) {
			ans.add(cand[i]);
			solve(cand, target, ans, i, sum + cand[i]);
			ans.remove(ans.size() - 1);
		}
	}

	public List<Integer> copyList(List<Integer> input) {
		List<Integer> list = new ArrayList<>();
		for (int i : input) {
			list.add(i);
		}
		return list;
	}
}
