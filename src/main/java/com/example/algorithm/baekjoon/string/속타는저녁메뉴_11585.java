package com.example.algorithm.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 속타는저녁메뉴_11585 {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		String want = br.readLine().trim().replaceAll(" ", "");
		String now = br.readLine().trim().replaceAll(" ", "");

		int canEat = search(want, now);

		if (N < canEat) {
			System.out.println(String.format("%d/%d", 1, 1));
		} else {
			int div = gcd(N, canEat);
			System.out.println(String.format("%d/%d", canEat / div, N / div));
		}

	}

	static int search(String now, String want) {
		return kmpSearch(now + now, want);
	}

	static int kmpSearch(String A, String B) {
		int a = A.length(), b = B.length();
		int[] pi = getPartialMatch(B);

		int begin = 0, matched = 0, count = 0;
		while (begin < a - b) {
			if (matched < b && A.charAt(begin + matched) == B.charAt(matched)) {
				++matched;
				if (matched == b)
					++count;
			} else {
				if (matched == 0)
					++begin;
				else {
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return count;
	}

	static int[] getPartialMatch(String B) {
		int b = B.length();
		int[] pi = new int[b];

		int begin = 1, matched = 0;
		while (begin + matched < b) {
			if (B.charAt(begin + matched) == B.charAt(matched)) {
				++matched;
				pi[begin + matched - 1] = matched;
			} else {
				if (matched == 0)
					++begin;
				else {
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return pi;
	}

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
