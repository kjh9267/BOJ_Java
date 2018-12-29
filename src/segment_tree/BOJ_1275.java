package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1275 {
	public static long[] tree;
	public static long[] nums;
	public static int N, Q;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(Math.log(N)/Math.log(2));
		int size = (int) Math.pow(2, height + 1);
		tree = new long[size];
		nums = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		init(1,1,N);
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			sb.append(sum(1,1,N,x,y)).append("\n");
			long diff = b - nums[a];
			nums[a] = b;
			update(1,1,N,a,diff);
		}
		System.out.println(sb.toString());
	}
	
	public static long init(int node, int start, int end) {
		if(start == end) return tree[node] = nums[start];
		int mid = (start + end)/2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	public static void update(int node, int start, int end, int index, long diff) {
		if(!(start <= index && index <= end)) return;
		tree[node] += diff;
		if(start != end) {
			int mid = (start + end)/2;
			update(node * 2, start, mid, index, diff);
			update(node * 2 + 1, mid + 1, end, index, diff);
		}
	}
	
	public static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		int mid = (start + end)/2;
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
}
