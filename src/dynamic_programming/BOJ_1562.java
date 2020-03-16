package dynamic_programming;

// https://www.acmicpc.net/problem/1562

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1562 {
    private static final int MOD = 1_000_000_000, TARGET = 1023;
    private static int N;
    private static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][10][TARGET + 1];

        for(int idx = 1; idx <= N; idx++) {
            for(int num = 0; num <= 9; num++) {
                Arrays.fill(dp[idx][num], -1);
            }
        }

        System.out.println(dfsAll());
    }

    public static int dfsAll() {
        int res = 0;
        for(int num = 1; num <= 9; num++) {
            res += dfs(1, num, 1 << num);
            res %= MOD;
        }
        return res;
    }

    public static int dfs(int cur, int num, int bit) {
        if(cur == N) {
            if(bit == TARGET) { return 1; }
            return 0;
        }
        if(dp[cur][num][bit] != -1)
            return dp[cur][num][bit];
        dp[cur][num][bit] = 0;
        if(num < 9) {
            int nxtNum = num + 1;
            int nxtBit = bit | (1 << nxtNum);
            dp[cur][num][bit] += dfs(cur + 1, nxtNum, nxtBit) % MOD;
        }
        if(num > 0) {
            int nxtNum = num - 1;
            int nxtBit = bit | (1 << nxtNum);
            dp[cur][num][bit] += dfs(cur + 1, nxtNum, nxtBit) % MOD;
        }
        return dp[cur][num][bit] % MOD;
    }
}
