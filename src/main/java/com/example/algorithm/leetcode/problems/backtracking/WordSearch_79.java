package com.example.algorithm.leetcode.problems.backtracking;

public class WordSearch_79 {
	static int[] toX = {0, 1, 0, -1};
	static int[] toY = {1, 0, -1, 0};

	public boolean exist(char[][] board, String word) {

		boolean flag = false;

		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				if (solve(board, new boolean[board.length][board[0].length], i, j, word)) {
					flag = true;
					break;
				}
			}

			if (flag) {
				break;
			}
		}

		return flag;
	}

	public boolean solve(char[][] board, boolean[][] taken, int x, int y, String part) {
		// base case
		// case 1: if already taken == false
		if (taken[x][y] || part.charAt(0) != board[x][y]) {
			return false;
		}

		// case 2 : part ramains one
		if (part.length() == 1) {
			return true;
		}

		for (int i = 0; i < 4; ++i) {
			int nextX = x + toX[i];
			int nextY = y + toY[i];

			// check out of range
			if (nextX < 0 || nextY < 0 || nextX >= board.length || nextY >= board[0].length) {
				continue;
			}

			taken[x][y] = true;
			if (solve(board, taken, nextX, nextY, part.substring(1))) {
				return true;
			}
			taken[x][y] = false;
		}

		return false;
	}
}
