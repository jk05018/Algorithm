package com.example.algorithm.jongmanbook.backtracking;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ClockSyncTest {

	@ParameterizedTest
	@MethodSource("generateData")
	void test(int[] clocks, int answer) {
		ClockSync solution = new ClockSync();

		assertThat(solution.solve(clocks, 0)).isEqualTo(answer);

	}

	static Stream<Arguments> generateData() {
		return Stream.of(
			Arguments.of(new int[] {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}, 2),
			Arguments.of(new int[] {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6}, 9)
		);
	}
}
