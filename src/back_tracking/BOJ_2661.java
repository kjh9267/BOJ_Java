package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2661
 *
 */


public class BOJ_2661 {
	private static String res;
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs(0,"1");
		
		System.out.println(res);
	}
	
	private static boolean dfs(int cur, String s) {
		if(s.length() == N) {
			res = s;
			return true;
		}
		for(int i = 1; i <= 3; i++)
			if(check(s + i))
				if(dfs(i,s + i))
					return true;
		return false;
	}
	
	private static boolean check(String s) {
		int len = s.length();
		int half = len >> 1;

		for(int i = 1; i <= half; i++)
			for(int j = 0; j <= len - i * 2; j++)
				if(s.substring(j, j + i).equals(s.substring(j + i, j + i * 2)))
					return false;
		return true;		
	}
}
