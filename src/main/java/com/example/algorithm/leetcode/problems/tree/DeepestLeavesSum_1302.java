package com.example.algorithm.leetcode.problems.tree;

import java.util.*;

public class DeepestLeavesSum_1302 {
	// using BFS(Level Traversal)
	static Queue<TreeNode> queue;

	public int deepestLeavesSum_bfs(TreeNode root) {
		queue = new LinkedList<TreeNode>();
		queue.offer(root);

		int last = 0;
		while(!queue.isEmpty()){
			// initialize
			last = 0;

			int len = queue.size();
			for(int i=0; i<len ; ++i){
				TreeNode node = queue.poll();
				last += node.val;

				if(node.left != null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
		}

		return last;

	}

	// using DFS
	int maxHeight = Integer.MIN_VALUE;
	int height = 0;
	int maxSum = 0;
	public int deepestLeavesSum_dfs(TreeNode root) {
		inorderDFS(root,height);
		return maxSum;
	}
	public void inorderDFS(TreeNode root, int height){
		if(root == null) return;
		height += 1;
		inorderDFS(root.left,height);
		if(root.left == null && root.right==null){ // Node is leaf
			if(maxHeight < height){
				maxHeight = height;
				maxSum = root.val;
			}
			else if(maxHeight == height){
				maxSum = maxSum + root.val;
			}
		}
		inorderDFS(root.right,height);
	}
}
