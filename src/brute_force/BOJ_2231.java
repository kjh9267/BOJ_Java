package brute_force;

/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/2231
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2231 {
	public static int solve(int target) {
		int num;
		StringBuilder sb;
		for(int i = 1; i <= 1_000_000; i++) {
			num = i;
			sb = new StringBuilder();
			sb.append(i);
			for(int j = 0; j < sb.length(); j++) {
				num += sb.charAt(j) - '0';
			}
			if(num == target)
				return i;
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(br.readLine());
		System.out.println(solve(target));
	}
}
