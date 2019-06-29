import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/11055
 *
 */

public class Test {
	public static int[] sequence, dp, res;
	public static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sequence = new int[N];
		dp = new int[N];
		int res = 0;
		
		Arrays.fill(dp, -1);
		for(int i = 0; i < N; i++)
			sequence[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
			res = Math.max(res, solve(i));
		
		System.out.println(res);
	}
	
	public static int solve(int start) {
		if(dp[start] != -1)
			return dp[start];
		
		dp[start] = 1;
		for(int next = start + 1; next < N; next++)
			if(sequence[start] < sequence[next]) {
				dp[start] = Math.max(dp[start], solve(next) + 1);
				res[next]	
			}

		return dp[start];
	}
}
