package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2581 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		boolean[] isPrime = new boolean[10_001];
		
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		
		for(int i = 2; i < 10001; i ++)
			if(isPrime[i])
				for(int j = i*2; j < 10001; j += i)
					isPrime[j] = false;
		
		int min = N + 1;
		int sum = 0;
		for(int i = M; i < N + 1; i++)
			if(isPrime[i]) {
				if(min > i)
					min = i;
				sum += i;
			}
		
		System.out.println(min == N + 1 ? -1 : sum + "\n" + min);
	}
}
