package com.example.algorithm.leetcode.problems.tree;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
Time Complexity : O(N)?
Space Complexity : O(N)?
 */

public class BalanceBinarySearchTree_1382 {
	// TreeNode -> Integer -> TreeNode 때문에 시간이 오래 걸림 수정 가능
	List<Integer> inorder;

	public TreeNode balanceBST(TreeNode root) {
		inorder = new ArrayList<>();
		inOrder(root);

		return makeTree(0, inorder.size() - 1);
	}

	public void inOrder(TreeNode node) {
		if (node == null)
			return;

		inOrder(node.left);
		inorder.add(node.val);
		inOrder(node.right);
	}

	public TreeNode makeTree(int left, int right) {
		if (left > right) {
			return null;
		}

		int mid = left + (right - left) / 2;

		TreeNode node = new TreeNode(inorder.get(mid));

		node.left = makeTree(left, mid - 1);
		node.right = makeTree(mid + 1, right);

		return node;
	}
}
