package com.example.algorithm.jongmanbook.dynamicprogramming;

import java.util.Scanner;

import com.example.algorithm.jongmanbook.CacheUtil;

public class Wildcard {
	static String wild, target;
	static int[][] cache;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tt = sc.nextInt();

		for (int t = 0; t < tt; ++t) {
			wild = sc.next();

			int k = sc.nextInt();
			for (int c = 0; c < k; ++c) {
				target = sc.next();

				cache = CacheUtil.initCache(wild.length(), target.length());

				if(solve(0,0)){
					System.out.println(target);
				}
			}
		}
	}

	public static boolean solve(int w, int t){
		if(cache[w][t] != -1){
			return cache[w][t] == 1;
		}
		while (w < wild.length() && t < target.length() &&
			(wild.charAt(w) == '?' || wild.charAt(w) == target.charAt(t))) {
			++w;
			++t;
		}

		if(w == wild.length())
			return t == target.length();

		if (wild.charAt(w) == '*') {
			for(int skip = 0; t + skip <= target.length() ; ++skip){
				if (solve(w + 1, t + skip)) {
					cache[w][t] = 1;
					return true;
				}
			}
		}

		cache[w][t] = 0;
		return false;
	}
}
