import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Naming {
	static int N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine().trim() + br.readLine().trim();

		List<Integer> answer = getPrefixSuffix(input);

		System.out.println(answer.stream()
			.sorted()
			.map(String::valueOf)
			.collect(Collectors.joining(" ")));

	}

	static List<Integer> getPrefixSuffix(String S) {
		int[] pi = getPartialMatch(S);
		List<Integer> answer = new ArrayList<>();

		int k = S.length();

		while(k > 0) {
			answer.add(k);
			k = pi[k-1];
		}

		return answer;
	}

	static int[] getPartialMatch(String S) {
		int s = S.length();
		int[] pi = new int[s];

		int begin = 1, matched = 0;

		while(begin + matched < s) {
			if(S.charAt(begin + matched) == S.charAt(matched)) {
				++matched;
				pi[begin + matched - 1] = matched;
			} else {
				if(matched == 0) {
					++begin;
				} else {
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return pi;
	}
}