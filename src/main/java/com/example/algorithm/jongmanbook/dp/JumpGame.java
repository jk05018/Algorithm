package com.example.algorithm.jongmanbook.dp;

import static com.example.algorithm.jongmanbook.CacheUtil.*;

import java.util.Scanner;

public class JumpGame {
	static int[][] board;
	static int[][] cache;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// basic settings
		n = sc.nextInt();
		board = new int[n][n]; // board init 필요
		cache = initCache(n, n);

		if (jump(0, 0) == 1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	public static int jump(int x, int y) {
		if (x >= n || y >= n)
			return 0;
		if (x == n - 1 && y == n - 1)
			return 1;
		if (cache[x][y] != -1) {
			return cache[x][y];
		}

		int next = board[x][y];
		if ((jump(x + next, y) == 1 || jump(x, y + next) == 1)) {
			cache[x][y] = 1;
		} else {
			cache[x][y] = 0;
		}

		return cache[x][y];
	}
}
