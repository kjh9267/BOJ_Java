package depth_first_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1245 {

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    private static final int ZERO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        int mountainCount = countMountains(N, M, grid, visited);

        System.out.println(mountainCount);
    }

    private static int countMountains(int N, int M, int[][] grid, boolean[][] visited) {
        int result = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col]) {
                    continue;
                }
                visited[row][col] = true;
                if (grid[row][col] == ZERO) {
                    continue;
                }
                if (isMountain(row, col, grid, N, M, visited)) {
                    result += 1;
                }
            }
        }

        return result;
    }

    private static boolean isMountain(int row, int col, int[][] grid, int N, int M, boolean[][] visited) {
        boolean result = true;

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                continue;
            }
            if (grid[row][col] < grid[nextRow][nextCol]) {
                result = false;
                continue;
            }
            if (grid[row][col] > grid[nextRow][nextCol]) {
                continue;
            }
            if (visited[nextRow][nextCol]) {
                continue;
            }
            visited[nextRow][nextCol] = true;
            if(!isMountain(nextRow, nextCol, grid, N, M, visited)) {
                result = false;
            }
        }

        return result;
    }
}
