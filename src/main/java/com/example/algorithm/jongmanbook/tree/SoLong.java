package com.example.algorithm.jongmanbook.tree;

import java.util.*;
import java.io.*;

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

		PriorityQueue<Word> pq = new PriorityQueue<>();

		for (int i = 0; i < N; ++i) {
			inputs = br.readLine().trim().split(" ");
			pq.offer(new Word(inputs[0], i, Integer.parseInt(inputs[1])));
		}

		TrieNode trie = new TrieNode();

		for (int i = 1; i <= N; ++i) {
			Word word = pq.poll();
			trie.insert(word.word, i);
		}

		trie.first = -1;

		inputs = br.readLine().trim().split(" ");

		int cnt = 0;
		for (int i = 0; i < M; ++i) {
			cnt += countKeys(trie, inputs[i]);
		}

		return cnt + M - 1;
	}

	// 사전을 나타내는 트라이가 주어질 때, 단어 word를 타이핑하는데 몇 번이나 키를 눌러야하는지 계산한다.
	static int countKeys(TrieNode trie, String word) {
		// 이 문자열이 사전에 있는지 확인하고, 있다면 번호를 구한다.
		TrieNode node = trie.find(word);
		// 사전에 없다면 직접 타이핑할 수밖에 없다.
		if (node == null || node.terminal == -1)
			return word.length();
		// 탐색하면서 타이핑 할 방법을 찾는다.
		return trie.type(word, node.terminal);
	}
}

class TrieNode {
	// static
	private static final int ALPHABETS = 26;

	public static int toNumber(char c) {
		return c - 'A';
	}

	// instance
	TrieNode[] children = new TrieNode[ALPHABETS];
	int terminal = -1;
	int first = -1;

	// 이 노드를 루트로 하는 트라이에 번호 id인 문자열 key를 추가한다.
	void insert(String key, int id) {
		if (first == -1)
			first = id;
		if (key.length() == 0) {
			terminal = id;
		} else {
			int next = toNumber(key.charAt(0));
			if (children[next] == null)
				children[next] = new TrieNode();
			children[next].insert(key.substring(1), id);
		}
	}

	TrieNode find(String key) {
		if (key.length() == 0)
			return this;
		int next = toNumber(key.charAt(0));
		if (children[next] == null)
			return null;
		return children[next].find(key.substring(1));
	}

	// 이 노드가 타이핑해 왔을 때, 번호가 id인 키를 타이핑하기 위해 최소 몇번의 키를 눌러야 하나?
	int type(String key, int id) {
		// 문자열 종료 시
		if (key.length() == 0)
			return 0;
		// 이 노드에서 추천되는 문자열이 이 문자열이면 탭 누르고 종료
		if (first == id)
			return 1;
		// 이 노드에서 추천되는 문자열이 이 문자열이면 탭 누르고 종료
		int next = toNumber(key.charAt(0));
		return 1 + children[next].type(key.substring(1), id);
	}
}

class Word implements Comparable<Word> {
	String word;
	int index;
	int priority;

	Word(String word, int index, int priority) {
		this.word = word;
		this.index = index;
		this.priority = priority;
	}

	@Override
	public int compareTo(Word w) {
		if (this.priority == w.priority) {
			return this.word.compareTo(w.word);
		} else {
			return Integer.compare(w.priority, this.priority);
		}
	}

}

