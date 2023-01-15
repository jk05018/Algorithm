package com.example.algorithm.leetcode.contest.d20220115;

public class P6291 {
	public int differenceOfSum(int[] nums) {
		int esum = 0;
		int dsum = 0;

		for(int num : nums) {
			esum += num;

			int value = num;
			while(value > 0) {
				dsum += value % 10;
				value /= 10;
			}
		}

		return Math.abs(esum - dsum);
	}
}
