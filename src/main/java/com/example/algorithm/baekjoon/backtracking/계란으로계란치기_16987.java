package com.example.algorithm.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 계란으로계란치기_16987 {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		List<Egg> eggs = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			String[] input = br.readLine().split(" ");
			eggs.add(new Egg(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}

		if(N == 1){
			System.out.println("0");
		}else{
			System.out.println(breakEgg(eggs, 0));
		}
	}

	public static int breakEgg(List<Egg> eggs, int index) {
		// base case
		if (index == N) {
			return (int)eggs.stream().filter(Egg::isBroken).count();
		}

		int ret = -1;

		for (int i = 0; i < N; ++i) {
			if(index == i){
				continue;
			}
			Egg me = eggs.get(index);
			Egg rival = eggs.get(i);

			if (me.isBroken() || rival.isBroken()) {
				ret = Math.max(ret, breakEgg(eggs, index + 1));
			} else {
				me.dur -= rival.wei;
				rival.dur -= me.wei;

				ret = Math.max(ret, breakEgg(eggs, index + 1));

				me.dur += rival.wei;
				rival.dur += me.wei;
			}
		}

		return ret;

	}
}

class Egg {
	int dur;
	int wei;

	Egg(int dur, int wei) {
		this.dur = dur;
		this.wei = wei;
	}

	boolean isBroken() {
		return this.dur <= 0;
	}
}

