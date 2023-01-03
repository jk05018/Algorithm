package com.example.algorithm.leetcode.problems.tree;

class RangeSumOfBst_938 {
	public int rangeSumBST(TreeNode root, int low, int high) {
		// BST이다!!! 특징을 잘 이용하자
		if (root == null)
			return 0;

		if (root.val < low)
			return rangeSumBST(root.right, low, high);
		if (root.val > high)
			return rangeSumBST(root.left, low, high);

		return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
	}
}
