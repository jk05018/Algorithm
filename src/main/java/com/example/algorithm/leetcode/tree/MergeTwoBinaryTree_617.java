package com.example.algorithm.leetcode.tree;

public class MergeTwoBinaryTree_617 {
	// Tree 아직 많이 약하다. 분발하자
	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return null;
		}

		if (root1 == null)
			return root2;
		if (root2 == null)
			return root1;

		int value = root1.val + root2.val;

		return new TreeNode(value, mergeTrees(root1.left, root2.left), mergeTrees(root1.right, root2.right));

	}
}
