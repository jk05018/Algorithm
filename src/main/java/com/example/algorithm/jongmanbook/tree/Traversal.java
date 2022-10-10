package com.example.algorithm.jongmanbook.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.util.*;

public class Traversal {
	static Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	private static void solve() {
		int n = sc.nextInt();
		List<Integer> preOrder = new ArrayList<>();
		List<Integer> inOrder = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			preOrder.add(sc.nextInt());
		}

		for (int i = 0; i < n; ++i) {
			inOrder.add(sc.nextInt());
		}

		traversal(preOrder, inOrder);
	}

	private static void traversal(List<Integer> preOrder, List<Integer> inOrder) {
		int n = preOrder.size();
		// base case
		if (n == 0) {
			return;
		}

		int root = preOrder.get(0);
		int l = -1;

		// find root index in inOrder list
		for (int i = 0; i < n; ++i) {
			if (inOrder.get(i) == root) {
				l = i;
				break;
			}
		}

		traversal(slice(preOrder, 1, l + 1), slice(inOrder, 0, l));
		traversal(slice(preOrder, l + 1, preOrder.size()), slice(inOrder, l + 1, inOrder.size()));

		System.out.print(root + " ");
	}

	private static List<Integer> slice(List<Integer> list, int start, int end) {
		List<Integer> newList = new ArrayList<>();

		for (int i = start; i < end; ++i) {
			newList.add(list.get(i));
		}

		return newList;
	}

	public static void main(String[] args) {
		int tt = sc.nextInt();

		for (int t = 0; t < tt; ++t) {
			solve();
		}
	}
}
