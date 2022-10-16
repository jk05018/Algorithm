package com.example.algorithm.leetcode.dp;

import java.util.*;

public class CountSortedVowelStrings_1641 {
	static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	static int[][] cache;

	public int countVowelStrings(int n) {
		// init Cache
		cache = new int[n + 1][5];
		for (int[] arr : cache) {
			Arrays.fill(arr, -1);
		}

		return count(n, 0);
	}

	public int count(int remain, int index) {
		// base case
		if (remain == 0)
			return 1;

		// check Cache
		if (cache[remain][index] != -1)
			return cache[remain][index];
		// solve
		int ret = 0;
		for (int i = index; i < vowels.length; ++i) {
			ret += count(remain - 1, i);
		}

		return cache[remain][index] = ret;
	}
}
