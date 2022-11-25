package com.example.algorithm.programmers.level2;

public class 전력망을둘로나누기 {
	boolean[][] matrix;
	int N;

	public int solution(int n, int[][] wires) {
		// init Settings
		N = n;
		matrix = makeMatrix(wires);

		int minDiff = Integer.MAX_VALUE;

		// solve
		for (int[] wire : wires) {
			matrix[wire[0]][wire[1]] = matrix[wire[1]][wire[0]] = false;

			int value = dfs(1, new boolean[N + 1]);

			minDiff = Math.min(minDiff, Math.abs(N - 2 * value));

			matrix[wire[0]][wire[1]] = matrix[wire[1]][wire[0]] = true;
		}

		return minDiff;

	}

	public int dfs(int now, boolean[] taken) {
		taken[1] = true;

		int ret = 1;

		for (int i = 1; i <= N; ++i) {
			if (!taken[i] && matrix[now][i]) {
				taken[i] = true;
				ret += dfs(i, taken);
			}
		}

		return ret;
	}

	public boolean[][] makeMatrix(int[][] wires) {
		boolean[][] matrix = new boolean[N + 1][N + 1];

		for (int[] wire : wires) {
			matrix[wire[0]][wire[1]] = matrix[wire[1]][wire[0]] = true;
		}

		return matrix;
	}
}
