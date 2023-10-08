package dynamic_programming;

// https://www.acmicpc.net/problem/2293

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        int[][] dp = new int[K + 1][N];

        for (int index = 0 ; index <= K; index++) {
            Arrays.fill(dp[index], -1);
        }

        for (int index = 0; index < N; index++) {
            coins[index] = Integer.parseInt(br.readLine());
        }

        int result = dfs(0, 0, coins, dp, N, K);

        System.out.println(result);
    }

    private static int dfs(int cur, int depth, int[] coins, int[][] dp, int N, int K) {
        if (depth >= K + 1) {
            return 0;
        }
        if (depth == K) {
            return 1;
        }
        if (dp[depth][cur] != -1) {
            return dp[depth][cur];
        }

        dp[depth][cur] = 0;
        for (int next = cur; next < N; next++) {
            dp[depth][cur] += dfs(next,depth + coins[next], coins, dp, N, K);
        }

        return dp[depth][cur];
    }
}
