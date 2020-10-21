package depth_first_search;

// https://www.acmicpc.net/problem/14890

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
    private static final int[][] DIR = {{1, 0}, {0, 1}};
    private static int N;
    private static int L;
    private static int[][] grid;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }

    private static int solve(){
        int cnt = 0;
        visited = new boolean[N][N];
        for(int row = 0; row < N; row++){
            dfs(0, row, 0, false, 0, 1);
            boolean key = true;
            for(int col = 0; col < N; col++){
                if (!visited[row][col]){
                    key = false;
                    break;
                }
            }
            if(key)
                cnt += 1;
        }
        visited = new boolean[N][N];
        for(int col = 0; col < N; col++){
            dfs(col, 0, 1, false, 0, 1);
            boolean key = true;
            for(int row = 0; row < N; row++){
                if (!visited[row][col]){
                    key = false;
                    break;
                }
            }
            if(key)
                cnt += 1;
        }
        return cnt;
    }

    private static void dfs(int x, int y, int idx, boolean down, int len, int cnt){
        if(len == L)
            down = false;
        visited[y][x] = true;
        int xx = x + DIR[idx][0];
        int yy = y + DIR[idx][1];
        if(!(0 <= xx && xx < N && 0 <= yy && yy < N)) {
            if(down)
                visited[y][x] = false;
            return;
        }
        if(!down){
            if(grid[yy][xx] == grid[y][x])
                dfs(xx, yy, idx, false,0, cnt + 1);
            else if(grid[yy][xx] == grid[y][x] - 1)
                dfs(xx, yy, idx, true,1, 0);
            else if(grid[yy][xx] == grid[y][x] + 1 && cnt >= L)
                dfs(xx, yy, idx, false, 0, 1);
        }
        else if(grid[yy][xx] == grid[y][x]){
            dfs(xx, yy, idx, true,len + 1, 0);
        }
    }
}
