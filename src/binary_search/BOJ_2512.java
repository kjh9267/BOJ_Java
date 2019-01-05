package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2512 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		
		for(int i = 1; i < N + 1; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(max < num) max = num;
			arr[i] = num;
		}
		int M = Integer.parseInt(br.readLine());
		
		System.out.println(binary_search(N,M,max,arr));
	}
	
	public static int binary_search(int N, int M, int max, int[] arr) {
		int res = 0;
		int mid = 0;
		int left = 1;
		int right = max;
		
		while(left<= right) {
			int sum = 0;
			mid = (left + right) >> 1;
			
			for(int i = 1; i < N + 1; i++) 
				sum += Math.min(arr[i], mid);
			
			if(sum <= M) {
				left = mid + 1;
				res = mid;
			}
			else
				right = mid - 1;
		}
		return res;
	}
}
