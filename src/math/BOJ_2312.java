package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2312
 *
 */

public class BOJ_2312 {
	public static int[] cnt;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			cnt = new int[N + 1];
			solve(N);
			for(int i = 0; i < N + 1; i++) {
				if(cnt[i] != 0)
					sb.append(i).append(' ').append(cnt[i]).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void solve(int N) {
		for(int i = 2; N > 1; i++) {
			while(N % i == 0) {
				cnt[i] += 1;
				N /= i;
			}
		}
	}
}
