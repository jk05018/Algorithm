package com.example.algorithm.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 광고_1305 {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		System.out.println(N - getPi(br.readLine()));
	}

	static int getPi(String S) {
		int s = S.length();
		int[] pi = new int[s];

		int begin = 1, matched = 0;

		while (begin + matched < s) {
			if (S.charAt(begin + matched) == S.charAt(matched)) {
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

		return pi[s - 1];
	}
}
