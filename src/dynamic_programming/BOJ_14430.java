package dynamic_programming;

// https://www.acmicpc.net/problem/14430

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14430 {
    public static final int[][] DIR = {{1, 0}, {0, 1}};
    public static int N, M;
    public static int[][] grid, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        dp = new int[N][M];

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for(int row = 0; row < N; row++)
            Arrays.fill(dp[row], -1);

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y){
        if(x == M || y == N)
            return 0;
        if(dp[y][x] != -1)
            return dp[y][x];
        dp[y][x] = 0;
        for(int[] dir: DIR){
            int xx = x + dir[0];
            int yy = y + dir[1];
            dp[y][x] = Math.max(dp[y][x], grid[y][x] + dfs(xx, yy));
        }
        return dp[y][x];
    }
}
