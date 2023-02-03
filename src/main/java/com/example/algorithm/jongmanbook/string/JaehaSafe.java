import java.util.*;
import java.io.*;

public class JaehaSafe {
	static int T, N;

	public static void main(String[] args) throws IOException {
		// take input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			int result = solve(br);
			System.out.println(String.format("%d", result));
		}
	}

	public static int solve(BufferedReader br) throws IOException {
		// take inputs
		N = Integer.parseInt(br.readLine());

		String dial = br.readLine().trim();
		int count = 0;

		for(int i=0; i<N; ++i) {
			String pass = br.readLine().trim();
			if(i % 2 != 0) {
				count += search(dial, pass);
			} else {
				count += search(pass, dial);
			}

			dial = new String(pass);
		}

		return count;
	}

	private static int search(String origin, String target){
		return kmpSearch(origin + origin, target);
	}

	private static int kmpSearch(String A, String B) {
		int a = A.length(), b = B.length();
		int[] pi = getPartialMatch(B);

		int begin = 0, matched = 0;
		while(begin <= a - b) {
			if(matched < b && A.charAt(begin + matched) == B.charAt(matched)) {
				++matched;
				if(matched == b) return begin;
			} else {
				if(matched == 0) ++begin;
				else {
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return -1;
	}

	private static int[] getPartialMatch(String B) {
		int b = B.length();
		int[] pi = new int[b];

		int begin = 1, matched = 0;
		while(begin + matched < b) {
			if(B.charAt(begin + matched) == B.charAt(matched)) {
				++matched;
				pi[begin + matched - 1] = matched;
			} else {
				if(matched == 0) ++begin;
				else {
					begin += matched + pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return pi;
	}


}
