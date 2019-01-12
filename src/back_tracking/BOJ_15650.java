package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {
	public static int[] nums, seq;
	public static int N, M, pointer;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		seq = new int[M];
		
		for(int i = 0; i < N; i++) {
			nums[i] = i + 1;
		}

		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int cur) {
		if(pointer == M ) {
			for(int i = 0; i < M; i++)
				sb.append(seq[i] + " ");
			sb.append('\n');
			return;
		}
		
		if(cur == N)
			return;

		seq[pointer] = nums[cur];
		pointer++;
		dfs(cur + 1);
		pointer--;
		dfs(cur + 1);
	}
}
