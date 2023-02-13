package com.example.algorithm.jongmanbook.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class SoLong {
	static int T, N, M;

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
		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		TrieNode trie = new TrieNode();

		Word[] words = new Word[N];
		for (int i = 0; i < N; ++i) {
			inputs = br.readLine().trim().split(" ");
			words[i] = new Word(inputs[0], Integer.parseInt(inputs[1]));
		}

		Arrays.sort(words, new Comparator<Word>() {
			@Override
			public int compare(Word a, Word b) {
				if (a.prior != b.prior) {
					return Integer.compare(b.prior, a.prior);
				}

				return a.value.compareTo(b.value);
			}
		});

		for (int i = 0; i < N; ++i) {
			trie.insert(words[i].value.toCharArray(), 0, i);
		}

		trie.first = -1;

		inputs = br.readLine().trim().split(" ");

		int cnt = 0;

		for (int i = 0; i < M; ++i) {
			cnt += countKeys(trie, inputs[i]);
		}

		return cnt + M - 1;

	}

	private static int countKeys(TrieNode trie, String word) {
		TrieNode find = trie.find(word.toCharArray(), 0);
		if (find == null || find.terminal == -1)
			return word.length();
		return trie.type(word.toCharArray(), 0, find.terminal);
	}

}

class Word {
	String value;
	int prior;

	Word(String value, int prior) {
		this.value = value;
		this.prior = prior;
	}
}

class TrieNode {
	// static
	public static final int ALPHABETS = 26;

	public static int toNumber(char c) {
		return c - 'A';
	}

	// instance
	TrieNode[] children;
	int terminal;
	int first;

	TrieNode() {
		this.children = new TrieNode[ALPHABETS];
		this.terminal = -1;
		this.first = -1;
	}

	void insert(char[] word, int index, int id) {
		// 먼저 first를 갱신
		if (first == -1)
			first = id;

		// 추가
		if (index == word.length) {
			terminal = id;
		} else {
			int next = toNumber(word[index]);

			if (children[next] == null)
				children[next] = new TrieNode();

			children[next].insert(word, index + 1, id);
		}
	}

	// find 왜 종료 노드가 아니어도 반환하는지 잘 알아두자
	TrieNode find(char[] word, int index) {
		if (index == word.length)
			return this;
		int next = toNumber(word[index]);
		if (children[next] == null)
			return null;
		return children[next].find(word, index + 1);
	}

	int type(char[] word, int index, int id) {
		if (index == word.length)
			return 0;
		if (first == id)
			return 1;

		int next = toNumber(word[index]);
		return 1 + children[next].type(word, index + 1, id);
	}
}
