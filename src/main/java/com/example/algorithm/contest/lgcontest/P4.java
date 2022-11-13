package com.example.algorithm.contest.lgcontest;

public class P4 {
	int N;
	boolean[] reversed;
	int[] count;

	public int[] solutiuon(int[][] edges, int[] roots) {
		// init Settings
		N = edges.length;
		reversed = new boolean[N];
		count = new int[N];

		for (int root : roots) {
			check(edges, root);
		}

		return count;
	}

	public void check(int[][] edges, int root) {
		for (int i = 0; i < N; ++i) {
			int left = reversed[i] ? edges[i][1] : edges[i][0];
			int right = reversed[i] ? edges[i][0] : edges[i][1];

			if (right == root) {
				check(edges, left);

				if (reversed[i]) {
					reversed[i] = false;
				} else {
					reversed[i] = true;
				}

				++count[i];

			}
		}
	}
}
