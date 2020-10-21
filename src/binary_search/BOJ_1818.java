package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1818
 *
 */

public class BOJ_1818 {
	private static int[] seq;
	private static int[] tempSeq;
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		seq = new int[N];
		tempSeq = new int[N];
		
		for(int i = 0; i < N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
				
		System.out.println(N - solve());
	}

	private static int solve() {
		int pointer = 0;
		tempSeq[0] = seq[0];
		
		for(int i = 1; i < N; i++) {
		
			if(seq[i] > tempSeq[pointer]) {
				pointer += 1;
				tempSeq[pointer] = seq[i];
			}
			else {
				int idx = binary_search(seq[i], pointer);
				tempSeq[idx] = seq[i];
			}
		}
		return pointer + 1;
	}

	private static int binary_search(int target, int hi) {
		int lo = -1;

		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			
			if(tempSeq[mid] >= target)
				hi = mid;
			else
				lo = mid;
		}
		return hi;
	}

}
