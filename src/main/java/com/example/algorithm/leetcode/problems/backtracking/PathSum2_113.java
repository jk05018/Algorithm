package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

public class PathSum2_113 {
	static List<List<Integer>> answers;

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		// init Settings
		answers = new ArrayList<>();

		solve(root, targetSum, new ArrayList<Integer>(), 0);

		return answers;
	}

	public void solve(TreeNode root, int target, List<Integer> answer, int sum) {
		// base case
		if (root == null) {
			return;
		}

		// if arrived at leaf node
		if (root.left == null && root.right == null) {
			if (sum + root.val == target) {
				answer.add(root.val);
				answers.add(new ArrayList<Integer>(answer));
				answer.remove(answer.size() - 1);
			}
			return;
		}

		answer.add(root.val);
		if (root.left != null) {
			solve(root.left, target, answer, sum + root.val);
		}
		if (root.right != null) {
			solve(root.right, target, answer, sum + root.val);
		}
		answer.remove(answer.size() - 1);

	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
