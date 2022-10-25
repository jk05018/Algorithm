package com.example.algorithm.jongmanbook.tree;

import java.util.Arrays;
import java.util.Scanner;

public class EditorWar {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tt = sc.nextInt();
		for (int t = 0; t < tt; ++t) {
			solve();
		}
	}

	static void solve() {
		int N = sc.nextInt();
		int M = sc.nextInt();

		BipartiteUnionFind buf = new BipartiteUnionFind(N);

		int flag = -1;

		for (int i = 0; i < M; ++i) {
			String type = sc.next();

			int from = sc.nextInt();
			int to = sc.nextInt();

			if (type.equals("ACK")) {
				if (!buf.ack(from, to)) {
					flag = i;
					break;
				}
			} else {
				if (!buf.dis(from, to)) {
					flag = i;
					break;
				}
			}

			if (flag != -1) {
				break;
			}
		}

		if (flag == -1) {
			System.out.println(String.format("MAX PARTY SIZE IS %d", maxParty(buf, N)));
		} else {
			System.out.println(String.format("CONTRADICTION AT %d", flag + 1));
		}

	}

	static int maxParty(BipartiteUnionFind buf, int n) {
		int ret = 0;
		for (int node = 0; node < n; ++node) {
			// root라면
			if (buf.parent[node] == node) {
				int enemy = buf.enemy[node];

				if (enemy > node)
					continue;
				int mySize = buf.size[node];
				int enemySize = (enemy == -1 ? 0 : buf.size[enemy]);

				ret += Math.max(mySize, enemySize);
			}
		}
		return ret;
	}
}

class BipartiteUnionFind {
	int[] parent, rank, enemy, size;

	BipartiteUnionFind(int n) {
		this.parent = new int[n];
		this.rank = new int[n];
		this.enemy = new int[n];
		this.size = new int[n];

		Arrays.fill(enemy, -1);
		Arrays.fill(size, 1);

		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = find(parent[u]);
	}

	int merge(int u, int v) {
		// u나 v가 공집합일 경우 나머지 하나를 반환한다.?
		if (u == -1 || v == -1) {
			return Math.max(u, v);
		}

		u = find(u);
		v = find(v);

		if (u == v) {
			return u;
		}

		// swap
		if (rank[u] > rank[v]) {
			int temp = u;
			u = v;
			v = temp;
		}

		parent[u] = v;
		size[v] += size[u];
		return v;
	}

	// u와 v가 서로 적대하는 집합에 속한다.
	boolean dis(int u, int v) {
		u = find(u);
		v = find(v);

		// 같은 집합에 속해 있다면 모순
		if (u == v) {
			return false;
		}

		// 적의 적은 나의 동지
		int a = merge(u, enemy[v]);
		int b = merge(v, enemy[u]);
		enemy[a] = b;
		enemy[b] = a;
		return true;
	}

	boolean ack(int u, int v) {
		u = find(u);
		v = find(v);

		// 두 집합이 서로 적대 관계라면 모순
		if (enemy[u] == v) {
			return false;
		}

		// 동지의 적은 나의 적
		int a = merge(u, v);
		int b = merge(enemy[u], enemy[v]);
		enemy[a] = b;

		if (b != -1) {
			enemy[b] = a;
		}

		return true;
	}
}
