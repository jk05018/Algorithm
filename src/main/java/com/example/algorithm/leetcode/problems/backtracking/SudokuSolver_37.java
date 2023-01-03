package com.example.algorithm.leetcode.problems.backtracking;

public class SudokuSolver_37 {
	static final int N = 9;
	static char[][] answer;

	public void solveSudoku(char[][] board) {
		fill(board);
	}

	private boolean fill(char[][] board) {
		int x = -1, y = -1;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (board[i][j] == '.') {
					x = i;
					y = j;
					break;
				}
			}

			if (y != -1) {
				break;
			}
		}

		// base case
		if (x == -1 && y == -1) {
			return true;
		}

		// solve
		for (int num = 1; num <= N; ++num) {
			char value = (char)(num + '0');
			if (satisfy(board, x, y, value)) {
				board[x][y] = value;
				if (fill(board)) {
					return true;
				}
				board[x][y] = '.';
			}
		}

		return false;
	}

	// auto boxing 없애니 훨씬 빨라짐
	private boolean satisfy(char[][] board, int x, int y, char num) {
		// case 1 : occur exactly once in each row.
		for (int i = 0; i < N; ++i) {
			if (board[x][i] == num) {
				return false;
			}
		}

		// case 2 : occur exactly oncee in each column.
		for (int i = 0; i < N; ++i) {
			if (board[i][y] == num) {
				return false;
			}
		}

		// case 3 : occur exactly once in each of the 9 sub-boxes of the gird
		int startX = x / 3 * 3;
		int startY = y / 3 * 3;

		for (int i = startX; i < startX + 3; ++i) {
			for (int j = startY; j < startY + 3; ++j) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}

		return true;
	}
}
