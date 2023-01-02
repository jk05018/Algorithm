package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class 좋은수열_2661 {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		find(new ArrayList<Integer>());
	}

	public static boolean find(List<Integer> answer) {
		// base case
		if (answer.size() == N) {
			System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining()));
			return true;
		}

		for (int i = 1; i <= 3; ++i) {
			answer.add(i);
			if (isGood(answer)) {
				if (find(answer)) {
					return true;
				}
			}
			answer.remove(answer.size() - 1);
		}

		return false;
	}

	public static boolean isGood(List<Integer> answer) {
		if (answer.size() == 1) {
			return true;
		}

		for (int len = 1; len <= answer.size() / 2; ++len) {
			boolean flag = true;
			for (int k = 1; k <= len; ++k) {
				if (answer.get(answer.size() - k) != answer.get(answer.size() - len - k)) {
					flag = false;
				}
			}

			if (flag)
				return false;
		}

		return true;
	}
}
