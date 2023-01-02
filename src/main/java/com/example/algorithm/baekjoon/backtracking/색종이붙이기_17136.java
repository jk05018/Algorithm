package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {
	static int N, minCount;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 10;
		minCount = Integer.MAX_VALUE;

		int[][] matrix = new int[N][N];

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(matrix, 0, new int[] {0, 5, 5, 5, 5, 5});

		if (minCount == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(minCount);
		}

	}

	public static boolean place(int[][] matrix, int x, int y, int count, int type) {
		boolean flag = true;

		for (int i = 0; i < count; ++i) {
			for (int j = 0; j < count; ++j) {
				int nextX = x + i;
				int nextY = y + j;

				// check out of range
				if (nextX >= N || nextY >= N) {
					flag = false;
					continue;
				}

				if (matrix[nextX][nextY] < 1) {
					flag = false;
				}

				matrix[nextX][nextY] += type;
			}
		}

		return flag;
	}

	public static void solve(int[][] matrix, int count, int[] remains) {
		// base case 1 : if count is over minCount
		if (count >= minCount) {
			return;
		}

		int x = -1;
		int y = -1;

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (matrix[i][j] == 1) {
					x = i;
					y = j;
					break;
				}
			}
			if (y != -1) {
				break;
			}
		}

		// base case 2 : Completed
		if (y == -1) {
			minCount = Math.min(minCount, count);
			return;
		}

		for (int i = 5; i >= 1; --i) {
			if (place(matrix, x, y, i, -1) && remains[i] >= 1) {
				remains[i] -= 1;
				solve(matrix, count + 1, remains);
				remains[i] += 1;
			}

			place(matrix, x, y, i, 1);
		}

	}
}
