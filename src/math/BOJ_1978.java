package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1978
 *
 */

public class BOJ_1978 {
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		int cnt = 0;
		
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		boolean[] isPrime = new boolean[1001];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;

		for(int i = 2; i < Math.sqrt(1001); i++)
			if(isPrime[i])
				for(int j = i*2; j < 1001; j += i)
					isPrime[j] = false;
		
		for (int i = 0; i < N; i++)
			if(isPrime[nums[i]])
				cnt++;

		System.out.println(cnt);
	}
}
