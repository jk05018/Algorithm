package com.example.algorithm.leetcode.tree;
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
public class ConstructBinarySearchPreOrderTraversal_1008 {

	// by using slice
	public TreeNode bstFromPreorder(int[] preorder) {
		// base case
		if(preorder.length == 0)
			return null;

		TreeNode node = new TreeNode(preorder[0]);

		int index =  preorder.length;;
		for(int i=1; i<preorder.length; ++i){
			if(preorder[i] > node.val){
				index = i;
				break;
			}
		}

		node.left = bstFromPreorder(slice(preorder,1,index));
		node.right = bstFromPreorder(slice(preorder,index,preorder.length));

		return node;
	}

	private int[] slice(int[] arr, int start, int end){
		int[] newArr = new int[end - start];

		for(int i = start; i < end; ++i){
			newArr[i - start] = arr[i];
		}

		return newArr;
	}


}
