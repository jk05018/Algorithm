package com.example.algorithm.jongmanbook.dp;

import java.util.Arrays;
import java.util.Scanner;

public class TrianglePath {
	static int n;
	static int[][] board, cache;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			// init settings
			n = sc.nextInt();
			board = new int[n][n];
			cache = initCache(n, n);
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j <= i; ++j) {
					board[i][j] = sc.nextInt();
				}
			}

			// solve
			System.out.println(path(0, 0));
		}
	}

	private static int path(int x, int y) {
		if (x == n - 1)
			return board[x][y];
		if (cache[x][y] != -1)
			return cache[x][y];

		return cache[x][y] = Math.max(path(x + 1, y), path(x + 1, y + 1)) + board[x][y];
	}

	public static int[][] initCache(int n, int m) {
		int[][] cache = new int[n][m];

		for (int[] arr : cache) {
			Arrays.fill(arr, -1);
		}

		return cache;
	}
}
