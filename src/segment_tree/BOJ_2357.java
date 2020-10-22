package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2357 {
	private static int N;
	private static int M;
	private static int[] min_tree;
	private static int[] max_tree;
	private static int[] nums;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(Math.log(N)/Math.log(2));
		int size = (int) Math.pow(2, height + 1);
		min_tree = new int[size];
		max_tree = new int[size];
		nums = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		min_init(1,1,N);
		max_init(1,1,N);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(min(1,1,N,a,b)).append(" ").append(max(1,1,N,a,b)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int min_init(int node, int start, int end) {
		if(start == end) return min_tree[node] = nums[start];
		int mid = (start + end)/2;
		return min_tree[node] = Math.min(min_init(node * 2, start, mid),
				min_init(node * 2 + 1, mid + 1, end));
	}

	private static int max_init(int node, int start, int end) {
		if(start == end) return max_tree[node] = nums[start];
		int mid = (start + end)/2;
		return max_tree[node] = Math.max(max_init(node * 2, start, mid),
				max_init(node * 2 + 1, mid + 1, end));
	}

	private static int min(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return Integer.MAX_VALUE;
		if(left <= start && end <= right) return min_tree[node];
		int mid = (start + end)/2;
		return Math.min(min(node * 2, start, mid, left, right),
				min(node * 2 + 1, mid + 1, end, left, right));
	}

	private static int max(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return Integer.MIN_VALUE;
		if(left <= start && end <= right) return max_tree[node];
		int mid = (start + end)/2;
		return Math.max(max(node * 2, start, mid, left, right), 
				max(node * 2 + 1, mid + 1, end, left, right));
	}
}
