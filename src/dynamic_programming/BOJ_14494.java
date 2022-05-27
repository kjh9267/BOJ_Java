package dynamic_programming;

// https://www.acmicpc.net/problem/14494

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14494 {

    private static final int MOD = 1_000_000_007;

    private static final int[][] DIR = {{0, 1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][M];

        int result = dfs(0, 0, dp, N, M);

        System.out.println(result);
    }

    private static int dfs(int row, int col, int[][] dp, int N, int M) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        if (row == N - 1 && col == M - 1) {
            return 1;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dp[row][col] = (dp[row][col] + (dfs(nextRow, nextCol, dp, N, M) % MOD)) % MOD;
        }

        return dp[row][col];
    }
}
