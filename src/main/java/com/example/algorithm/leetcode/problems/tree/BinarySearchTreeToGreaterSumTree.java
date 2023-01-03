package com.example.algorithm.leetcode.problems.tree;

public class BinarySearchTreeToGreaterSumTree {
	static int pre;

	public TreeNode bstToGst(TreeNode root) {
		pre = 0;
		traverse(root);
		return root;
	}

	public void traverse(TreeNode root) {
		// revserse inOrder traverse
		if (root.right != null)
			traverse(root.right);
		pre = root.val = pre + root.val;
		if (root.left != null)
			traverse(root.left);
	}
}
