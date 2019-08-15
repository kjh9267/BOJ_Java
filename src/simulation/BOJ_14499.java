package simulation;

// https://www.acmicpc.net/problem/14499

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
    public static final int[][] DIR = {{}, {1, 0}, {-1 , 0}, {0, -1}, {0, 1}};
    public static int N, M;
    public static int[][] grid;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Block b = new Block(x, y);
        st = new StringTokenizer(br.readLine());
        while(K-- > 0){
            int idx = Integer.parseInt(st.nextToken());
            int value = roll(idx, b);
            if(value != -1)
                sb.append(value).append('\n');
        }
        System.out.print(sb);
    }
    public static int roll(int idx, Block b){
        int xx = b.x + DIR[idx][0];
        int yy = b.y + DIR[idx][1];
        if(!(0 <= xx && xx < M && 0 <= yy && yy < N))
            return -1;
        b.x = xx;
        b.y = yy;
        int temp = b.down;
        if(idx == 1){
            b.down = b.right;
            b.right = b.up;
            b.up = b.left;
            b.left = temp;
        }
        else if(idx == 2){
            b.down = b.left;
            b.left = b.up;
            b.up = b.right;
            b.right = temp;
        }
        else if(idx == 3){
            b.down = b.back;
            b.back = b.up;
            b.up = b.front;
            b.front = temp;
        }
        else {
            b.down = b.front;
            b.front = b.up;
            b.up = b.back;
            b.back = temp;
        }
        if(grid[b.y][b.x] != 0) {
            b.down = grid[b.y][b.x];
            grid[b.y][b.x] = 0;
        }
        else
            grid[b.y][b.x] = b.down;
        return b.up;
    }
    public static class Block{
        int x, y, front = 0, back = 0, left = 0, right = 0, up = 0, down = 0;
        public Block(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
