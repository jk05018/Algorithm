package com.example.algorithm.jongmanbook.backtracking;

import java.util.Scanner;

public class BoardCover {
	static int[][][] coverType = {
		{{0, 0}, {1, 0}, {0, -1}},
		{{0, 0}, {1,0}, {1, -1}},
		{{0, 0}, {0,-1}, {1, -1}},
		{{0, 0}, {0,-1}, {-1, -1}}
	};

	static int H, W;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int c = 0; c < C; ++c) {
			int[][] board = initBoard(sc);

			System.out.println(cover(board));
		}
	}

	private static int cover(int[][] board) {
		int x = -1;
		int y = -1;
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
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

		if (y == -1) {
			System.out.println("GOOD!");
			return 1;
		}

		int ret = 0;
		for (int type = 0; type < 4; ++type) {
			if (set(board, x, y, type, 1)) {
				ret += cover(board);
			}
			set(board, x, y, type, -1);
		}

		return ret;
	}

	private static boolean set(int[][] board, int x, int y, int type, int delta) {
		boolean flag = true;

		for (int i = 0; i < 3; ++i) {
			int toX = coverType[type][i][0];
			int toY = coverType[type][i][1];

			if (toX < 0 || toY < 0 || toX >= H || toY >= W){
				flag = false;
				continue;
			}
			if ((board[toX][toY] += delta) > 1) {
				flag = false;
			}
		}

		return flag;
	}

	private static int[][] initBoard(Scanner sc) {
		H = sc.nextInt();
		W = sc.nextInt();

		int[][] board = new int[H][W];

		for (int i = 0; i < H; ++i) {
			String input = sc.next();
			for (int j = 0; j < W; ++j) {
				if (input.charAt(j) == '#') {
					board[i][j] = 1;
				}
			}
		}

		return board;
	}
}
