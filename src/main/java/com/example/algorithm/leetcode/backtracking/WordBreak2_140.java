package com.example.algorithm.leetcode.backtracking;

import java.util.*;

public class WordBreak2_140 {
	static List<String> answers;
	static String S;
	static int N;

	public List<String> wordBreak(String s, List<String> wordDict) {
		// init Settings
		answers = new ArrayList<>();
		S = s;
		N = S.length();

		match(wordDict, new ArrayList<String>(), 0);

		return answers;
	}

	public void match(List<String> wordDict, List<String> answer, int index) {
		// base case
		if (index == N) {
			answers.add(makeAnswer(answer));
			return;
		}

		for (int i = 0; i < wordDict.size(); ++i) {
			String word = wordDict.get(i);

			if (index + word.length() <= N && isSame(word, index, index + word.length())) {
				answer.add(word);
				match(wordDict, answer, index + word.length());
				answer.remove(answer.size() - 1);
			}
		}

	}

	public boolean isSame(String word, int start, int end) {
		return word.equals(S.substring(start, end));
	}

	public String makeAnswer(List<String> answer) {
		StringBuilder builder = new StringBuilder(answer.get(0));

		for (int i = 1; i < answer.size(); ++i) {
			builder.append(" ");
			builder.append(answer.get(i));
		}

		return builder.toString();
	}
}
