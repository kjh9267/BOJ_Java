package dynamic_programming;

// https://www.acmicpc.net/problem/4811

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_4811 {
    private static final String NEW_LINE = "\n";
    private static int N;
    private static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            dp = new long[N * 2][N + 1][N + 1];
            sb.append(dfs(0, N, 0))
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static long dfs(int cur, int whole, int half) {
        if (cur == N * 2) {
            return 1;
        }
        if (dp[cur][whole][half] != 0) {
            return dp[cur][whole][half];
        }
        if (whole > 0) {
            dp[cur][whole][half] += dfs(cur + 1, whole - 1, half + 1);
        }
        if (half > 0) {
            dp[cur][whole][half] += dfs(cur + 1, whole, half - 1);
        }

        return dp[cur][whole][half];
    }
}
