package com.example.algorithm.leetcode.problems.tree.unionfind.structure;

import java.util.*;

public class NaiveDisjointSet {
	int[] parent;

	NaiveDisjointSet(int n){
		this.parent = new int[n];

		for(int i=0; i <n; ++i){
			parent[i] = i;
		}
	}

	// u가 속한 트리의 루트의 번호를 반환한다.
	int find(int u){
		if(u == parent[u]){
			return u;
		}
		return find(parent[u]);
	}

	// u가 속한 트리와 v가 속한 트리를 합한다.
	void merge(int u, int v){
		u = find(u);
		v = find(v);

		if(u == v)
			return;

		parent[u] = v;
	}


}
