package dynamic_programming;

// https://www.acmicpc.net/problem/17070

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17070 {

    private static final char WALL = '1';

    private static final int NOT_VISITED = -1;

    private static final int[][] DIR = {{0, 1}, {1, 1}, {1, 0}};

    private static char[][] grid;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = st.nextToken()
                        .charAt(0);
            }
        }
        
        int[][][] dp = new int[N][N][3];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                Arrays.fill(dp[row][col], NOT_VISITED);
            }
        }

        int count = dfs(0, 0, 0, dp);

        System.out.println(count);
    }

    private static int dfs(int row, int col, int direction, int[][][] dp) {
        if (isEnd(row, col, direction)) {
            return 1;
        }
        if (dp[row][col][direction] != NOT_VISITED) {
            return dp[row][col][direction];
        }

        dp[row][col][direction] = 0;
        int nextRow = row + DIR[direction][0];
        int nextCol = col + DIR[direction][1];
        for (int nextDirection = 0; nextDirection < 3; nextDirection++) {
            if (!canRotate(direction, nextDirection)) {
                continue;
            }
            if (!isInGrid(nextRow, nextCol, nextDirection)) {
                continue;
            }
            if (!canMove(nextRow, nextCol, nextDirection)) {
                continue;
            }
            dp[row][col][direction] += dfs(nextRow, nextCol, nextDirection, dp);
        }
        
        return dp[row][col][direction];
    }

    private static boolean isEnd(int row, int col, int direction) {
        if (row == N - 1 && col == N - 2 && direction == 0) {
            return true;
        }
        if (row == N - 2 && col == N - 2 && direction == 1) {
            return true;
        }
        if (row == N - 2 && col == N - 1 && direction == 2) {
            return true;
        }

        return false;
    }

    private static boolean canRotate(int direction, int nextDirection) {
        return Math.abs(direction - nextDirection) < 2;
    }

    private static boolean isInGrid(int row, int col, int direction) {
        if (row == N || col == N) {
            return false;
        }
        if (direction == 0) {
            return col + 1 < N;
        }
        if (direction == 1) {
            return row + 1 < N && col + 1 < N;
        }
        if (direction == 2) {
            return row + 1 < N;
        }

        return false;
    }

    private static boolean canMove(int row, int col, int direction) {
        if (grid[row][col] == WALL) {
            return false;
        }
        if (direction == 0) {
            return grid[row][col + 1] != WALL;
        }
        if (direction == 1) {
            return grid[row + 1][col] != WALL && grid[row][col + 1] != WALL && grid[row + 1][col + 1] != WALL;
        }
        if (direction == 2) {
            return grid[row + 1][col] != WALL;
        }

        return false;
    }
}
