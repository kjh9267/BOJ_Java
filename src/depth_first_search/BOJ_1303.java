package depth_first_search;

// https://www.acmicpc.net/problem/1303

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1303 {
    public static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
    public static int N, M, A, B, cnt;
    public static char[][] grid;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        int A = 0, B = 0;

        for(int i = 0; i < N; i++)
            grid[i] = br.readLine().toCharArray();

        visited = new boolean[N][M];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(visited[row][col] || grid[row][col] != 'W')
                    continue;
                dfs(col, row, 'W');
                A += cnt * cnt;
                cnt = 0;
            }
        }
        visited = new boolean[N][M];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(visited[row][col] || grid[row][col] != 'B')
                    continue;
                dfs(col, row, 'B');
                B += cnt * cnt;
                cnt = 0;
            }
        }
        System.out.println(A + " " + B);
    }
    public static void dfs(int x, int y, char value){
        if(visited[y][x])
            return;
        visited[y][x] = true;
        for(int[] dir : DIR){
            int xx = x + dir[0];
            int yy = y + dir[1];
            if(!(0 <= xx && xx < M && 0 <= yy && yy < N))
                continue;
            if(grid[yy][xx] != value)
                continue;
            dfs(xx, yy, value);
        }
        cnt += 1;
    }
}
