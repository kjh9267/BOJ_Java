package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1964 {
	public static BufferedReader br;
	public static void main(String args[]) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int res = 5;
		
		if(N == 1) {
			System.out.println(5);
		}
		else {
			for(int i = 2 ; i < N+1; i++) {
				res += (i - 1) * 3 + 4;
				res %= 45678;
			}
			System.out.println(res%45678);
		}
	}
}
