package string_handing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ_10809 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count[] = new int[26];
		Arrays.fill(count,-1);
		int cnt = -1;
		
		for(char word: br.readLine().toCharArray()) {
			cnt++;
			if(count[word - 97] == -1) {
				count[word - 97] = cnt;
			}
		}
		for(int i : count) {
			System.out.print(i + " ");
		}
	}
} 