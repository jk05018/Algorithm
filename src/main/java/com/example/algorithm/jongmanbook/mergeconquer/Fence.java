package com.example.algorithm.jongmanbook.mergeconquer;

import static java.lang.Math.*;

import java.util.Scanner;

public class Fence {

	static int[] h;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt();

		for (int c = 0; c < C; ++c) {
			int n = sc.nextInt();
			h = new int[n];

			for (int i = 0; i < n; ++i) {
				h[i] = sc.nextInt();
			}

			System.out.println(solve(0, n - 1));
		}
	}

	private static int solve(int left, int right) {
		if (left == right)
			return h[left];

		int mid = left + (right - left) / 2;
		int ret = max(solve(left, mid), solve(mid + 1, right));
		int lo = mid, hi = mid + 1;
		int height = min(h[lo], h[hi]);
		ret = max(ret, height * 2);

		while (left < lo || hi < right) {
			if (hi < right && (lo == left || h[lo - 1] < h[hi + 1])) {
				++hi;
				height = min(height, h[hi]);
			} else {
				--lo;
				height = min(height, h[lo]);
			}

			ret = max(ret, height * (hi - lo + 1));
		}

		return ret;

	}
}
