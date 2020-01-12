package dynamic_programming;

// https://www.acmicpc.net/problem/2688

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2688 {
    public static final int MAX = 64;
    public static final String NEW_LINE = "\n";
    public static long[][] dp;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        dp = new long[MAX][10];

        for(int n = 0; n < 10; n++)
            dfs(0, n);

        while (T-- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[MAX - n][0]).append(NEW_LINE);
        }
        System.out.println(sb);
    }

    public static long dfs(int depth, int num){
        if(depth == MAX)
            return 1;
        if(dp[depth][num] != 0)
            return dp[depth][num];
        for(int n = num; n < 10; n++)
            dp[depth][num] += dfs(depth + 1, n);
        return dp[depth][num];
    }
}
