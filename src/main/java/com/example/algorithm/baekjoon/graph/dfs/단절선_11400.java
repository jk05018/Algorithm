package com.example.algorithm.baekjoon.graph.dfs;

import java.util.*;
import java.io.*;

public class 단절선_11400 {
	static int V, E, counter;
	static List<Integer>[] adj;
	static int[] discovered;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize and make Graph
		String[] inputs = br.readLine().trim().split(" ");
		V = Integer.parseInt(inputs[0]) + 1;
		E = Integer.parseInt(inputs[1]);

		adj = new ArrayList[V];

		for (int i = 0; i < V; ++i) {
			adj[i] = new ArrayList<>();
		}

		discovered = new int[V];
		Arrays.fill(discovered, -1);
		counter = 0;

		for (int i = 0; i < E; ++i) {
			inputs = br.readLine().trim().split(" ");

			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			adj[a].add(b);
			adj[b].add(a);
		}

		List<int[]> answer = new ArrayList<>();

		for (int i = 1; i < V; ++i) {
			if (discovered[i] == -1) {
				getCutLine(i, 0, answer);
			}
		}

		Collections.sort(answer, (a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a1[0] - a2[0]);
		System.out.println(answer.size());

		for (int[] arr : answer) {
			System.out.println(arr[0] + " " + arr[1]);
		}

	}

	static int getCutLine(int here, int parent, List<int[]> answer) {
		discovered[here] = counter++;
		int ret = discovered[here];

		for (int i = 0; i < adj[here].size(); ++i) {
			int there = adj[here].get(i);

			if (discovered[there] == -1) {
				int subtree = getCutLine(there, here, answer);

				if (discovered[here] < subtree) {
					if (here > there) {
						answer.add(new int[] {there, here});
					} else {
						answer.add(new int[] {here, there});
					}

				}
				ret = Math.min(ret, subtree);

			} else if (discovered[here] > discovered[there] && there != parent) {
				ret = Math.min(ret, discovered[there]);
			}
		}

		return ret;

	}

}
