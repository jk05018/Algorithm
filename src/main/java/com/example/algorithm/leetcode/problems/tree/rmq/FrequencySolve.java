package com.example.algorithm.leetcode.problems.tree.rmq;

public class FrequencySolve {
	// 왼쪽 부분 구간의 계산 결과 a, 오른쪽 부분 구간의 계산 결과 b를 합친다.
	RangeResult merge(RangeResult a, RangeResult b) {
		RangeResult ret = new RangeResult();
		// 구간의 크기는 쉽게 계산 가능
		ret.size = a.size + b.size;

		ret.leftNumber = a.leftNumber;
		ret.leftFreq = a.leftFreq;
		if (a.size == a.leftFreq && a.leftNumber == b.leftNumber) {
			ret.leftFreq += b.leftFreq;
		}

		ret.rightNumber = b.rightNumber;
		ret.rightFreq = b.rightFreq;
		if (b.size == b.rightFreq && a.rightNumber == b.rightNumber) {
			ret.rightFreq += a.rightFreq;
		}

		ret.mostFrequent = Math.max(a.mostFrequent, b.mostFrequent);

		if (a.rightNumber == b.leftNumber) {
			ret.mostFrequent = Math.max(ret.mostFrequent, a.rightFreq + b.leftFreq);
		}

		return ret;

	}

}

/*
숫자의 최대 출현 회수 문제의 두 개의 답 합치기
*/
class RangeResult {
	int size;
	int mostFrequent;
	int leftNumber, leftFreq;
	int rightNumber, rightFreq;

}


