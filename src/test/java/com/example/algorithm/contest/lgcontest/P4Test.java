package com.example.algorithm.contest.lgcontest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class P4Test {

	@Test
	void test1() {
		// given
		int[][] edges = new int[][]{
			{1,3},
			{1,2},
			{2,4},
			{2,5}
		};

		int[] roots = new int[]{2,3};

		// when
		int[] result = new P4().solutiuon(edges, roots);

		for(int i : result){
			System.out.print(i + " ");
		}

		// then
		assertArrayEquals(result, new int[]{1,2,0,0});
	}

	@Test
	void test2() {
		// given
		int[][] edges = new int[][]{
			{1,2},
			{1,3},
			{2,4}
		};

		int[] roots = new int[]{3,4,1,2};

		// when
		int[] result = new P4().solutiuon(edges, roots);

		// then
		assertArrayEquals(result, new int[]{3,2,2});
	}

	@Test
	void test3() {
		// given
		int[][] edges = new int[][]{
			{1,2},
			{1,3},
			{1,4},
			{2,5},
			{4,6},
			{4,7}
		};

		int[] roots = new int[]{6,5,7};

		// when
		int[] result = new P4().solutiuon(edges, roots);

		for(int i : result){
			System.out.print(i + " ");
		}
		// then
		assertArrayEquals(result, new int[]{2,0,3,2,2,1});
	}
}
