package dynamic_programming;

// https://www.acmicpc.net/problem/2186

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2186 {

    private static final int NOT_VISITED = -1;

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N;

    private static int M;

    private static int K;

    private static String target;

    private static int targetSize;

    private static char[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        target = br.readLine();
        targetSize = target.length();

        int[][][] dp = new int[N][M][targetSize];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                Arrays.fill(dp[row][col], NOT_VISITED);
            }
        }

        int count = dfsAll(dp);

        System.out.println(count);
    }

    private static int dfsAll(int[][][] dp) {
        char start = target.charAt(0);
        int count = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] != start) {
                    continue;
                }
                count += dfs(row, col, 0, dp);
            }
        }

        return count;
    }

    private static int dfs(int row, int col, int depth, int[][][] dp) {
        if (depth == targetSize - 1) {
            return 1;
        }
        if (dp[row][col][depth] != NOT_VISITED) {
            return dp[row][col][depth];
        }

        dp[row][col][depth] = 0;
        for (int[] dir: DIR) {
            for (int diff = 1; diff <= K; diff++) {
                int nextRow = row + dir[0] * diff;
                int nextCol = col + dir[1] * diff;

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }
                if (grid[nextRow][nextCol] != target.charAt(depth + 1)) {
                    continue;
                }
                dp[row][col][depth] += dfs(nextRow, nextCol, depth + 1, dp);
            }
        }

        return dp[row][col][depth];
    }
}
