package com.example.algorithm.leetcode.problems.tree;

public class CountNodesEqualToAverageOfSubtree_2265 {
	static int answer;

	public int averageOfSubtree(TreeNode root) {
		answer = 0;
		calculate(root);

		return answer;
	}

	public int[] calculate(TreeNode node) {
		// base case
		// 필요한 정보가 2개 일 때는 배열로 반환해 보도록 하자
		if (node == null) {
			return new int[] {0, 0};
		}

		int[] left = calculate(node.left);
		int[] right = calculate(node.right);

		int count = left[0] + right[0] + 1;
		int sum = left[1] + right[1] + node.val;

		if (sum / count == node.val) {
			++answer;
		}

		return new int[] {count, sum};

	}
}
