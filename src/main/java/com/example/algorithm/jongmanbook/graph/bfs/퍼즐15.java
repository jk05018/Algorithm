package com.example.algorithm.jongmanbook.graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 퍼즐15 {

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		State start = makeState(br);
		State finish = makeState(br);

		System.out.println(String.format("Minimum distance : %d ", bidirectional(start, finish)));

	}

	private static State makeState(BufferedReader br) throws IOException {
		int idx = 0;
		long state = 0L;
		int vacuum = -1;

		for (int i = 0; i < 4; ++i) {
			String[] inputs = br.readLine().trim().split(" ");
			for (int j = 0; j < 4; ++j) {
				int value = Integer.parseInt(inputs[j]);
				if (value == 0)
					vacuum = idx;
				state = State.set(state, idx++, value);
			}
		}

		return new State(state, vacuum);

	}

	// start에서 finish로 가는 최단 경로의 길이를 반환한다.
	private static int bidirectional(State start, State finish) {
		// 각 정점까지의 최단 경로의 길이를 저장한다
		Map<Long, Integer> c = new HashMap<>();
		// 앞으로 방문할 정점들을 저장한다.
		Queue<State> q = new LinkedList<>();
		// 시작 상태와 목표 상태가 같은 경우는 예외로 처리해야 한다 -> 잘 생각해보면 이유를 알 수 있을 것이다,.
		if (State.isSame(start, finish))
			return 0;

		q.offer(start);
		c.put(start.value, 1);
		q.offer(finish);
		c.put(finish.value, -1);

		while (!q.isEmpty()) {
			State here = q.poll();

			// 인접한 상태들을 검사한다.
			List<State> adjacents = here.getAdjacents();
			for (int i = 0; i < adjacents.size(); ++i) {
				State next = adjacents.get(i);
				if (c.get(next.value) == null) {
					c.put(next.value, incr(c.get(here.value)));
					q.offer(next);
				} else if (sign(c.get(next.value)) != sign(c.get(here.value))) {
					return Math.abs(c.get(next.value)) + Math.abs(c.get(here.value)) - 1;
				}
			}

		}

		return -1;

	}

	// x의 부호를 반환한다.
	static int sign(int x) {
		if (x == 0)
			return 0;
		return x > 0 ? 1 : -1;
	}

	// x의 절댓값을 1 증가시킨다.
	static int incr(int x) {
		if (x < 0)
			return x - 1;
		return x + 1;
	}
}

class State {
	// 64비트 부호 없는 정수를 [0,15] 범의의 정수 16개를 담는 배열로 사용하기
	long value;
	// 현재 비어있는 좌표
	int index;

	public State(long value, int index) {
		this.value = value;
		this.index = index;
	}

	public List<State> getAdjacents() {
		List<State> adjacents = new ArrayList<>();

		for (int i = 0; i < 4; ++i) {
			int result = canMove(index, i);
			if (result != -1) {
				change(index, result);
				adjacents.add(new State(this.value, result));
				change(index, result);
			}
		}

		return adjacents;
	}

	public int canMove(int index, int direction) {
		// up
		if (direction == 0 && index - 4 >= 0) {
			return index - 4;
		}

		// down
		if (direction == 1 && index + 4 < 16) {
			return index + 4;
		}

		// left
		if (direction == 2 && (index - 1) / 4 == (index / 4)) {
			return index - 1;
		}

		// right
		if (direction == 3 && (index + 1) / 4 == (index / 4)) {
			return index + 1;
		}

		return -1;
	}

	private void change(int from, int to) {
		int fromTemp = get(value, from);

		this.value = set(this.value, from, get(value, to));
		this.value = set(this.value, to, fromTemp);
	}

	public static boolean isSame(State a, State b) {
		return a.value == b.value;
	}

	// mask의 index 위치에 쓰인 값을 반환한다.
	public static int get(long mask, int index) {
		return (int)(mask >> (index << 2)) & 15;
	}

	// mask의 index 위치를 value로 바꾼 결과를 반환한다.
	public static long set(long mask, int index, long value) {
		return mask & ~(15L << (index << 2)) | (value << (index << 2));
	}
}
