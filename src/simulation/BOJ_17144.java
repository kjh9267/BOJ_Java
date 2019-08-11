package simulation;

// https://www.acmicpc.net/problem/17144

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
    public static final int[][] DIR = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}, REV = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static int N, M;
    public static int[][] grid, add;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        int up = 0, down = 0;

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < N; i++) {
            if (grid[i][0] == -1) {
                up += i;
                break;
            }
        }
        for(int i = N - 1 ; i >= 0; i--) {
            if (grid[i][0] == -1) {
                down += i;
                break;
            }
        }
        while (T-- > 0){
            add = new int[N][M];
            for(int row = 0; row < N; row++){
                for(int col = 0; col < M; col++){
                    int value = grid[row][col];
                    if(value > 0){
                        int cnt = 0;
                        for(int[] dir : DIR){
                            int xx = col + dir[0];
                            int yy = row + dir[1];
                            if(!(0 <= xx && xx < M && 0 <= yy && yy < N))
                                continue;
                            if(grid[yy][xx] == -1)
                                continue;
                            add[yy][xx] += value / 5;
                            cnt += 1;
                        }
                        add[row][col] -= value / 5 * cnt;
                    }
                }
            }
            for(int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    grid[row][col] += add[row][col];
                }
            }
            int x = 1, y = up, prev = grid[y][x];
            grid[y][x] = 0;
            for(int[] dir: DIR) {
                while (true) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (!(0 <= xx && xx < M && 0 <= yy && yy < N))
                        break;
                    if(grid[yy][xx] == -1)
                        break;
                    int temp = grid[yy][xx];
                    grid[yy][xx] = prev;
                    prev = temp;
                    x = xx;
                    y = yy;
                }
            }
            x = 1;
            y = down;
            prev = grid[y][x];
            grid[y][x] = 0;
            for(int[] dir: REV) {
                while (true) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (!(0 <= xx && xx < M && 0 <= yy && yy < N))
                        break;
                    if(grid[yy][xx] == -1)
                        break;
                    int temp = grid[yy][xx];
                    grid[yy][xx] = prev;
                    prev = temp;
                    x = xx;
                    y = yy;
                }
            }
        }
        int res = 0;
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                res += grid[row][col] > 0 ? grid[row][col] : 0;
            }
        }
        System.out.println(res);
    }
}
