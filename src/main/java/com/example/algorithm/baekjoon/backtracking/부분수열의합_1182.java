package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 부분수열의합_1182 {
	static int N, S, count;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		S = Integer.parseInt(input[1]);

		count = 0;
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		solve(0, -1, new ArrayList<Integer>());
		System.out.println(count);
	}

	public static void solve(int sum, int index, List<Integer> answer) {
		if (sum == S && index != -1) {
			++count;
		}

		for (int i = index + 1; i < N; ++i) {
			solve(sum + nums[i], i, answer);
		}

	}
}
