package com.example.algorithm.leetcode.tree.unionfind.structure;

import java.util.Arrays;

public class OptimizedDisjointSet {
	int[] parent, rank;

	OptimizedDisjointSet(int n) {
		this.parent = new int[n];
		this.rank = new int[n];

		Arrays.fill(rank, 1);

		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		// u가 속한 트리의 루트의 번호를 반환
		return parent[u] = find(parent[u]);
	}

	void merge(int u, int v) {
		u = find(u);
		v = find(v);

		if (u == v)
			return;
		if (rank[u] > rank[v]) {
			// swap 항상 rank[v] > rank[u] 인 것을 보장하려고
			int temp = u;
			u = v;
			v = temp;
		}

		parent[u] = v;
		if (rank[u] == rank[v])
			++rank[v];

	}
}
