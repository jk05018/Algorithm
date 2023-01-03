package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

public class PalindromePartitioning_131 {
	static List<List<String>> answers;
	static int N;

	public List<List<String>> partition(String s) {
		// init Settings
		answers = new ArrayList<>();
		N = s.length();

		part(new ArrayList<String>(), s,0);

		return answers;
	}

	public void part(List<String> answer, String s, int index){
		// base case
		if(index == N){
			answers.add(new ArrayList<String>(answer));
			return;
		}

		// solve
		for(int next = 1; index + next <= N ; ++next){
			String str = s.substring(index, index+next);
			if(isPalindrome(str)){
				answer.add(str);
				part(answer, s, index+next);
				answer.remove(answer.size()-1);
			}
		}
	}

	public boolean isPalindrome(String str){
		return str.equals(new StringBuilder(str).reverse().toString());
	}
}
