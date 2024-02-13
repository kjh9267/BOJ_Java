package dynamic_programming;

// https://www.acmicpc.net/problem/17845

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class BOJ_17845 {

    private static int N;

    private static int K;

    private static Lecture[] data;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        data = new Lecture[K];
        dp = new int[K][N + 1];

        for (int index = 0; index < K; index++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            data[index] = new Lecture(value, cost);
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }

    private static class Lecture {
        int value;
        int cost;

        Lecture(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }
    }

    private static int dfs(int cur, int total) {
        if (cur == K) {
            return 0;
        }
        if (dp[cur][total] != 0) {
            return dp[cur][total];
        }

        if (total + data[cur].cost <= N) {
            dp[cur][total] = max(dp[cur][total], dfs(cur + 1, total + data[cur].cost) + data[cur].value);
        }
        dp[cur][total] = max(dp[cur][total], dfs(cur + 1, total));

        return dp[cur][total];
    }
}
