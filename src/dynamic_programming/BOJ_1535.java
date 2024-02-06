package dynamic_programming;

// https://www.acmicpc.net/problem/1535

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class BOJ_1535 {

    private static final int NOT_VISITED = -1;

    private static final int HEALTH = 100;

    private static int[] minus;

    private static int[] plus;

    private static int[][] dp;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minus = new int[N];
        plus = new int[N];
        dp = new int[N][HEALTH + 1];

        for (int index = 0; index < N; index++) {
            Arrays.fill(dp[index], NOT_VISITED);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0 ; index < N; index++) {
            minus[index] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int index = 0 ; index < N; index++) {
            plus[index] = Integer.parseInt(st.nextToken());
        }

        int result = dfs(0, HEALTH);
        System.out.println(result);
    }

    private static int dfs(int cur, int health) {
        if (cur == N) {
            return 0;
        }
        if (dp[cur][health] != NOT_VISITED) {
            return dp[cur][health];
        }

        dp[cur][health] = 0;
        if (health - minus[cur] >= 1) {
            dp[cur][health] = max(dp[cur][health], dfs(cur + 1, health - minus[cur]) + plus[cur]);
        }
        dp[cur][health] = max(dp[cur][health], dfs(cur + 1, health));

        return dp[cur][health];
    }
}
