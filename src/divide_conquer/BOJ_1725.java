package divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1725


public class BOJ_1725 {
	public static int[] data;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		data = new int[N];
			
		for(int i = 0; i < N; i++)
			data[i] = Integer.parseInt(br.readLine());
			
		System.out.println(solve(0, N));
	}
	
	public static long solve(int start, int end) {
		if(start == end - 1)
			return data[start];
		
		int mid = (start + end) / 2;
		long res = Math.max(solve(start, mid), solve(mid, end));
		
		int left = mid;
		int right = mid;
		int height = data[mid];

		while(start < left || right < end - 1) {
			int leftHeight = start != left ? Math.min(height, data[left - 1]) : -1;
			int rightHeight = right != end - 1 ? Math.min(height, data[right + 1]) : -1;

			if(leftHeight > rightHeight) {
				height = leftHeight;
				left -= 1;
			}
			else {
				height = rightHeight;
				right += 1;
			}
			res = Math.max(res, (right - left + 1) * height);
		}
		return res;
	}
}
