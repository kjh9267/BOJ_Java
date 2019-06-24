package string_handing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/10174
 *
 */

public class BOJ_10174 {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringBuilder line = new StringBuilder(br.readLine());
			int len = line.length();
			boolean key = true;
			
			for(int i = 0; i < len; i++) {
				char c = line.charAt(i);
				if('a' <= c && c <= 'z') {
					line.replace(i, i + 1, (char) (c - 32) + "");
				}
			}

			for(int i = 0; i < len / 2; i++) {
				if(line.charAt(i) != line.charAt(len - 1 - i)) {
					key = false;
					break;
				}
			}
			
			if(key)
				sb.append("Yes\n");
			else
				sb.append("No\n");
		}
		System.out.println(sb);
	}
}
