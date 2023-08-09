package dynamic_programming;

// https://www.acmicpc.net/problem/1749

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1749 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N + 1][M + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = init(grid, N, M);

        System.out.println(findMaxSum(dp, N, M));
    }


    private static int[][] init(int[][] grid, int N, int M) {
        int[][] dp = new int[N + 1][M + 1];

        for (int row = 1; row <= N; row++) {
            dp[row][0] = grid[row][0] + dp[row - 1][0];
        }

        for (int col = 1; col <= M; col++) {
            dp[0][col] = grid[0][col] + dp[0][col - 1];
        }

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                dp[row][col] = grid[row][col] + dp[row - 1][col] + dp[row][col - 1] - dp[row - 1][col - 1];
            }
        }

        return dp;
    }

    private static int findMaxSum(int[][] dp, int N, int M) {
        int maxSum = Integer.MIN_VALUE;

        for (int startRow = 1; startRow <= N; startRow++) {
            for (int startCol = 1; startCol <= M; startCol++) {
                for (int endRow = startRow; endRow <= N; endRow++) {
                    for (int endCol = startCol; endCol <= M; endCol++) {
                        int value = dp[endRow][endCol] - dp[startRow - 1][endCol] - dp[endRow][startCol - 1] + dp[startRow - 1][startCol - 1];
                        maxSum = Math.max(maxSum, value);
                    }
                }
            }
        }

        return maxSum;
    }
}
