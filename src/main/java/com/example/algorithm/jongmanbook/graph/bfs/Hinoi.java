package com.example.algorithm.jongmanbook.graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Hinoi {
	static int T, N, MAX_DISC = 12;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			System.out.println(String.format("%d", solve(br)));
		}
	}

	public static int solve(BufferedReader br) throws IOException {
		// take inputs
		N = Integer.parseInt(br.readLine());
		int start = initialState(br);
		int end = initialState(br);

		return bidirectionBfs(start, end);
	}

	static int initialState(BufferedReader br) throws IOException {
		int state = 0;

		for (int i = 0; i < 4; ++i) {
			String[] inputs = br.readLine().trim().split(" ");
			int c = Integer.parseInt(inputs[0]);
			for (int j = 1; j <= c; ++j) {
				state = set(state, Integer.parseInt(inputs[j]), i);
			}
		}

		return state;
	}

	static int bfs(int start, int end) {
		if (start == end)
			return 0;
		int[] cost = new int[1 << ((MAX_DISC + 1) * 2)];
		Arrays.fill(cost, -1);
		Queue<Integer> q = new LinkedList<>();

		q.offer(start);
		cost[start] = 0;

		while (!q.isEmpty()) {
			int here = q.poll();

			int[] top = {-1, -1, -1, -1};
			for (int i = 12; i >= 1; --i) {
				top[get(here, i)] = i;
			}

			for (int i = 0; i < 4; ++i)
				if (top[i] != -1)
					for (int j = 0; j < 4; ++j)
						if (i != j && (top[j] == -1 || top[i] < top[j])) {
							int there = set(here, top[i], j);
							if (cost[there] != -1)
								continue;
							cost[there] = cost[here] + 1;
							if (end == there)
								return cost[there];
							q.offer(there);
						}
		}

		return -1;
	}

	static int bidirectionBfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		int[] cost = new int[1 << ((MAX_DISC + 1) * 2)];

		q.offer(start);
		q.offer(end);
		cost[start] = 1;
		cost[end] = -1;

		while (!q.isEmpty()) {
			int here = q.poll();

			int[] top = {-1, -1, -1, -1};
			for (int i = N; i >= 1; --i) {
				top[get(here, i)] = i;
			}

			for (int i = 0; i < 4; ++i) {
				if (top[i] != -1) {
					for (int j = 0; j < 4; ++j) {
						if (i != j && (top[j] == -1 || top[j] > top[i])) {
							int there = set(here, top[i], j);
							if (cost[there] == 0) {
								cost[there] = incr(cost[here]);
								q.offer(there);
							} else if (sign(cost[there]) != sign(cost[here])) {
								return Math.abs(cost[there]) + Math.abs(cost[here]) - 1;
							}

						}

					}
				}

			}
		}

		return -1;

	}

	static int sign(int x) {
		return x == 0 ? 0 : x > 0 ? 1 : -1;
	}

	static int incr(int x) {
		return x < 0 ? x - 1 : x + 1;
	}

	static int get(int state, int index) {
		return (state >> (index * 2)) & 3;
	}

	static int set(int state, int index, int value) {
		return (state & ~(3 << (index * 2))) | (value << (index * 2));
	}
}

