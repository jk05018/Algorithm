package com.example.algorithm.jongmanbook.dp;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.Scanner;

import com.example.algorithm.jongmanbook.CacheUtil;

public class Quantize {
	static final int INF = 999_999_999;
	static int N, S;
	static int[] nums, pSum, pSqSum;
	static int[][] cache;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			// init Settings
			N = sc.nextInt();
			S = sc.nextInt();

			nums = new int[N];
			pSum = new int[N];
			pSqSum = new int[N];
			cache = CacheUtil.initCache(N, S);

			for (int i = 0; i < N; ++i) {
				nums[i] = sc.nextInt();
			}

			//solve
			preCalc();
			System.out.println(quantize(0,S));
		}

	}

	private static int quantize(int start, int parts) {
		// base case
		if (start == N)
			return 0;
		if (parts == 0)
			return INF;

		// check Caches
		if (cache[start][parts] != -1) {
			return cache[start][parts];
		}

		// solve
		int ret = INF;
		for (int plus = 1; start + plus <= N; ++plus) {
			ret = min(ret, minError(start, start + plus - 1) + quantize(start + plus, parts - 1));
		}

		return cache[start][parts] = ret;
	}

	private static void preCalc(){
		// sorting ascending
		Arrays.sort(nums);

		pSum[0] = nums[0];
		pSqSum[0] = nums[0] * nums[0];

		for (int i = 1; i < nums.length; ++i) {
			pSum[i] = pSqSum[i - 1] + nums[i];
			pSqSum[i] = pSqSum[i - 1] + nums[i] * nums[i];
		}
	}

	private static int minError(int start, int end){
		int sum = pSum[end] - pSum[start];
		int sqSum = pSqSum[end] - pSqSum[start];

		int m = (int)(0.5 + (double)sum / (end - start + 1));
		int ret = sqSum - 2 * m * sum + m * m * (end - start + 1);

		return ret;
	}
}
