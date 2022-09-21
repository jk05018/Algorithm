package com.example.algorithm.jongmanbook.backtracking;

import java.util.Scanner;

// input
/*
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
*/
public class ClockSync {
	static int CLOCKS = 16;
	static int SWITCHES = 10;
	static int INF = 99999;

	static String[] linked = {
		"xxx.............",
		"...x...x.x.x....",
		"....x.....x...xx",
		"x...xxxx........",
		"......xxx.x.x...",
		"x.x...........xx",
		"...x..........xx",
		"....xx.x......xx",
		".xxxxx..........",
		"...xxx...x...x.."
	};

	public int solve(int[] clocks, int swtch) {
		if (swtch == SWITCHES)
			return isAligned(clocks) ? 0 : INF;

		int ret = INF;
		for (int cnt = 0; cnt < 4; ++cnt) {
			ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
			push(clocks, swtch);
		}

		return ret;

	}

	private void push(final int[] clocks, final int swtch) {
		for (int i = 0; i < CLOCKS; ++i) {
			if (linked[swtch].charAt(i) == 'x') {
				clocks[i] += 3;
			}
			if (clocks[i] == 15) {
				clocks[i] = 3;
			}
		}
	}

	// 시계가 전부 정렬되어 있는지 확인하는 메서드
	public boolean isAligned(int[] clocks) {
		for (int clock : clocks) {
			if (clock != 12) {
				return false;
			}
		}

		return true;
	}
}
