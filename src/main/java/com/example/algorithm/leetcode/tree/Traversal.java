package com.example.algorithm.leetcode.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.util.*;

public class Traversal {
	static Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	private static void solve() {
		// init Settings
		int n = sc.nextInt();
		List<Integer> preOrder = new ArrayList<>();
		List<Integer> inOrder = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			preOrder.add(sc.nextInt());
		}

		for (int i = 0; i < n; ++i) {
			inOrder.add(sc.nextInt());
		}

		travel(preOrder, inOrder);
	}

	private static void travel(List<Integer> preOrder, List<Integer> inOrder) {
		int len = preOrder.size();

		// base case
		if (len == 0) {
			return;
		}

		int root = preOrder.get(0);

		// find root index in inOrder List
		int m = findIndex(inOrder, root);

		travel(slice(preOrder, 1, m + 1), slice(inOrder, 0, m));
		travel(slice(preOrder, m + 1, preOrder.size()), slice(inOrder, m + 1, inOrder.size()));

		System.out.print(root + " ");

	}

	private static List<Integer> slice(List<Integer> list, int start, int end) {
		List<Integer> newList = new ArrayList<>();

		for (int i = start; i < end; ++i) {
			newList.add(list.get(i));
		}

		return newList;
	}

	private static int findIndex(List<Integer> list, int value) {
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) == value) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int tt = sc.nextInt();

		for (int t = 0; t < tt; ++t) {
			solve();
		}
	}
}
