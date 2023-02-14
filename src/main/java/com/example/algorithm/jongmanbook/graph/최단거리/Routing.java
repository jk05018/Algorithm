package com.example.algorithm.jongmanbook.graph.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Routing {
	static int T, N, M;
	static List<Vertex>[] adj;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			System.out.println(String.format("%.10f", solve(br)));
		}
	}

	public static double solve(BufferedReader br) throws IOException {
		// take inputs
		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		adj = new ArrayList[N];
		for (int i = 0; i < N; ++i) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			inputs = br.readLine().trim().split(" ");

			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			double percent = Double.parseDouble(inputs[2]);

			adj[from].add(Vertex.forAdj(to, percent));
			adj[to].add(Vertex.forAdj(from, percent));
		}

		double[] cost = dijkstra(0);

		return cost[N - 1];
	}

	private static double[] dijkstra(int src) {
		double[] cost = new double[N];
		Arrays.fill(cost, Double.MAX_VALUE);

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(Vertex.forVertex(src, 1));
		cost[src] = 1;

		while (!pq.isEmpty()) {
			Vertex v = pq.poll();

			if (cost[v.index] < v.cost)
				continue;

			for (int i = 0; i < adj[v.index].size(); ++i) {
				Vertex there = adj[v.index].get(i);
				if (there.index == 0)
					continue;
				double nextCost = v.cost * there.percent;

				if (cost[there.index] > nextCost) {
					cost[there.index] = nextCost;
					pq.offer(Vertex.forVertex(there.index, nextCost));
				}
			}
		}

		return cost;
	}
}

class Vertex implements Comparable<Vertex> {
	int index;
	double percent;
	double cost;

	private Vertex(int index, double percent, double cost) {
		this.index = index;
		this.percent = percent;
		this.cost = cost;
	}

	public static Vertex forAdj(int index, double percent) {
		return new Vertex(index, percent, -1);
	}

	public static Vertex forVertex(int index, double cost) {
		return new Vertex(index, -1, cost);
	}

	@Override
	public int compareTo(Vertex v) {
		if (this.cost != v.cost) {
			return Integer.compare(this.index, v.index);
		} else {
			return Double.compare(this.cost, v.cost);
		}

	}
}
