package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1436
 *
 */

public class BOJ_1436 {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num[] = new int[100];
		int cnt = 1;
		
		Arrays.fill(num, 0);
		for(int i = 97; i < 100; i++)
			num[i] = 6;
		
		while(cnt < N) {
			num[99] += 1;
			for(int i = 99; i > 0; i--){
				if(num[i] == 10){
					num[i-1] += 1;
					num[i] = 0;
				}
			}
			for(int i = 0; i < 100; i++){
				if(num[i] == 6 && num[i - 1] == 6 && num[i - 2] == 6){
					cnt += 1;
					break;
				}
			}
		}
		int pointer = 0;
		for(int i = 0; i < 100; i++) {
			if(num[i] != 0) {
				pointer = i;
				break;
			}
		}
		for(int i = pointer; i < 100; i++)
			sb.append(num[i]);
		System.out.println(sb);			
	}
}
