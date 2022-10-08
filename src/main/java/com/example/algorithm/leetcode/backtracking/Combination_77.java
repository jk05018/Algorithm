package com.example.algorithm.leetcode.backtracking;

import java.util.*;

public class Combination_77 {
	static List<List<Integer>> answer;
	static int N, K;

	public List<List<Integer>> combine(int n, int k) {
		// init Settings
		answer = new ArrayList<>();
		N = n;
		K = k;

		pick(new ArrayList<>(), 0);

		return answer;
	}

	public void pick(List<Integer> picked, int last) {
		// base case
		if (picked.size() == K) {
			answer.add(new ArrayList<Integer>(picked));
			return;
		}

		// solve
		for (int next = last + 1; next <= N; ++next) {
			picked.add(next);
			pick(picked, next);
			picked.remove(picked.size() - 1);
		}
	}
}
