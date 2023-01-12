package com.example.algorithm.leetcode.todaychallenge;

import java.util.ArrayList;

public class NumberofNodesintheSubTreeWiththeSameLabel_1519 {
	int N;
	ArrayList<Integer>[] trees;

	public int[] countSubTrees(int n, int[][] edges, String labels) {
		// make tree
		N = n;
		trees = new ArrayList[N];

		for (int i = 0; i < N; ++i) {
			trees[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			trees[edge[0]].add(edge[1]);
			trees[edge[1]].add(edge[0]);
		}

		int[] answer = new int[N];
		boolean[] visited = new boolean[N];
		visited[0] = true;
		count(0, answer, 26, visited, labels);

		return answer;
	}

	public int[] count(int index, int[] answer, int n, boolean[] taken, String labels) {
		int[] cnt = new int[n];

		// traversal
		for (int child : trees[index]) {
			if (!taken[child]) {
				taken[child] = true;
				int[] result = count(child, answer, n, taken, labels);
				for (int i = 0; i < n; ++i) {
					cnt[i] += result[i];
				}
			}
		}

		// solve
		int value = labels.charAt(index) - 'a';
		cnt[value] += 1;
		answer[index] = cnt[value];

		return cnt;
	}
}
