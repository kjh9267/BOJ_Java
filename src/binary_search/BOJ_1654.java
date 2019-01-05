package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K+1];
		int max = 0;
		
		for(int i = 1; i < K + 1; i++) {
			int num = Integer.parseInt(br.readLine());
			if(max < num) max = num;
			arr[i] = num;
		}
		System.out.println(binary_search(N, K, max, arr));
	}
	
	public static long binary_search(int N, int K, int max, int[] arr) {
		long res = 0;
		long left = 1;
		long right = max;
		long mid;

		while(left <= right) {
			int cnt = 0;
			mid = (left + right) >> 1;
			
			for(int i = 1; i < K + 1; i++) {
				cnt += arr[i] / mid;
			}

			if(cnt >= N) {
				left = mid + 1;
				res = mid;
			}
			else
				right = mid - 1;
		}
		return res;
	}
}
