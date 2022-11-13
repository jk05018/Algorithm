package com.example.algorithm.contest.lgcontest;

import java.util.ArrayList;
import java.util.List;

public class P2 {

	static int count;

	public static int solution(int k, int[] limits, int[][] sockets) {
		// init Trees
		count = 0;
		TreeNode root = makeTree(limits, sockets, 0);
		traverse(root, k);
		return count;
	}

	public static int traverse(TreeNode node, int k) {
		// base case
		if (node == null) {
			return 0;
		}

		int ret = 0;
		for (TreeNode plug : node.plugs) {
			ret += traverse(plug, k);
		}

		int plugCounts = node.items + ret;

		while (plugCounts * k > node.maxCapacity) {
			--plugCounts;
			++count;
		}

		return plugCounts;

	}

	public static TreeNode makeTree(int[] limits, int[][] sockets, int index) {
		TreeNode node = new TreeNode(index, limits[index]);

		for (int i = 0; i < 5; ++i) {
			int status = sockets[index][i];

			if (status == -1) {
				++node.items;
			}

			if (status >= 2) {
				node.plugs.add(makeTree(limits, sockets, status - 1));
			}
		}

		return node;
	}

	public static void main(String[] args) {
		int k = 120;
		int[] limits = new int[] {1500, 300, 250, 359, 600};
		int[][] sockets = new int[][] {
			{2, 3, 4, 0, -1},
			{0, 0, 0, 0, 0},
			{-1, -1, -1, 0, 0},
			{0, 0, 5, 0, 0},
			{-1, 0, 0, -1, -1}
		};

		System.out.println(solution(k, limits, sockets));
	}

}

class TreeNode {
	int index;
	int maxCapacity;
	int items;
	List<TreeNode> plugs;

	TreeNode(int index, int maxCapacity) {
		this.index = index;
		this.maxCapacity = maxCapacity;
		this.items = 0;
		this.plugs = new ArrayList<>();
	}
}
