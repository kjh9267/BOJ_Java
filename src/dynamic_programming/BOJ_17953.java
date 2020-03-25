package dynamic_programming;

// https://www.acmicpc.net/problem/17953

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17953 {
    private static int N, M;
    private static int[][][] dp;
    private static int[][] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M][N][2];
        data = new int[M][N];

        for(int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++) {
                data[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++) {
                Arrays.fill(dp[row][col], -1);
            }
        }

        System.out.println(dfsAll());
    }

    public static int dfsAll() {
        int res = 0;

        for(int food = 0; food < M; food++) {
            res = Math.max(res, dfs(0, food, 0));
        }

        return res;
    }

    public static int dfs(int day, int food, int same) {
        if(day == N)
            return 0;
        if(dp[food][day][same] != -1)
            return dp[food][day][same];
        dp[food][day][same] = 0;

        int value;
        if(same == 0)
            value = data[food][day];
        else
            value = data[food][day] / 2;

        for(int idx = 0; idx < M; idx++) {
            int retValue;
            if(food == idx)
                retValue = dfs(day + 1, idx, 1) + value;
            else
                retValue = dfs(day + 1, idx, 0) + value;
            dp[food][day][same] = Math.max(dp[food][day][same], retValue);
        }

        return dp[food][day][same];
    }
}
