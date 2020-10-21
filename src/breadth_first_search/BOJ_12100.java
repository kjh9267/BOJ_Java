package breadth_first_search;

// https://www.acmicpc.net/problem/12100

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12100 {
    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static int N;
    private static int res;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];

        for(int row = 0; row < N; row++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(grid);
        System.out.println(res);
    }

    private static void bfs(int[][] grid){
        Queue<int[][]> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(grid);
        visited.add(Arrays.deepToString(grid));
        maxCheck(grid);
        int cnt = 0;
        while(!queue.isEmpty()){
            int len = queue.size();
            cnt += 1;
            if(cnt == 6)
                break;
            while (len-- > 0){
                int[][] cur = queue.poll();

                for(int i = 0; i < 4; i++){
                    int[][] nxt = check(cur, i);
                    String key = Arrays.deepToString(nxt);
                    if(visited.contains(key))
                        continue;
                    queue.add(nxt);
                    visited.add(key);
                    maxCheck(nxt);
                }
            }
        }
    }

    private static int[][] check(int[][] grid, int idx){
        int[][] temp = new int[N][N];
        boolean[][] add = new boolean[N][N];

        for(int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                temp[row][col] = grid[row][col];
            }
        }
        if(idx == 0) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (temp[row][col] == 0)
                        continue;
                    go(col, row, temp, idx, add);
                }
            }
        }
        else if(idx == 1){
            for (int col = N - 1; col >= 0; col--) {
                for (int row = 0; row < N; row++) {
                    if (temp[row][col] == 0)
                        continue;
                    go(col, row, temp, idx, add);
                }
            }
        }
        else if(idx == 2){
            for (int row = N - 1; row >= 0; row--) {
                for (int col = 0; col < N; col++) {
                    if (temp[row][col] == 0)
                        continue;
                    go(col, row, temp, idx, add);
                }
            }
        }
        else{
            for (int col = 0; col < N; col++) {
                for (int row = 0; row < N; row++) {
                    if (temp[row][col] == 0)
                        continue;
                    go(col, row, temp, idx, add);
                }
            }
        }
        return temp;
    }

    private static int[][] go(int x, int y, int[][] temp, int idx, boolean[][] add){
        while(true){
            int xx = x + DIR[idx][0];
            int yy = y + DIR[idx][1];
            if(-1 == xx || xx == N || -1 == yy || yy == N)
                break;
            if(temp[yy][xx] != 0) {
                if (temp[yy][xx] == temp[y][x] && !add[yy][xx]) {
                    temp[yy][xx] *= 2;
                    temp[y][x] = 0;
                    add[yy][xx] = true;
                }
                break;
            }
            temp[yy][xx] = temp[y][x];
            temp[y][x] = 0;
            x = xx;
            y = yy;
        }
        return temp;
    }

    private static void maxCheck(int[][] tmp){
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                res = Math.max(res, tmp[row][col]);
            }
        }
    }
}
