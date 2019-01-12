package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_4948 {
	public static final int MAX = 123456 * 2;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] isPrime = new boolean[MAX + 1];
		int[] res = new int[MAX + 1];
		
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		
		for(int i = 2; i <= MAX; i++)
			if(isPrime[i])
				for(int j = i*2; j <= MAX; j += i)
					isPrime[j] = false;
		
		for(int i = 1; i <= MAX; i++) {
			if(isPrime[i])
				res[i] = res[i-1] + 1;
			else
				res[i] = res[i-1];
		}
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;

			sb.append(res[2*N] - res[N]).append('\n');
		}
		System.out.print(sb);
	}
}
