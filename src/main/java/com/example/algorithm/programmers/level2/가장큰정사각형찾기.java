package com.example.algorithm.programmers.level2;

import java.util.Arrays;

public class 가장큰정사각형찾기 {
	int[][] cache, board;
	int max = Integer.MIN_VALUE;

	public int solution(int[][] board) {
		this.cache = initArray(board);
		this.board = board;

		solve(0, 0);

		return max * max;
	}

	int[][] initArray(int[][] board) {
		int[][] cache = new int[board.length][board[0].length];

		for (int[] arr : cache) {
			Arrays.fill(arr, -1);
		}

		return cache;
	}

	int solve(int x, int y) {
		// check out of range
		if (x >= board.length || y >= board[0].length) {
			return 0;
		}

		// check cache
		if (cache[x][y] != -1) {
			return cache[x][y];
		}

		// solve
		int value = Math.min(Math.min(solve(x + 1, y), solve(x, y + 1)), solve(x + 1, y + 1)) + 1;

		if (board[x][y] == 0) {
			value = 0;
		}

		max = Math.max(max, value);

		return cache[x][y] = value;
	}
}
