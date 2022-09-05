package com.example.algorithm.leetcode.algo;

public class SearchInsertPosition {

	// using binary search
	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int answer = -1;

		while(left <= right){
			int mid = left + (right - left) / 2;

			if(nums[mid] == target){
				answer = mid;
			}

			if(nums[mid] < target){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}

		return left;
	}
}
