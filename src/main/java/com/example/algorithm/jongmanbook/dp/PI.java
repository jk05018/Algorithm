package com.example.algorithm.jongmanbook.dp;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.Scanner;

public class PI {
	static final int INF = 999_999_999;
	static String s;
	static int[] cache;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			// init Settings
			s = sc.next().trim();
			cache = initCache(s.length());

			// solve
			System.out.println(solve(0));

		}

	}

	private static int calculate(int start, int end) {
		String value = s.substring(start, end);

		// case 1 : 모든 숫자가 같을 때
		boolean same = true;
		for (int i = 1; i < value.length(); ++i) {
			if (value.charAt(0) != value.charAt(i)) {
				same = false;
				break;
			}
		}
		if (same)
			return 1;

		// case 2 숫자가 1씩 단조 증가하거나 단조 감소할 때
		boolean prograssive = true;
		for (int i = 1; i < value.length(); ++i) {
			if (value.charAt(1) - value.charAt(0) != value.charAt(i) - value.charAt(i - 1)) {
				prograssive = false;
				break;
			}
		}

		if (prograssive && Math.abs(value.charAt(1) - value.charAt(0)) == 1) {
			return 2;
		}

		// case 3 : 두 개의 숫자가 번갈아가면서 나타날 때
		boolean alter = true;
		for (int i = 2; i < value.length(); ++i) {
			if (value.charAt(i) != value.charAt(i - 2)) {
				alter = false;
			}
		}

		if (alter)
			return 4;

		// case 4 : 숫자가 등차수열일 때
		if (prograssive)
			return 5;

		return 10;
	}

	private static int solve(int start) {
		// base case
		if (start == s.length())
			return 0;
		// check cache
		if (cache[start] != -1)
			return cache[start];

		int ret = INF;
		for (int plus = 3; plus <= 5; ++plus) {
			if (start + plus <= s.length()) {
				ret = min(ret, calculate(start, start + plus) + solve(start + plus));
			}
		}

		return cache[start] = ret;

	}

	public static int[] initCache(int n) {
		int[] cache = new int[n];

		Arrays.fill(cache, -1);

		return cache;
	}
}
