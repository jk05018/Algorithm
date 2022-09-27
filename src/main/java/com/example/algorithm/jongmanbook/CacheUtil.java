package com.example.algorithm.jongmanbook;

import java.util.Arrays;

public class CacheUtil {

	public static int[] initCache(int n){
		int[] cache = new int[n];

		Arrays.fill(cache, -1);

		return cache;
	}

	public static int[][] initCache(int n, int m){
		int[][] cache = new int[n][m];

		for(int[] arr : cache){
			Arrays.fill(arr, -1);
		}

		return cache;
	}
}
