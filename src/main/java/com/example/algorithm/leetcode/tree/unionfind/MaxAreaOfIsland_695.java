package com.example.algorithm.leetcode.tree.unionfind;

public class MaxAreaOfIsland_695 {
	// solve by using DFS
	int max;
	int[] toX = {0, 1, 0, -1};
	int[] toY = {1, 0, -1, 0};

	public int maxAreaOfIsland(int[][] grid) {
		max = 0;

		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				if (grid[i][j] == 1) {
					max = Math.max(max, travel(grid, i, j));
				}
			}
		}

		return max;
	}

	public int travel(int[][] grid, int x, int y) {
		// check out of range
		if (x < 0 || y < 0 || x >= grid.length ||
			y >= grid[0].length || grid[x][y] == 0) {
			return 0;
		}

		grid[x][y] = 0;
		int ret = 1;

		for (int i = 0; i < 4; ++i) {
			ret += travel(grid, x + toX[i], y + toY[i]);
		}

		return ret;

	}
}
