package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class 스도쿠_2580 {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 9;

		int[][] board = new int[N][N];

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(board);

	}

	public static boolean solve(int[][] board) {
		int x = -1;
		int y = -1;

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (board[i][j] == 0) {
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
		if (y == -1) {
			printAnswer(board);
			return true;
		}

		for (int i = 1; i <= 9; ++i) {
			board[x][y] = i;
			if (isOk(board, x, y)) {
				if(solve(board)){
					return true;
				}
			}
			board[x][y] = 0;
		}

		return false;

	}

	public static boolean isOk(int[][] board, int x, int y) {
		boolean[] taken = new boolean[N + 1];

		// check row
		for (int i = 0; i < N; ++i) {
			if (board[i][y] != 0 && taken[board[i][y]]) {
				return false;
			}
			taken[board[i][y]] = true;
		}

		// check col
		Arrays.fill(taken, false);

		for (int j = 0; j < N; ++j) {
			if (board[x][j] != 0 && taken[board[x][j]]) {
				return false;
			}
			taken[board[x][j]] = true;
		}

		// check box
		Arrays.fill(taken, false);

		int numX = 3 * (x / 3);
		int numY = 3 * (y / 3);

		for (int i = numX; i < numX + 3; ++i) {
			for (int j = numY; j < numY + 3; ++j) {
				if (board[i][j] != 0 && taken[board[i][j]]) {
					return false;
				}
				taken[board[i][j]] = true;
			}
		}

		return true;
	}

	public static void printAnswer(int[][] board) {
		for (int i = 0; i < N; ++i) {
			System.out.println(Arrays.stream(board[i]).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
		}
	}
}
