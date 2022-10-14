package com.example.algorithm.leetcode.tree;

public class SumOfNodesWithEvenValuedGrandParent_1315 {
	static int sum;

	public int sumEvenGrandparent(TreeNode root) {
		sum = 0;
		traverse(root, null, null);
		return sum;
	}

	public void traverse(TreeNode node, TreeNode parent, TreeNode grandParent) {
		// base case
		if (node == null) {
			return;
		}

		if (grandParent != null && grandParent.val % 2 == 0) {
			sum += node.val;
		}

		traverse(node.left, node, parent);
		traverse(node.right, node, parent);
	}
}
