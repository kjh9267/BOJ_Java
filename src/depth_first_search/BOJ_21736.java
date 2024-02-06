package depth_first_search;

// https://www.acmicpc.net/problem/21736

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21736 {

    private static final String IMPOSSIBLE = "TT";

    private static final char WALL = 'X';

    private static final char PERSON = 'P';

    private static final char START = 'I';

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int N;

    private static int M;

    private static char[][] grid;

    private static boolean[][] visited;

    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == START) {
                    visited[row][col] = true;
                    dfs(col, row);
                }
            }
        }

        if (count == 0) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(count);
        }
    }

    private static void dfs(int x, int y) {
        if (grid[y][x] == PERSON) {
            count += 1;
        }

        for (int[] dir: DIR) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            if (nextX < 0 || nextX == M || nextY < 0 || nextY == N) {
                continue;
            }
            if (grid[nextY][nextX] == WALL) {
                continue;
            }
            if (visited[nextY][nextX]) {
                continue;
            }
            visited[nextY][nextX] = true;
            dfs(nextX, nextY);
        }
    }
}
