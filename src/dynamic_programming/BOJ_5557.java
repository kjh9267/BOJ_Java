package dynamic_programming;

// https://www.acmicpc.net/problem/5557

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5557 {
    private static int N;
    private static int[] data;
    private static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N + 1];
        dp = new long[N + 1][21];

        for(int idx = 1; idx <= N; idx++) {
            Arrays.fill(dp[idx], -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx = 1; idx <= N; idx++) {
            data[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(2, data[1]));
    }

    private static long dfs(int cur, int sum) {
        if(cur == N)
            return sum == data[N] ? 1 : 0;
        if(dp[cur][sum] != -1)
            return dp[cur][sum];
        dp[cur][sum] = 0;
        if(sum + data[cur] <= 20)
            dp[cur][sum] += dfs(cur + 1, sum + data[cur]);
        if(sum - data[cur] >= 0)
            dp[cur][sum] += dfs(cur + 1, sum - data[cur]);
        return dp[cur][sum];
    }
}
