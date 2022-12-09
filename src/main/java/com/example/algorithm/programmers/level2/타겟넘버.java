package com.example.algorithm.programmers.level2;

public class 타겟넘버 {

	public int solution(int[] numbers, int target) {
		return method(numbers, target, 0, 0);
	}

	public int method(int[] numbers, int target, int index, int sum) {
		// base case
		if (index == numbers.length) {
			if (sum == target) {
				return 1;
			}
			return 0;
		}

		// solve
		int ret = 0;

		ret += method(numbers, target, index + 1, sum + numbers[index]);
		ret += method(numbers, target, index + 1, sum - numbers[index]);

		return ret;
	}
}
