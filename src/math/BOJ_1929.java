package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1929
 *
 */

public class BOJ_1929 {
	private static final int MAX = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		double limit = Math.sqrt(MAX);
		
		for(int i = 2; i < limit; i++)
			if(isPrime[i])
				for(int j = i * 2; j < MAX; j += i)
					isPrime[j] = false;
		
		for(int i = M; i <= N; i++)
			if(isPrime[i])
				sb.append(i).append('\n');
		
		System.out.println(sb);
	}
}
