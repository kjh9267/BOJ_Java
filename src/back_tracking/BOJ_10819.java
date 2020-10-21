package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/10819
 *
 */

public class BOJ_10819 {
	private static int res;
	private static int N;
	private static boolean[] visited;
	private static int[] data;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new int[N];
		for(int i = 0; i < N; i++)
			data[i] = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		dfs(0, new int[N]);
		System.out.println(res);
	}
	
	private static void dfs(int depth, int[] nums) {
		if(depth == N) {
			res = Math.max(res, check(nums));
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			nums[depth] = data[i];
			dfs(depth + 1, nums);
			visited[i] = false;
		}
	}
	
	private static int check(int[] nums) {
		int sum = 0;
		for(int i = 0; i < N - 1; i++)
			sum += Math.abs(nums[i] - nums[i + 1]);
		return sum;
	}
}
