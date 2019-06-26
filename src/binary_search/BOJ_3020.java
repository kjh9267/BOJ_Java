package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/3020
 *
 */

public class BOJ_3020 {
	public static int N, H, res = Integer.MAX_VALUE, res2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int[] ceiling = new int[N / 2];
		int[] floor = new int[N / 2];
		
		for(int i = 0; i < N / 2; i++) {
			floor[i] = Integer.parseInt(br.readLine());
			ceiling[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(floor);
		Arrays.sort(ceiling);
		
		for(int i = 1; i <= H; i++) {
			int cnt = 0;
			cnt += N / 2 - binarySearch(i, floor);
			cnt += N / 2 - binarySearch(H - i + 1, ceiling);
			if(res > cnt) {
				res = cnt;
				res2 = 1;
			}
			else if(res == cnt)
				res2 += 1;
		}
		
		System.out.println(res + " " + res2);
	}
	public static int binarySearch(int target, int[] data) {
		int lo = -1;
		int hi = N / 2;
		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			if(data[mid] >= target)
				hi = mid;
			else
				lo = mid;
		}
		return hi;
	}
}
