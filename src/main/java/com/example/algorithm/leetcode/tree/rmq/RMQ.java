package com.example.algorithm.leetcode.tree.rmq;

/*
배열의 구간 최소 쿼리(RMQ) 문제를 해결하는 구간 트리위 초기화
*/
public class RMQ {
	int n;
	int[] rangeMin;

	RMQ(int[] arr) {
		n = arr.length;
		rangeMin = new int[4 * n];
		init(arr, 0, n - 1, 1);
	}

	// 배열하면 slice 사용하는 것이 아니라 이렇게 left, right를 사용하는 것도 연습해야 한다.
	int init(int[] arr, int left, int right, int node) {
		// base case
		if (left == right) {
			return rangeMin[node] = arr[left];
		}

		int mid = left + (right - left) / 2;
		int leftMin = init(arr, left, mid, node * 2);
		int rightMin = init(arr, mid + 1, right, node * 2 + 1);

		return rangeMin[node] = Math.min(leftMin, rightMin);
	}

	int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// 두 구간이 겹치지 않으면 아주 큰 값을 반환한다. : 무시됨
		if (right < nodeLeft || left > nodeRight) {
			return Integer.MAX_VALUE;
		}

		// node가 표현하는 범위가 arr[left..right]에 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {
			return rangeMin[node];
		}

		// 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return Math.min(query(left, right, node * 2, nodeLeft, mid),
			query(left, right, node * 2 + 1, mid + 1, nodeRight));

	}

	int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {
		// index가 노드가 표현하는 구간과 상관 없는 경우엔 무시한다.
		if (index < nodeLeft || nodeRight < index) {
			return rangeMin[node];
		}

		// 트리의 리프까지 내려온 경우
		if (nodeLeft == nodeRight) {
			return rangeMin[node] = newValue;
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

		return rangeMin[node] = Math.min(
			update(index, newValue, node * 2, nodeLeft, mid),
			update(index, newValue, node * 2 + 1, mid + 1, nodeRight)
		);
	}

	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}
}
