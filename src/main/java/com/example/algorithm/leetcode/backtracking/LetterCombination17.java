package com.example.algorithm.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombination17 {
	static final String[] ALPHAS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	static List<String> answer;

	// my solution
	public List<String> letterCombinations(String digits) {
		answer = new ArrayList<>();
		choose(0, "", digits);
		return answer;
	}

	public void choose(int next, String ans, String digits){
		// 기저 사례
		if(next == digits.length()){

			if(ans != "")
				answer.add(ans);
			return;
		}

		int num = digits.charAt(next) - '0';

		for(int i=0; i<ALPHAS[num].length() ; ++i){
			choose(next + 1, ans + ALPHAS[num].charAt(i) , digits);
		}
	}
}
