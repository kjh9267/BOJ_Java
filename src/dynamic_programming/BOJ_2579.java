package dynamic_programming;

// https://www.acmicpc.net/problem/2579

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2579 {

    private static final int WRONG_PATH = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N + 1];
        int[][] dp = new int[N + 1][3];

        for(int idx = 1; idx <= N; idx++) {
            data[idx] = Integer.parseInt(br.readLine());
        }

        System.out.println(dfs(0, 0, N, data, dp));
    }

    private static int dfs(int cur, int cnt, int N, int[] data, int[][] dp) {
        if(cur == N) {
            return data[cur];
        }
        if(cur > N) {
            return WRONG_PATH;
        }
        if(dp[cur][cnt] != 0) {
            return dp[cur][cnt];
        }

        int max = WRONG_PATH;

        if(cnt < 2) {
            int retValue = dfs(cur + 1, cnt + 1, N, data, dp);
            if (retValue != WRONG_PATH) {
                max = Math.max(max, retValue + data[cur]);
            }
        }
        int retValue = dfs(cur + 2, 1, N, data, dp);
        if (retValue != WRONG_PATH) {
            max = Math.max(max, retValue + data[cur]);
        }

        dp[cur][cnt] = max;

        return dp[cur][cnt];
    }
}
