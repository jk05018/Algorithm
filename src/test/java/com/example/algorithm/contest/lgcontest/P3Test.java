package com.example.algorithm.contest.lgcontest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class P3Test {

	@ParameterizedTest
	@CsvSource({
		"abc,bcab,2",
		"vxrvip,xrviprvipvxrv,4",
		"abcd,abcdabcd,4",
		"abcd,abcdabcdab,2"
	})
	void name(String reference, String track, int answer) {

		// when
		int result = new P3().solution(reference, track);

		// then
		assertEquals(result,answer);

	}
}
