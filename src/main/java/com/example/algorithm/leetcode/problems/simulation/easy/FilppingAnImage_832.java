package com.example.algorithm.leetcode.problems.simulation.easy;

public class FilppingAnImage_832 {

	/*
	Time Complexity : O(N^2)
	Space Complexity = O(N^2)

	My Solution
	 */
	public int[][] flipAndInvertImage(int[][] image) {
		int N = image.length;
		int[][] newImage = new int[N][N];

		for (int i = 0; i < N; ++i) {
			for (int j = N - 1; j >= 0; --j) {
				newImage[i][N - 1 - j] = image[i][j] == 1 ? 0 : 1;
			}
		}

		return newImage;
	}

	/*
	Time Complexity : O(N * M)
	Space Complexity = O(1)

	which is a brilliant idea
	 */
	public int[][] flipAndInvertImage2(int[][] image) {
		if (image == null || image.length == 0 || image[0].length == 0) {
			return image;
		}

		for (int[] row : image) {
			int start = 0;
			int end = row.length - 1;
			while (start <= end) {
				if (row[start] == row[end]) {
					row[start] ^= 1;
					row[end] = row[start];
				}
				start++;
				end--;
			}
		}

		return image;
	}
}
