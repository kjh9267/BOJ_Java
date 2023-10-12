package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1786
 *
 */

public class BOJ_1786 {

	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] data = br.readLine()
				.toCharArray();
		char[] word = br.readLine()
				.toCharArray();

		int N = data.length;
		int M = word.length;
		int[] fix = new int[M];

		init(M, word, fix);
		int count = kmp(data, word, fix, N, M, sb);
		
		System.out.println(count);
		System.out.println(sb);
	}

	private static void init(int M, char[] word, int[] fix) {
		int start = 1;
		int matched = 0;

		while (start + matched < M) {
			if (word[matched] == word[start + matched]) {
				matched += 1;
				fix[start + matched - 1] = matched;
			}
			else if (matched == 0) {
				start += 1;
			}
			else {
				start += matched - fix[matched - 1];
				matched = fix[matched - 1];
			}
		}
	}

	private static int kmp(char[] data, char[] word, int[] fix, int N, int M, StringBuilder sb) {
		int count = 0;
		int start = 0;
		int matched = 0;

		while (start + matched < N) {
			if (matched < M && data[start + matched] == word[matched]) {
				matched += 1;
				if (matched == M) {
					sb.append(start + 1)
							.append(SPACE);
					count += 1;
				}
			}
			else if (matched == 0) {
				start += 1;
			}
			else {
				start += matched - fix[matched - 1];
				matched = fix[matched - 1];
			}
		}

		return count;
	}
}
