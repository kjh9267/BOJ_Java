package dynamic_programming;

// https://www.acmicpc.net/problem/10164

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164 {
    public static boolean[][] grid;
    public static int[][] dp;
    public static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new boolean[N][M];
        dp = new int[N][M];

        while (st.hasMoreTokens()){
            int K = Integer.parseInt(st.nextToken()) - 1;
            int x = K % M;
            int y = K / M;
            for(int row = N - 1; row > y; row--){
                for(int col = 0; col < x; col++){
                    grid[row][col] = true;
                }
            }
            for(int row = 0; row < y; row++){
                for(int col = M - 1; col > x; col--){
                    grid[row][col] = true;
                }
            }
        }
        System.out.println(dfs(0, 0));
    }
    public static int dfs(int x, int y){
        if(grid[y][x])
            return 0;
        if(x == M - 1 && y == N - 1)
            return 1;
        if(dp[y][x] != 0)
            return dp[y][x];
        if (x + 1 < M)
            dp[y][x] += dfs(x + 1, y);
        if (y + 1 < N)
            dp[y][x] += dfs(x, y + 1);
        return dp[y][x];
    }
}
