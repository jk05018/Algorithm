package com.example.algorithm.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {
	static List<String> answer;

	public List<String> generateParenthesis(int n) {
		answer = new ArrayList<>();
		solve(0, 0, "", n);

		return answer;
	}

	public void solve(int left, int right, String ans, int n) {
		if (left < right || left > n)
			return;
		if (left == n && right == n) {
			answer.add(ans);
			return;
		}

		solve(left + 1, right, ans + "(", n);
		solve(left, right + 1, ans + ")", n);
	}
}
