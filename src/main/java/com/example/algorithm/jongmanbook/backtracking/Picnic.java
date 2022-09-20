package com.example.algorithm.jongmanbook.backtracking;

import java.util.Scanner;

public class Picnic {

	static boolean[][] areFriends;
	static int n;

	public static void main(String[] args) {
		// init settings
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int i = 0; i < C; ++i) {
			n = sc.nextInt();
			areFriends = initFriends(sc, n, sc.nextInt());

			System.out.println(countingPairs(new boolean[n]));
		}

	}

	private static int countingPairs(boolean[] taken) {
		int first = -1;
		for (int i = 0; i < n; ++i) {
			if (!taken[i]) {
				first = i;
				break;
			}
		}

		if (first == -1) {
			return 1;
		}

		int ret = 0;
		for (int next = first + 1; next < n; ++next) {
			if (!taken[next] && areFriends[first][next]) {
				taken[first] = taken[next] = true;
				ret += countingPairs(taken);
				taken[first] = taken[next] = false;
			}
		}

		return ret;
	}

	private static boolean[][] initFriends(Scanner sc, int n, int m) {
		boolean[][] matrix = new boolean[n][n];

		for (int i = 0; i < m; ++i) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			matrix[x][y] = true;
			matrix[y][x] = true;
		}

		return matrix;
	}
}
