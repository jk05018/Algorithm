package com.example.algorithm.leetcode.contest;

import java.util.*;

public class CheckContainsAllNumbers_2133 {
	public boolean checkValid(int[][] matrix) {
		int n = matrix.length;

		boolean[] row_taken = new boolean[n + 1];
		boolean[] col_taken = new boolean[n + 1];

		for (int i = 0; i < n; ++i) {
			Arrays.fill(row_taken, false);
			Arrays.fill(col_taken, false);

			for (int j = 0; j < n; ++j) {
				if (row_taken[matrix[i][j]] || col_taken[matrix[j][i]])
					return false;
				row_taken[matrix[i][j]] = true;
				col_taken[matrix[j][i]] = true;
			}
		}

		return true;

	}
}
