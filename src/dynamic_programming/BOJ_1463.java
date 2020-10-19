package dynamic_programming;

// https://www.acmicpc.net/problem/1463

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1463 {
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        Arrays.fill(dp, MAX);

        System.out.println(dfs(N, 0, dp));
    }

    private static int dfs(int cur, int depth, int[] dp) {
        if (cur < 1) {
            return MAX;
        }
        if (cur == 1) {
            return depth;
        }
        if (dp[cur] != MAX && depth >= dp[cur]) {
            return dp[cur];
        }

        if (cur % 3 == 0) {
            dp[cur] = Math.min(dp[cur], dfs(cur / 3, depth + 1, dp));
        }
        if (cur % 2 == 0) {
            dp[cur] = Math.min(dp[cur], dfs(cur / 2, depth + 1, dp));
        }
        dp[cur] = Math.min(dp[cur], dfs(cur - 1, depth + 1, dp));

        return dp[cur];
    }
}
