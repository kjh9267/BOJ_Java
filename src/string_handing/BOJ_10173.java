package string_handing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/10173
 *
 */

public class BOJ_10173 {
	public static final String END = "EOI", TARGET = "NEMO";
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringBuilder line = new StringBuilder(br.readLine());
			if(line.toString().equals(END))
				break;
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if('a' <= c && c <= 'z')
					line.replace(i, i + 1, (char) (c - 32) + "");
			}
			if(line.toString().contains(TARGET))
				sb.append("Found").append('\n');
			else
				sb.append("Missing").append('\n');
		}
		System.out.println(sb);
	}
}
