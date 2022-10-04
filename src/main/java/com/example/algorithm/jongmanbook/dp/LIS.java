package com.example.algorithm.jongmanbook.dp;

import java.util.Scanner;

import com.example.algorithm.jongmanbook.CacheUtil;

public class LIS {
	static int n;
	static int[] cache, nums;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			// init Settings
			n = sc.nextInt();
			nums = new int[n + 1];
			cache = CacheUtil.initCache(n + 1);

			for (int i = 0; i < n; ++i) {
				nums[i] = sc.nextInt();
			}

			// solve
			int ret = -1;
			for (int i = 0; i < n; ++i) {
				ret = Math.max(ret, lis(i));
			}
			System.out.println(ret);
		}
	}

	private static int lis(int start) {
		if (start > n)
			return 0;

		// check Cache
		if (cache[start] != -1)
			return cache[start];

		// calculate
		int ret = 1;
		for (int next = start + 1; next < n; ++next) {
			if (nums[start] < nums[next]) {
				ret = Math.max(ret, 1 + lis(next));
			}
		}

		return cache[start] = ret;

	}
}
