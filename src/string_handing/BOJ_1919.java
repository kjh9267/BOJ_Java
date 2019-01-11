package string_handing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1919 {
	public static void main(String[] args) throws Exception{
		int[] cntA = new int[26];
		int[] cntB = new int[26];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		
		count(cntA,A);
		count(cntB,B);
		
		int res = 0;
		
		for(int i = 0; i < 26; i++)
			res += cntA[i] > cntB[i] ? cntA[i] - cntB[i] : cntB[i] - cntA[i];
		
		System.out.println(res);
	}
	
	public static void count(int[] arr, String s) {
		int len = s.length();
		for(int i = 0; i < len; i++) {
			int index = s.charAt(i) - 'a';
			arr[index] += 1;
		}
	}
}
