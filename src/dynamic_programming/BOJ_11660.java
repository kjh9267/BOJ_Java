package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/11660
 */

public class BOJ_11660 {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N + 1][N + 1];
        int[][] acc = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N + 1; j++)
                grid[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i < N + 1; i++)
            for(int j = 1; j < N + 1; j++)
                acc[i][j] = acc[i - 1][j] + acc[i][j - 1] + grid[i][j] - acc[i - 1][j - 1];
        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            sb.append(acc[r2][c2] - acc[r1 - 1][c2] - acc[r2][c1 - 1] + acc[r1 - 1][c1 - 1]).append('\n');
        }
        System.out.println(sb);
    }
}
