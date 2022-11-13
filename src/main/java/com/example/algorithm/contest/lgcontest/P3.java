package com.example.algorithm.contest.lgcontest;

import java.util.Arrays;

public class P3 {
	int N;
	int[] cache;

	public int solution(String reference, String track) {
		// init Settings
		N = track.length();
		cache = new int[N];
		Arrays.fill(cache, -1);

		return jump(0, reference, track);
	}

	public int jump(int index, String reference, String track) {
		// base case
		if (index == N) {
			return Integer.MAX_VALUE;
		}

		// check cache
		if (cache[index] != -1) {
			return cache[index];
		}

		int ret = Integer.MIN_VALUE;

		for (int plus = 1; index + plus <= N; ++plus) {
			String part = track.substring(index, index + plus);

			if (reference.contains(part)) {
				int temp = Math.min(plus, jump(index + plus, reference, track));
				ret = Math.max(ret, temp);
			}
		}

		return cache[index] = ret;
	}
}
