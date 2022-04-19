package dynamic_programming;

// https://www.acmicpc.net/problem/15988

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15988 {

    private static final int MAX = 1_000_000;

    private static final int MOD = 1_000_000_009;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        long[] dp = new long[MAX + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int number = 4; number <= MAX; number++) {
            dp[number] = (dp[number] + (dp[number - 1] + dp[number - 2]) % MOD) % MOD;
            dp[number] = (dp[number] + dp[number - 3]) % MOD;
        }

        for (int test = 0; test < T; test++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n])
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }
}
