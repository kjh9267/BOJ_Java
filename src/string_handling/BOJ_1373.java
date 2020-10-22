package string_handling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1373
 *
 */

public class BOJ_1373 {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String num = br.readLine();
		int len = num.length();
		int remainder = len % 3;
		
		if(remainder == 0) {
			for(int i = 0; i < len; i += 3) {
				String temp = num.substring(i,i+3);
				int a = 0;
				a += (temp.charAt(2) - '0');
				a += 2 * (temp.charAt(1) - '0');
				a += 4 * (temp.charAt(0) - '0');
				sb.append(a);
			}
		}
		else if(remainder == 1) {
			sb.append(num.charAt(0));
			for(int i = 1; i < len; i += 3) {
				String temp = num.substring(i,i+3);
				int a = 0;
				a += (temp.charAt(2) - '0');
				a += 2 * (temp.charAt(1) - '0');
				a += 4 * (temp.charAt(0) - '0');
				sb.append(a);
			}
		}
		else {
			int a = 0;
			a += 2 * (num.charAt(0) - '0');
			a += num.charAt(1) - '0';
			sb.append(a);
			for(int i = 2; i < len; i += 3) {
				String temp = num.substring(i,i+3);
				a = 0;
				a += (temp.charAt(2) - '0');
				a += 2 * (temp.charAt(1) - '0');
				a += 4 * (temp.charAt(0) - '0');
				sb.append(a);
			}
		}
		System.out.println(sb);
	}
}
