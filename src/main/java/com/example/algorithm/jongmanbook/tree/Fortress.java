package com.example.algorithm.jongmanbook.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.util.*;

public class Fortress {
	static Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	static int[] x, y, radius;
	static int N;
	static int longest;

	private static void solve() {
		// init Settings
		N = sc.nextInt();
		x = new int[N];
		y = new int[N];
		radius = new int[N];

		for (int i = 0; i < N; ++i) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
			radius[i] = sc.nextInt();
		}

		// solve
		longest = 0;
		TreeNode root = getTree(0);
		int h = height(root);
		System.out.println(Math.max(longest, h));
	}

	private static TreeNode getTree(int root) {
		TreeNode ret = new TreeNode();

		for (int i = 0; i < N; ++i) {
			if (isChild(root, i)) {
				ret.child.add(getTree(i));
			}
		}

		return ret;
	}

	private static int sqr(int x) {
		return x * x;
	}

	// return distance between wall A and wall B
	private static int sqrdist(int a, int b) {
		return sqr(x[a] - x[b]) + sqr(y[a] - y[b]);
	}

	// return true if wall A include wall B
	private static boolean encloses(int a, int b) {
		return radius[a] > radius[b] && sqrdist(a, b) < sqr(radius[a] - radius[b]);
	}

	private static boolean isChild(int parent, int child) {
		if (!encloses(parent, child)) {
			return false;
		}

		for (int i = 0; i < N; ++i) {
			if (i != parent && i != child && encloses(parent, i) && encloses(i, child)) {
				return false;
			}
		}

		return true;
	}

	private static int height(TreeNode root) {
		List<Integer> heights = new ArrayList<>();

		for (int i = 0; i < root.child.size(); ++i) {
			heights.add(height(root.child.get(i)));
		}

		// base case
		int len = heights.size();

		if (len == 0)
			return 0;

		// sorting to find two largest heights
		Collections.sort(heights);

		if (len >= 2) {
			longest = Math.max(longest, 2 + heights.get(len - 1) + heights.get(len - 2));
		}

		return 1 + heights.get(len - 1);
	}

	public static void main(String[] args) {
		int tt = sc.nextInt();

		for (int t = 0; t < tt; ++t) {
			solve();
		}
	}

}

// TreeNode
class TreeNode {
	List<TreeNode> child = new ArrayList<>();
	;
}
