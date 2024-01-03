package depth_first_search;

// https://www.acmicpc.net/problem/6743

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6743 {

    private static int x1;

    private static int y1;

    private static int x2;

    private static int y2;

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) {
                break;
            }

            H = (H << 1) + 1;
            W = (W << 1) + 1;
            boolean[][] grid = new boolean[H][W];

            int N = Integer.parseInt(br.readLine());

            for (int loop = 0; loop < N; loop++) {
                st = new StringTokenizer(br.readLine());
                x1 = Integer.parseInt(st.nextToken()) << 1;
                y1 = Integer.parseInt(st.nextToken()) << 1;
                x2 = Integer.parseInt(st.nextToken()) << 1;
                y2 = Integer.parseInt(st.nextToken()) << 1;

                if (x1 >= x2 && y1 >= y2) {
                    swap();
                }

                cut(grid);
            }

            int result = dfsAll(grid, H, W);
            sb.append(result)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void swap() {
        int temp = x1;
        x1 = x2;
        x2 = temp;

        temp = y1;
        y1 = y2;
        y2 = temp;
    }

    private static void cut(boolean[][] grid) {
        for (int y = y1; y <= y2; y++) {
            grid[y][x1] = true;
            grid[y][x2] = true;
        }
        for (int x = x1; x <= x2; x++) {
            grid[y1][x] = true;
            grid[y2][x] = true;
        }
    }

    private static int dfsAll(boolean[][] grid, int H, int W) {
        int count = 0;

        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                if (grid[row][col]) {
                    continue;
                }
                dfs(col, row, H, W, grid);
                count += 1;
            }
        }

        return count;
    }

    private static void dfs(int x, int y, int H, int W, boolean[][] grid) {
        if (x < 0 || x == W || y < 0 || y == H) {
            return;
        }
        if (grid[y][x]) {
            return;
        }
        grid[y][x] = true;

        for (int[] dir: DIR) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            dfs(nextX, nextY, H, W, grid);
        }
    }
}
