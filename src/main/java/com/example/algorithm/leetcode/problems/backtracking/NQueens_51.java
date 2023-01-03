package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

public class NQueens_51 {
	static List<List<String>> answer;
	static int N;

	public List<List<String>> solveNQueens(int n) {
		// init Settings
		answer = new ArrayList<>();
		N = n;

		// init chess board
		char[][] board = new char[n][n];

		for (char[] arr : board) {
			Arrays.fill(arr, '.');
		}

		solve(board, 0);

		return answer;
	}

	void solve(char[][] board, int row) {
		if (row == N) {
			answer.add(arrToList(board));
			return;
		}

		for (int col = 0; col < N; ++col) {
			if (satisfy(board, row, col)) {
				board[row][col] = 'Q';
				solve(board, row + 1);
				board[row][col] = '.';
			}
		}
	}

	/*
	인사이트!
	이렇게 일일히 계산하지 않고 rows, cols배열을 저장해 놓은 것을 추가해 놓음으로써 최적화할 수 있다.
	생각해보면 아래 satisfy를 일일히 계산하지 않아도 된다. -> 추후 해보자 아니다 이따해보자
	 */
	boolean satisfy(char[][] board, int row, int col) {
		// check col
		for (int i = 0; i < N; ++i) {
			if (board[i][col] == 'Q') {
				return false;
			}
		}

		// check diagonal
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < N; ++j) {
				if (board[i][j] == 'Q' && Math.abs(row - i) == Math.abs(col - j)) {
					return false;
				}
			}
		}

		return true;
	}

	List<String> arrToList(char[][] arrs) {
		List<String> list = new ArrayList<>();

		for (char[] arr : arrs) {
			StringBuilder builder = new StringBuilder();
			for (char c : arr) {
				builder.append(c);
			}
			list.add(builder.toString());
		}

		return list;
	}

}
