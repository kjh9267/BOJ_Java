package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] homes = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(homes);
		System.out.println(binary_search(N, C, homes));
	}
	
	public static int binary_search(int N, int C, int[] homes) {
		int res = 0;
		int left = 1;
		int right = homes[N] - homes[1];
		
		while(left <= right) {
			int mid = (left + right) >> 1;
			int cnt = 1;
			int prev = 1;
			
			for(int i = 2; i < N + 1; i++) {
				if (homes[i] - homes[prev] >= mid) {
					cnt += 1;
					prev = i;
				}
			}

			if(cnt >= C) {
				left = mid + 1;
				res = mid;
			}
			else {
				right = mid - 1;
			}
		}
		return res;
	}
}
