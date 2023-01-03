package com.example.algorithm.leetcode.problems.tree;

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
Time Complexity : if tree is balanced : O(Nlog(N)) or not O(N^2)
Space Complexity : N? I dont know
 */
public class MaximumBinaryTree_654 {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null)
			return null;

		return traverse(nums);

	}

	private TreeNode traverse(int[] nums) {
		// base case
		if (nums.length == 0)
			return null;

		// find Max Index
		int maxIndex = findMaxIndex(nums);

		TreeNode node = new TreeNode(nums[maxIndex]);

		node.left = traverse(slice(nums, 0, maxIndex));
		node.right = traverse(slice(nums, maxIndex + 1, nums.length));

		return node;

	}

	// slice 없애는 걸로 최적화 가능할 것 같다.
	private int[] slice(int[] nums, int start, int end) {
		int num = end - start;
		int[] arr = new int[num];

		for (int i = start; i < end; ++i) {
			arr[i - start] = nums[i];
		}

		return arr;
	}

	private int findMaxIndex(int[] nums) {
		int max = -1;
		int index = -1;

		for (int i = 0; i < nums.length; ++i) {
			if (max < nums[i]) {
				max = nums[i];
				index = i;
			}
		}

		return index;
	}
}
