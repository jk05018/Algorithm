package com.example.algorithm.leetcode.contest.d20220115;

public class P6292 {
	public int[][] rangeAddQueries(int n, int[][] queries) {
		int[][] mat = new int[n][n];

		for(int[] query : queries) {
			for(int i = query[0] ; i <= query[2] ; ++i){
				for(int j=query[1]; j <= query[3] ; ++j){
					mat[i][j] += 1;
				}
			}
		}

		return mat;
	}
}
