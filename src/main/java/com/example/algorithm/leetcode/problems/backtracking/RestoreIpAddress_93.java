package com.example.algorithm.leetcode.problems.backtracking;

import java.util.*;

public class RestoreIpAddress_93 {
	static List<String> answer;
	static int N;

	public List<String> restoreIpAddresses(String s) {
		// init Settings
		answer = new ArrayList<>();
		N = s.length();

		solve(new ArrayList<>(), s, 0);

		return answer;

	}

	public void solve(List<String> addresses, String s, int index) {
		if (addresses.size() >= 4) {
			if (index == N) {
				answer.add(toIpAddress(addresses));
				return;
			}
			return;
		}

		for (int plus = 1; plus <= 3; ++plus) {
			if (index + plus <= N && satisfy(s, index, index + plus)) {
				addresses.add(s.substring(index, index + plus));
				solve(addresses, s, index + plus);
				addresses.remove(addresses.size() - 1);
			}
		}
	}

	public boolean satisfy(String s, int start, int end) {
		String num = s.substring(start, end);

		if (num.equals("0")) {
			return true;
		}

		// num should not start with '0'
		if (num.startsWith("0")) {
			return false;
		}

		if (Integer.parseInt(num) > 255) {
			return false;
		}

		return true;
	}

	public String toIpAddress(List<String> addresses) {
		StringBuilder builder = new StringBuilder(addresses.get(0));

		for (int i = 1; i < addresses.size(); ++i) {
			builder.append("." + addresses.get(i));
		}

		return builder.toString();
	}
}
