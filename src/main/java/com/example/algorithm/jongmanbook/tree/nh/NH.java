package com.example.algorithm.jongmanbook.tree.nh;

import java.util.*;

import java.util.*;
import java.io.*;

public class NH {
	static int T, N, M;
	static final int MOD = 10007;
	static int[][] cache;

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
		cache = new int[101][1001];



		return 0;

	}

	// 앞으로 length 글자를 더 만들어야 하는데, 아호-코라식 알고리즘의
	// // 현재 상태가 state에 주어질 때 IDS에 걸리지 않는 문자열 수는?
	// private static int count(int length, TrieNode state) {
	// 	// base case: 이 상태에 문자열이 출현하면 곧장 종료
	// 	if(state.output.size() == 0) return 0;
	// 	// base case: 문자열을 전부 만든 경우
	// 	if(length == 0) return 1;
	//
	// 	int ret = cache[length][state.no]; // ??
	// 	if(ret != -1) return ret;
	// 	ret = 0;
	//
	// 	// 다음으로 letter 글자를 넣을 경우
	// 	for(int letter = 0; letter < TrieNode.ALPHABETS; ++letter) {
	// 		ret += count(length - 1, state.children[letter]);
	// 		ret %= MOD;
	// 	}
	//
	// 	return ret;
	//
	// }


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
	TrieNode fail = null;
	List<Integer> output = new ArrayList<>();

	/**
	 * 이 노드를 루트로 하는 트라이에 문자열 key를 추가한다.
	 * */
	void insert(char[] key, int index , int id) {
		if(key.length == index) {
			terminal = id;
		} else {
			int next = toNumber(key[index]);
			if(children[next] == null)
				children[next] = new TrieNode();
			children[next].insert(key, index+1, id);
		}
	}

	/**
	 * 이 노드를 루트로 하는 트라이에 문자열 key와 대응되는 노드를 찾는다.
	 * 없으면 NULL을 반환한다.
	 * */
	TrieNode find(char[] key, int index) {
		if(key.length == index) return this;
		int next = toNumber(key[index]);
		if(children[next] == null) return null;
		return children[next].find(key, index+1);
	}

	/**
	 *  트라이가 주어질 때 각 노드에 대해 실패 연결과 출력 문자열 목록을 계산한다.
	 * */
	public static void computeFailFunc(TrieNode root) {
		// 루트에서부터 시작해 한 단계씩 아래로 내려가며 각 노드의 실패 연결을 계산한다.
		Queue<TrieNode> q = new LinkedList<>();
		root.fail = root;
		q.offer(root);

		while(!q.isEmpty()) {
			TrieNode here = q.poll();
			// here의 모든 자손에 대해 실패 연결을 계산하고 이들을 큐에 넣는다.
			for(int edge = 0; edge < ALPHABETS; ++edge) {
				TrieNode child = here.children[edge];
				if(child == null) continue;
				// 1레벨 노드의 실패 연결은 항상 루트
				if(here == root)
					child.fail = root;
				else {
					// 아닌 경우 부모의 실패 연결을 따라가면서 실패 연결을 찾는다.
					TrieNode t = here.fail;
					while(t != root && t.children[edge] == null) // <= 이것을 이해해야해!
						t = t.fail;
					if(t.children[edge] != null)
						t = t.children[edge];
					child.fail = t;
				}

				// 출력 문자열 목록: 실패 연결을 따라간 노드에서 복사해온 뒤
				// 이 위치에서 끝나는 바늘 문자열이 있으면 추가한다.
				child.output = child.fail.output;
				if(child.terminal != -1)
					child.output.add(child.terminal);
				q.offer(child);
			}
		}
	}

	/**
	 * trie에 포함된 패턴들을 s에서 찾는다.
	 * s 내에서 패턴이 출현할 때마다 (마지막 글자, 패턴 번호) 쌍을 저장한다.
	 * */
	public static List<int[]> ahoCorasick(String s, TrieNode root) {
		List<int[]> ret = new ArrayList<>();
		TrieNode state = root;
		// 실제 반복문 내는 KMP와 별로 다를것이 없다.
		for(int i=0; i<s.length(); ++i) {
			int chr = toNumber(s.charAt(i));
			while(state != root && state.children[chr] == null)
				state = state.fail;
			if(state.children[chr] != null)
				state = state.children[chr];
			for(int j=0; j<state.output.size(); ++j) {
				ret.add(new int[]{i, state.output.get(j)});
			}
		}

		return ret;
	}


}
