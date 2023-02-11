package com.example.algorithm.baekjoon.graph.dfs;

import java.util.*;
import java.io.*;

public class 사회망서비스_2533 {
	static int N, people, counter;
	static int[] discovered;
	static List<Integer>[] adj;
	static final int NOADAPTER = 1, ADAPTER = 0;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// make Graph
		adj = new ArrayList[N + 1];
		discovered = new int[N + 1];
		counter = 0;
		people = 0;

		for (int i = 0; i < N + 1; ++i) {
			adj[i] = new ArrayList<>();
		}

		Arrays.fill(discovered, -1);

		for (int i = 1; i < N; ++i) {
			String[] inputs = br.readLine().trim().split(" ");

			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			adj[a].add(b);
			adj[b].add(a);
		}

		dfs(1);

		System.out.println(String.format("%d", people));
	}

	static int dfs(int here) {
		discovered[here] = counter++;

		int noAdapterCnt = 0;

		for (int i = 0; i < adj[here].size(); ++i) {
			int there = adj[here].get(i);

			if (discovered[there] == -1) {
				noAdapterCnt += dfs(there);
			}
		}

		if (noAdapterCnt > 0) {
			++people;
			return ADAPTER;
		}

		return NOADAPTER;
	}

}

