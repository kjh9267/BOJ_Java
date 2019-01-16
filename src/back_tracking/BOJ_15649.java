package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {
	public static int[] nums;
	public static int N, M;
	public static StringBuilder sb;
	public static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = i + 1;
		}

		dfs(0, 0, "");
		System.out.print(sb);
	}
	
	public static void dfs(int cur, int depth, String s) {
		if(depth == M) {
			sb.append(s);
			sb.append('\n');
			return;
		}
		
		if(cur == N)
			return;
		
		for(int i = 0; i < N; i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			dfs(i, depth + 1, s + nums[i] + " ");
			visited[i] = false;
		}
	}
}
