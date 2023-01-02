package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class NM9_15663 {
	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		nums = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		solve(new boolean[N], new ArrayList<Integer>(), 0);

	}

	public static void solve(boolean[] taken, List<Integer> answer, int count) {
		// base case
		if (count == M) {
			System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
			return;
		}

		for (int i = 0; i < N; ++i) {
			if (!taken[i]) {
				if (i > 0 && !taken[i - 1] && nums[i] == nums[i - 1]) {
					continue;
				}

				taken[i] = true;
				answer.add(nums[i]);

				solve(taken, answer, count + 1);

				taken[i] = false;
				answer.remove(answer.size() - 1);
			}
		}
	}
}
