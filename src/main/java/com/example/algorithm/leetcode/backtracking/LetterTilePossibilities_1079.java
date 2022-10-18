package com.example.algorithm.leetcode.backtracking;

// 시간복잡도 = N!?
/*
Insight
단순히 중복되지 않는 조합을 뽑을 때는 for문 + index를 사용함으로써 중복되지 않는 조합을 뽑을 수는 있었다.
하지만 다음의 경우 단순히 조합을 뽑는게 아니라 ABA와 와 같은 순서도 가능하였다.
따라서 for문 + index는 사용하지 못함

개수에 따라 달라짐
하 어럽다.
 */
public class LetterTilePossibilities_1079 {
	static int[] count;
	static int sum;
	public int numTilePossibilities(String tiles) {
		// init Settings
		count = new int[26];
		sum = 0;
		for(char c : tiles.toCharArray()){
			count[c - 'A']++;
		}

		pick();

		return sum;

	}

	private void pick(){

		for(int i=0; i<26; ++i){
			if(count[i] > 0){
				sum++;
				count[i]--;
				pick();
				count[i]++;
			}
		}
	}
}
