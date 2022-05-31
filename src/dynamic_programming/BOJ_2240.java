package dynamic_programming;

// https://www.acmicpc.net/problem/2240

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2240 {

    private static final int NOT_VISITED = -1;

    private static int[] fruits;

    private static int T;

    private static int W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        fruits = new int[T + 1];

        for (int time = 1; time <= T; time++) {
            fruits[time] = Integer.parseInt(br.readLine()) - 1;
        }

        int[][][] dp = new int[T + 1][31][2];

        for (int time = 0; time <= T; time++) {
            for (int moveCount = 0; moveCount <= W; moveCount++) {
                Arrays.fill(dp[time][moveCount], NOT_VISITED);
            }
        }

        int maxCount = dfs(0, 0, 0, dp);

        System.out.println(maxCount);
    }

    private static int dfs(int time, int moveCount, int location, int[][][] dp) {
        if (dp[time][moveCount][location] != NOT_VISITED) {
            return dp[time][moveCount][location];
        }

        dp[time][moveCount][location] = 0;
        int nextTime = time + 1;
        int nextLocation = (location + 1) % 2;

        if (nextTime > T) {
            return 0;
        }
        if (fruits[nextTime] == location) {
            dp[time][moveCount][location] = Math.max(dp[time][moveCount][location], dfs(nextTime, moveCount, location, dp) + 1);
            if (moveCount < W) {
                dp[time][moveCount][location] = Math.max(dp[time][moveCount][location], dfs(nextTime, moveCount + 1, nextLocation, dp));
            }
        }
        else {
            dp[time][moveCount][location] = Math.max(dp[time][moveCount][location], dfs(nextTime, moveCount, location, dp));
            if (moveCount < W) {
                dp[time][moveCount][location] = Math.max(dp[time][moveCount][location], dfs(nextTime, moveCount + 1, nextLocation, dp) + 1);
            }
        }

        return dp[time][moveCount][location];
    }
}
