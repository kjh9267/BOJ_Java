package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1300 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		System.out.println(binary_search(N,K));
	}
	
	public static int binary_search(int N, int K) {
		int left = 1;
		int right = K;
		int res = 1;
		
		while(left <= right) {
			int cnt = 0;
			int mid = (left + right) >> 1;
			for(int i = 1; i < N + 1; i++)
				cnt += Math.min(mid/i, N);
			if(cnt >= K) {
				res = mid;
				right = mid - 1;
			}
			else
				left = mid + 1;
		}
		return res;
	}
}
