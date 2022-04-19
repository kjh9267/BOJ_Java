package dynamic_programming;

// https://www.acmicpc.net/problem/2156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2156 {

    private static final int LIMIT = 3;

    private static int NOT_DRINK = -1;

    private static int[] wine;

    private static int[][] dp;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        wine = new int[N + 1];

        for (int location = 1; location <= N; location++) {
            wine[location] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1][LIMIT];

        for (int location = 0; location <= N; location++) {
            Arrays.fill(dp[location], NOT_DRINK);
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int location, int count) {
        if (location + 1 > N) {
            return 0;
        }
        if (dp[location][count] != NOT_DRINK) {
            return dp[location][count];
        }

        dp[location][count] = 0;

        if (count + 1 < LIMIT) {
            dp[location][count] = Math.max(dp[location][count], dfs(location + 1, count + 1) + wine[location + 1]);
        }
        dp[location][count] = Math.max(dp[location][count], dfs(location + 1, 0));

        return dp[location][count];
    }
}
