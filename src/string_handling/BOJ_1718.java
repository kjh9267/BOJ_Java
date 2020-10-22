package string_handling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1718 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String normal = br.readLine();
		String password = br.readLine();
		
		System.out.println(convert(normal, password));
	}
	
	private static StringBuilder convert(String normal, String password) {
		StringBuilder res = new StringBuilder();
		int len = normal.length();
		int plen = password.length();
		int index = 0;
		
		for(int i = 0; i < len; i++) {
			char x = normal.charAt(i);
			char y = password.charAt(index);
			
			if(x == ' ') {
				res.append(' ');
				index = next_index(index, plen);
				continue;
			}
			
			int c = x - (y - 96);
			
			if (c < 97)
				c = c - 97 + 123;
				
			res.append((char)c);
			index = next_index(index, plen);
		}
		return res;
	}

	private static int next_index(int index, int plen) {
		return ++index == plen ? index - plen : index;
	}
}
