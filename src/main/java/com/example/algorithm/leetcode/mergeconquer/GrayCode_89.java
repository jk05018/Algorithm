package com.example.algorithm.leetcode.mergeconquer;

import java.util.*;

public class GrayCode_89 {
	// merge and conquer
	static int[] nums;
	static int N;

	public List<Integer> grayCode(int n) {
		// init Settings
		N = (int)Math.pow(2, n);
		nums = new int[N];

		for (int i = 0; i < N; ++i) {
			nums[i] = i;
		}

		return solve(0, N - 1);
	}

	public List<Integer> solve(int left, int right) {
		// base case
		if (left == right) {
			return new ArrayList<Integer>() {{
				add(nums[left]);
			}};
		}

		int mid = left + (right - left) / 2;

		List<Integer> leftList = solve(left, mid);
		List<Integer> rightList = solve(mid + 1, right);

		return merge(leftList, rightList);
	}

	public List<Integer> merge(List<Integer> left, List<Integer> right) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < left.size(); ++i) {
			list.add(left.get(i));
		}

		for (int i = right.size() - 1; i >= 0; --i) {
			list.add(right.get(i));
		}

		return list;
	}
}
