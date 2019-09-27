package dynamic_programming;

// https://www.acmicpc.net/problem/1309

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1309 {
    public static int N;
    public static boolean[][] grid;
    public static int [][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new boolean[N + 1][2];
        dp = new int[N + 1][3];
        System.out.println(dfs(0, 0));
    }
    public static int dfs(int idx, int state){
        if(idx == N)
            return 1;
        if(dp[idx][state] != 0)
            return dp[idx][state];
        for(int i = 0; i < 3; i++) {
            if(i == state && state != 0)
                continue;
            dp[idx][state] += dfs(idx + 1, i);
        }
        return dp[idx][state] % 9901;
    }
}
