package com.example.algorithm.leetcode.tree.unionfind;

public class NumberOfEnclaves_1020 {

	/*
	My Solution but ac
	i need to think creatively
	 */
	int[] toX = {0, 1, 0, -1};
	int[] toY = {1, 0, -1, 0};
	int count, N, M;

	public int numEnclaves(int[][] grid) {
		N = grid.length;
		M = grid[0].length;
		count = 0;

		int c = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (grid[i][j] == 1) {
					++c;
					int[] result = dfs(grid, i, j);
					if (result[1] == -1) {
						count += result[0];
					}
				}
			}
		}

		return count;
	}

	public int[] dfs(int[][] grid, int x, int y) {
		// out of range but can walkout
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return new int[] {0, 1};
		}

		if (grid[x][y] == 0) {
			return new int[] {0, -1};
		}

		grid[x][y] = 0;

		int count = 1;
		int flag = -1;

		for (int i = 0; i < 4; ++i) {
			int nextX = x + toX[i];
			int nextY = y + toY[i];

			int[] result = dfs(grid, nextX, nextY);
			count += result[0];
			if (result[1] == 1) {
				flag = 1;
			}
		}

		return new int[] {count, flag};
	}

	// another solve - this is brilliant idea
	public int numEnclaves2(int[][] grid) {
		N = grid.length;
		M = grid[0].length;
		count = 0;

		for(int i=0; i<N; ++i){
			for(int j=0; j<M; ++j){
				if((i == 0 || j == 0 || i == N-1 || j == M - 1) && grid[i][j] == 1){
					dfs2(grid, i, j);
				}
			}
		}

		for(int i=0; i<N; ++i){
			for(int j=0; j<M; ++j){
				if(grid[i][j] == 1){
					++count;
				}
			}
		}


		return count;
	}


	public void dfs2(int[][] grid, int x, int y){
		if(x < 0 || y < 0 || x>= N || y >= M || grid[x][y] == 0){
			return;
		}

		grid[x][y] = 0;

		for(int i=0; i<4; ++i){
			int nextX = x + toX[i];
			int nextY = y + toY[i];

			dfs(grid, nextX, nextY);
		}

	}
}
