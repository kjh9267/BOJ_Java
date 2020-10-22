package string_handling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/3062
 *
 */

public class BOJ_3062 {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String num = br.readLine();
			StringBuilder reversedNum =  new StringBuilder();
			
			for(int i = num.length() - 1; i >= 0; i--) {
				reversedNum.append(num.charAt(i));
			}
			
			String res = Integer.parseInt(num) + Integer.parseInt(reversedNum.toString()) + "";
			int len = res.length();
			boolean key = true;
			
			for(int i = 0; i < len / 2; i++) {
				if(res.charAt(i) != res.charAt(len - 1 - i)) {
					key = false;
					break;
				}
			}
			
			if(key)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.println(sb);
	}
}
