package com.example.algorithm.leetcode.tree.unionfind;

import java.util.Arrays;

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

	/*
	solve by using union find - but dfs is more effective
	 */
	static int max2, N, M;

	public int maxAreaOfIsland2(int[][] grid) {
		max = 0;
		N = grid.length;
		M = grid[0].length;
		SizeUnionFind uf = new SizeUnionFind(grid);

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (grid[i][j] == 1) {
					dfs(grid, i, j, uf);
					max2 = Math.max(max2, uf.size[uf.find(i * M + j)]);
				}
			}
		}

		return max2;
	}

	public void dfs(int[][] grid, int x, int y, SizeUnionFind uf) {
		grid[x][y] = 0;

		for (int i = 0; i < 4; ++i) {
			int nextX = x + toX[i];
			int nextY = y + toY[i];

			// check out of range
			if (checkOutOfRange(nextX, nextY) || grid[nextX][nextY] == 0) {
				continue;
			}

			uf.merge(x * M + y, nextX * M + nextY);
			dfs(grid, nextX, nextY, uf);
		}

	}

	public boolean checkOutOfRange(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return true;
		}
		return false;
	}

}

class SizeUnionFind {
	int[] parent, rank, size;

	// init
	SizeUnionFind(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		this.parent = new int[n * m];
		this.rank = new int[n * m];
		this.size = new int[n * m];

		Arrays.fill(rank, 1);
		Arrays.fill(size, 1);

		for (int i = 0; i < n * m; ++i) {
			this.parent[i] = i;
		}
	}

	// find
	int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = find(parent[u]);
	}

	// merge
	void merge(int u, int v) {
		u = find(u);
		v = find(v);

		// if already connected -> return
		if (u == v)
			return;

		// make v always has more rank than u
		if (rank[u] > rank[v]) {
			int temp = u;
			u = v;
			v = temp;
		}

		parent[u] = v;
		if (rank[u] == rank[v])
			++rank[v];
		size[v] += size[u];
	}
}
