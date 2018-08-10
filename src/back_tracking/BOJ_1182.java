package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	
	public static int cnt = 0, N, S, sum = 0;
	public static int[] nums;
	
	public static void dfs(int cur) {
		if(cur == N) {
			return;
		}
		
		if(sum + nums[cur] == S) {
			cnt++;
		}
		
		dfs(cur + 1);
		
		sum += nums[cur];
		dfs(cur + 1);
		
		sum -= nums[cur];
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(cnt);
	}
}
