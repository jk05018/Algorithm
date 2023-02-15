package com.example.algorithm.jongmanbook.graph.최단거리;

import java.util.*;
import java.io.*;

public class FireCar {
	static int T, V, E, n, m;
	static List<Edge>[] adj;
	static StringTokenizer st;

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
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for(int i=0; i<=V; ++i) {
			adj[i] = new ArrayList<>();
		}

		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[from].add(new Edge(to, dist));
			adj[to].add(new Edge(from, dist));
		}

		int[] fire = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; ++i) {
			fire[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; ++i) {
			int to = Integer.parseInt(st.nextToken());
			adj[0].add(new Edge(to, 0));
			adj[to].add(new Edge(0, 0));
		}

		int[] dist = dijkstra(0);

		int sum = 0;
		for(int i : fire) {
			sum += dist[i];
		}

		return sum;

	}

	static int[] dijkstra(int src) {
		boolean[] visited = new boolean[V+1];
		int[] cost = new int[V+1];
		Arrays.fill(cost, Integer.MAX_VALUE);

		cost[src] = 0;

		while(true) {
			int closet = Integer.MAX_VALUE, here = -1;

			for(int i=0; i<=V; ++i) {
				if(closet > cost[i] && !visited[i]) {
					closet = cost[i];
					here = i;
				}
			}

			if(closet == Integer.MAX_VALUE)
				break;

			visited[here] = true;
			for(Edge there : adj[here]) {
				if(visited[there.to]) continue;
				int nextDist = cost[here] + there.dist;
				cost[there.to] = Math.min(cost[there.to], nextDist);
			}
		}
		return cost;
	}
}

class Edge {
	int to;
	int dist;

	Edge(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}
}

