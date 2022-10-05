package com.example.algorithm.jongmanbook.dp;

import java.util.Scanner;

import com.example.algorithm.jongmanbook.CacheUtil;

public class Tiling {
	static final int MOD = 1000000007;
	static int n;
	static int[] cache;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			// init Settings
			n = sc.nextInt();
			cache = CacheUtil.initCache(n + 1);

			// solve
			System.out.println(tile(n));
		}
	}

	private static int tile(int n) {
		// base case
		if (n <= 1)
			return 1;

		// check Cache
		if (cache[n] != -1)
			return cache[n];

		// solve
		return cache[n] = (tile(n - 1) + tile(n - 2)) % MOD;
	}
}
