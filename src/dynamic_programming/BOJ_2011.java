package dynamic_programming;

// https://www.acmicpc.net/problem/2011

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2011 {
    private static int N;
    private static char[] data;
    private static long[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine().toCharArray();
        N = data.length;
        dp = new long[N];
        System.out.println(dfs(0));
    }

    private static long dfs(int depth){
        if(depth >= N)
            return 1;
        if(data[depth] == '0')
            return 0;
        if(dp[depth] != 0)
            return dp[depth];
        if(data[depth] == '1'){
            if(depth != N - 1 && '1' <= data[depth + 1] && data[depth + 1] <= '9')
                dp[depth] = dfs(depth + 1) + dfs(depth + 2);
            else if(depth != N - 1 && data[depth + 1] == '0')
                dp[depth] = dfs(depth + 2);
            else
                dp[depth] = dfs(depth + 1);
        }
        else if(data[depth] == '2'){
            if(depth != N - 1 && '1' <= data[depth + 1] && data[depth + 1] <= '6')
                dp[depth] = dfs(depth + 1) + dfs(depth + 2);
            else if(depth != N - 1 && data[depth + 1] == '0')
                dp[depth] = dfs(depth + 2);
            else
                dp[depth] = dfs(depth + 1);
        }
        else
            dp[depth] = dfs(depth + 1);
        return dp[depth] % 1_000_000;
    }
}
