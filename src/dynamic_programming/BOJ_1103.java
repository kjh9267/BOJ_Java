package dynamic_programming;

// https://www.acmicpc.net/problem/1103

import sun.nio.cs.ext.MacHebrew;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1103 {
    private static final int[][] DIR = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private static int[][] dp;
    private static char[][] grid;
    private static boolean[][] finished;
    private static int N, M;
    private static boolean cycle;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        dp = new int[N][M];
        finished = new boolean[N][M];

        for(int row = 0; row < N; row++){
            String line = br.readLine();
            for(int col = 0; col < M; col++){
                grid[row][col] = line.charAt(col);
            }
        }

        for(int row = 0; row < N; row++)
            Arrays.fill(dp[row], -1);

        int res = dfs(0, 0);

        if(cycle)
            System.out.println(-1);
        else
            System.out.println(res);
    }

    public static int dfs(int x, int y){
        if(x < 0 || x >= M || y < 0 || y >= N || grid[y][x] == 'H')
            return 0;
        if(dp[y][x] != -1){
            if(!finished[y][x])
                cycle = true;
            return dp[y][x];
        }
        dp[y][x] = 0;
        int jump = grid[y][x] - '0';
        for(int[] dir: DIR){
            int xx = x + dir[0] * jump;
            int yy = y + dir[1] * jump;
            dp[y][x] = Math.max(dp[y][x], dfs(xx, yy) + 1);
        }
        finished[y][x] = true;
        return dp[y][x];
    }
}
