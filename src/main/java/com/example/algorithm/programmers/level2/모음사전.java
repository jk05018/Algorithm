package com.example.algorithm.programmers.level2;

public class 모음사전 {
	char[] alphas = {'A', 'E', 'I', 'O', 'U'};
	int count = 0;

	public int solution(String word) {
		dfs(word, "");
		return count;
	}

	public boolean dfs(String word, String answer) {
		// base case
		if (word.equals(answer)) {
			return true;
		}

		if (answer.length() == 5) {
			return false;
		}

		for (char alpha : alphas) {
			count += 1;
			if (dfs(word, answer + alpha)) {
				return true;
			}
		}

		return false;
	}
}
