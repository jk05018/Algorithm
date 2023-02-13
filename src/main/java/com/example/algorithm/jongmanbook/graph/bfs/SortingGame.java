package com.example.algorithm.jongmanbook.graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SortingGame {
	static int T, N;
	static Map<Integer, Integer> toSort;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		toSort = new HashMap<>();
		for (int i = 1; i <= 8; ++i) {
			precalc(i);
		}

		for (int t = 1; t <= T; ++t) {
			System.out.println(String.format("%d", solve(br)));
		}
	}

	public static int solve(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		String[] inputs = br.readLine().trim().split(" ");
		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(inputs[i]);
		}

		int[] newArr = new int[N];
		for (int i = 0; i < N; ++i) {
			int smaller = 0;
			for (int j = 0; j < N; ++j) {
				if (arr[j] < arr[i])
					++smaller;
			}
			newArr[i] = smaller;
		}

		return toSort.get(Arrays.hashCode(newArr));
	}

	static void precalc(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; ++i)
			arr[i] = i;

		Queue<MyArr> q = new LinkedList<>();
		MyArr myArr = new MyArr(arr);
		q.offer(myArr);
		toSort.put(myArr.hash, 0);

		while (!q.isEmpty()) {
			myArr = q.poll();
			arr = myArr.arr;

			int cost = toSort.get(myArr.hash);

			for (int i = 0; i < N; ++i) {
				for (int j = i + 2; j <= N; ++j) {
					reverse(i, j, arr);
					MyArr next = new MyArr(arr);
					if (toSort.get(next.hash) == null) {
						q.offer(next);
						toSort.put(next.hash, cost + 1);
					}

					reverse(i, j, arr);
				}
			}
		}
	}

	static void reverse(int start, int end, int[] arr) {
		int[] temp = new int[end - start];

		for (int i = start; i < end; ++i) {
			temp[i - start] = arr[i];
		}

		for (int i = 0; i < temp.length; ++i) {
			arr[end - i - 1] = temp[i];
		}
	}
}

class MyArr {
	int[] arr;
	int hash;

	MyArr(int[] arr) {
		this.arr = arr.clone();
		this.hash = Arrays.hashCode(this.arr);
	}
}

