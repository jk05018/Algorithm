package com.example.algorithm.leetcode.problems.tree.unionfind;

import java.util.*;

/*
Time Complexity: O(N)?
Spacee Complexity : O(N)?
 */
public class FindIfPathExistsInGraph_1971 {
	public boolean validPath(int n, int[][] edges, int source, int destination) {
		UnionFind uf = new UnionFind(n);

		for (int[] arr : edges) {
			uf.merge(arr[0], arr[1]);
		}

		return uf.find(source) == uf.find(destination);

	}

}

class UnionFind {
	int[] parent, rank;

	// init
	UnionFind(int n) {
		this.parent = new int[n];
		this.rank = new int[n];

		Arrays.fill(rank, 1);

		for (int i = 0; i < n; ++i) {
			parent[i] = i;
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

		// return if u and v is already connected
		if (u == v)
			return;

		// make v has higher rank
		if (rank[u] > rank[v]) {
			int temp = u;
			u = v;
			v = temp;
		}

		parent[u] = v;
		if (rank[u] == rank[v]) {
			++rank[v];
		}
	}
}
