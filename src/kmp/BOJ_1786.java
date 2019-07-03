package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1786
 *
 */

public class BOJ_1786 {
	public static int[] table;
	public static char[] string, target;
	public static int N, M, cnt;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		string = br.readLine().toCharArray();
		target = br.readLine().toCharArray();

		init();
		kmp();
		
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	public static void init() {
		N = string.length;
		M = target.length;
		table = new int[M];
		int idx = 1;
		int offset = 0;
		while(idx + offset < M) {
			if(target[idx + offset] == target[offset]) {
				offset += 1;
				table[idx + offset - 1] = offset;
			}
			else if(offset == 0)
				idx += 1;
			else {
				idx += offset - table[offset - 1];
				offset = table[offset - 1];
			}
		}
	}
	
	public static void kmp() {
		cnt = 0;
		int idx = 0;
		int offset = 0;
		while(idx <= N - M) {
			if(offset < M && string[idx + offset] == target[offset]) {
				offset += 1;
				if(offset == M) {
					sb.append(idx + 1).append(' ');
					cnt += 1;
				}
			}
			else if(offset == 0)
				idx += 1;
			else {
				idx += offset - table[offset - 1];
				offset = table[offset - 1];
			}
		}
	}
}
