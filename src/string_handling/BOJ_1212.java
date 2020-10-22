package string_handling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1212
 *
 */

public class BOJ_1212 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String num = br.readLine();
		int len = num.length();
		int quotient;
		
		int n = num.charAt(0) - '0';
		quotient = n / 4;
		n %= 4;
		if(quotient == 1)
			sb.append(1);
		quotient = n / 2;
		n %= 2;
		if(quotient == 1 || sb.length() == 1)
			sb.append(quotient);
		sb.append(n);
		
		for(int i = 1; i < len; i++) {
			n = num.charAt(i) - '0';
			quotient = n / 4;
			n %= 4;
			sb.append(quotient);
			quotient = n / 2;
			n %= 2;
			sb.append(quotient);
			sb.append(n);
		}
		System.out.println(sb);
	}
}
