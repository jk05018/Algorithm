package com.example.algorithm.leetcode.backtracking;

import java.util.*;

/*
Time Complexity : O(C(9,K)) 0> O(9^K),
space : K
 */
public class CombinationSum3_216 {
	static List<List<Integer>> answers;
	static int N, K;

	public List<List<Integer>> combinationSum3(int k, int n) {
		// init Settings
		answers = new ArrayList<>();
		K = k;
		N = n;

		find(new ArrayList<>(), new boolean[10], 1, 0);

		return answers;
	}

	public void find(List<Integer> answer, boolean[] taken, int index, int sum) {
		// base case
		if (N < sum) {
			return;
		}

		if (sum == N && answer.size() == K) {
			answers.add(new ArrayList<Integer>(answer));
			return;
		}

		// solve
		for (int i = index; i < 10; ++i) {
			if (taken[i])
				continue;

			answer.add(i);
			taken[i] = true;

			find(answer, taken, i + 1, sum + i);

			answer.remove(answer.size() - 1);
			taken[i] = false;

		}
	}
}
