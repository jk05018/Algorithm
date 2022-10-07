package com.example.algorithm.leetcode.dp;

import java.util.*;

public class JumpGame_55 {
	static int[] cache;
	static int N;

	public boolean canJump(int[] nums) {
		// init Settings
		N = nums.length;
		cache = new int[N];

		Arrays.fill(cache,-1);

		// solve
		return jump(nums,0);
	}

	boolean jump(int[] nums, int index){
		// base case
		if(index >= N-1){
			return true;
		}

		// check cache
		if(cache[index] != -1){
			if(cache[index] == 1){
				return true;
			}
			return false;
		}

		// solve
		int len = nums[index];
		if(len == 0) {
			cache[index] = 0;
			return false;
		}

		for(int add = 1; add <= len ; ++add){
			if(jump(nums, index + add)){
				cache[index] = 1;
				return true;
			}
		}

		cache[index] = 0;
		return false;
	}
}
